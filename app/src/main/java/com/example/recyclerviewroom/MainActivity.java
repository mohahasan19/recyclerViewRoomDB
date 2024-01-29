package com.example.recyclerviewroom;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<User> users;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").build();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                users = db.userDao().getAllUsers();

                // Update the UI on the main thread using View.post
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Update the RecyclerView adapter with the data obtained from the database
                        adapter = new UserAdapter(users);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "HIIIIIIIIII");
                startActivity(new Intent(MainActivity.this, CreateUser.class));
            }
        });
    }
}
