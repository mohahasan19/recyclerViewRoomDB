package com.example.recyclerviewroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class CreateUser extends AppCompatActivity {

    private static final String TAG = "CreateUser";
    EditText firstName, lastName, email;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.save_user);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "holaaaaaa");
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString());
                        db.userDao().insertAll(user);
                    }
                });

                //db.userDao().insertAll(new User("meow", "maru", "meowmaru@gmail.com"));
                startActivity(new Intent(CreateUser.this, MainActivity.class));
            }
        });
    }

}
