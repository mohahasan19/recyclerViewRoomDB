package com.example.recyclerviewroom;

import java.util.List;

public class DatabaseThread extends Thread {
    private AppDatabase db;
    private MainActivity activity;

    public DatabaseThread(AppDatabase db, MainActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    @Override
    public void run() {
        // Perform database operations on the background thread
        List<User> users = db.userDao().getAllUsers();

        // Update the UI with the data obtained from the database
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Update the UI (e.g., update the RecyclerView adapter)
                // You can access the UI elements directly here
            }
        });
    }
}