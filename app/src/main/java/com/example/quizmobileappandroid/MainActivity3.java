package com.example.quizmobileappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.quizmobileappandroid.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    private TextView firstname;
    private TextView lastname;
    private TextView email;
    private ImageView avatar;
    private Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id", 7);

        firstname = findViewById(R.id.first_name_text_view);
        lastname = findViewById(R.id.last_name_text_view);
        email = findViewById(R.id.email_text_view);
        avatar = findViewById(R.id.image_view);
        goBackBtn = findViewById(R.id.btnBack);

        goBackBtn.setOnClickListener(view -> {
            finish();
        });

        String url = "https://reqres.in/api/users/" + id;

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getStringRequest(url));
    }

    private StringRequest getStringRequest(String url){
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try{
                JSONObject object = new JSONObject(response);
                JSONObject user = object.getJSONObject("data");
//                JSONObject user = array.getJSONObject(0);

                firstname.setText(user.getString("first_name"));
                lastname.setText(user.getString("last_name"));
                email.setText(user.getString("email"));

                Glide.with(this)
                        .load(user.getString("avatar"))
                        .circleCrop()
                        .into(avatar);

            } catch (JSONException e){
                e.printStackTrace();
            }

        }, (error) -> {

        });
    }
}