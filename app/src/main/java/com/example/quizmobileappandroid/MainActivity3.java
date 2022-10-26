package com.example.quizmobileappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizmobileappandroid.Models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    private TextView id;
    private ImageView profilePic;
    private TextView fname;
    private TextView lname;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        profilePic = findViewById(R.id.image_view);
        fname = findViewById(R.id.first_name_text_view);
        lname = findViewById(R.id.last_name_text_view);
        email = findViewById(R.id.email_text_view);


        User user = (User)getIntent().getSerializableExtra("userDetail");
        Log.d("DEBUG", user.getFirstname());


        Glide.with(this)
                .load(user.getAvatar())
                .into(profilePic);

        fname.setText(user.getFirstname());
        lname.setText(user.getLastname());
        email.setText(user.getEmail());




    }
}