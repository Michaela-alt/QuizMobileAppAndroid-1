package com.example.quizmobileappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<User> users;
    TextView firstname;
    TextView lastname;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String url = "https://reqres.in/api/users";

        RequestQueue queue = Volley.newRequestQueue(this);

        users = new ArrayList<User>();
        for (int i = 0; i < 6; i++) {
            int finalI = i;

//            queue.add(getStringRequest(url, i));
            queue.add(
                    new StringRequest(Request.Method.GET, url, (response) -> {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("data");
                            JSONObject user = array.getJSONObject(finalI);

                            int id = user.getInt("id");
                            String email = user.getString("email");
                            String first_name = user.getString("first_name");
                            String last_name = user.getString("last_name");
                            String avatar = user.getString("avatar");

                            users.add(new User(id, email, first_name, last_name, avatar));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }, (error) -> {

                    })
            );


        }

        prepareListView();
    }

    private StringRequest getStringRequest(String url, int i) {
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try {
                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("data");
                JSONObject user = array.getJSONObject(i);

                String email = user.getString("email");
                String first_name = user.getString("first_name");
                String last_name = user.getString("last_name");
                String avatar = user.getString("avatar");

//                Glide.with(this)
//                        .load(user.getString("avatar"))
//                        .circleCrop()
//                        .into(avatar);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, (error) -> {

        });
    }

    private void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(users);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }

    class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

        ArrayList<User> userModels;


        public DataModelAdapter(ArrayList<User> userModels) {
            this.userModels = userModels;
        }

        @NonNull
        @Override
        public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.list_item_card_value, parent, false);

            return new DataModelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
            User model = userModels.get(position);

            Glide.with(MainActivity2.this)
                    .load(model.getAvatar())
                    .circleCrop()
                    .into(holder.avatarImage);
            holder.firstNameText.setText(model.getFirstname());
            holder.lastNameText.setText(model.getLastname());
        }

        @Override
        public int getItemCount() {
            return userModels.size();
        }

    }

    class DataModelViewHolder extends RecyclerView.ViewHolder {

        View parent;
        ImageView avatarImage;
        TextView firstNameText;
        TextView lastNameText;

        public DataModelViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
            avatarImage = itemView.findViewById(R.id.image_view);
            firstNameText = itemView.findViewById(R.id.title_text);
            lastNameText = itemView.findViewById(R.id.description_text);
        }
    }
}