package com.example.i_schedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private List<MeetingOption> meetingOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        meetingOptions = new ArrayList<>();

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = year + "-" + (month + 1) + "-" + day;
                Toast.makeText(CalendarActivity.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                showAddEventDialog(selectedDate);
            }
        });
    }

    private void showAddEventDialog(final String selectedDate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Meeting Option");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_event, null);
        builder.setView(view);

        final EditText timeEditText = view.findViewById(R.id.editTextTime);
        final EditText locationEditText = view.findViewById(R.id.editTextLocation);
        final EditText descriptionEditText = view.findViewById(R.id.editTextDescription);

        builder.setPositiveButton("Add Option", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String time = timeEditText.getText().toString();
                String location = locationEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                MeetingOption option = new MeetingOption(selectedDate, time, location, description);
                meetingOptions.add(option);

                showOptionAddedDialog(option);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User canceled the meeting option addition
            }
        });

        builder.show();
    }

    private void showOptionAddedDialog(final MeetingOption option) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Option Added");

        builder.setMessage("Meeting option added for " + option.getDate() + " at " + option.getTime());

        builder.setPositiveButton("Send to Contacts", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Implement sending the meeting option to a list of contacts (not shown here)
                // You may want to use intents or any communication mechanism for this
            }
        });

        builder.setNegativeButton("Add Another Option", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAddEventDialog(option.getDate()); // Allow adding another meeting option for the same date
            }
        });

        builder.show();
    }

    private static class MeetingOption {
        private String date;
        private String time;
        private String location;
        private String description;

        public MeetingOption(String date, String time, String location, String description) {
            this.date = date;
            this.time = time;
            this.location = location;
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getLocation() {
            return location;
        }

        public String getDescription() {
            return description;
        }
    }
}
