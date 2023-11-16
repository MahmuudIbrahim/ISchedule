package com.example.i_schedule;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ProfileActivity {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> profileCollection;

    public ProfileActivity() {
        mongoClient = MongoClients.create("mongodb://localhost:9999");
        database = mongoClient.getDatabase("ishcedule_profiles");
        profileCollection = database.getCollection("profiles");
    }

    public void displayUserProfile(String userId) {
        Document userProfile = profileCollection.find(new Document("userId", userId)).first();
        System.out.println("User Profile:");
        System.out.println("Name: " + userProfile.getString("name"));
        System.out.println("Email: " + userProfile.getString("email"));
    }

    public void updateProfile(String userId, String newName, String newEmail) {
        // Find the user's profile
        Document userQuery = new Document("userId", userId);
        Document userProfile = profileCollection.find(userQuery).first();
        userProfile.put("name", newName);
        userProfile.put("email", newEmail);

        profileCollection.replaceOne(userQuery, userProfile);

        System.out.println("User profile updated successfully!");
    }

    public static void main(String[] args) {
        ProfileActivity profileActivity = new ProfileActivity();

        String userId = "user123";

        profileActivity.displayUserProfile(userId);


        profileActivity.updateProfile(userId, "New Name", "newemail@example.com");


        profileActivity.displayUserProfile(userId);
    }
}