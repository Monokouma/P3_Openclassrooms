package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;

public class NeightbourInfoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neightbour_info);
        ImageView avatarImage = findViewById(R.id.avatarImage);
        TextView nameOnPictureTextView = findViewById(R.id.namePictureTextView);
        TextView nameInView = findViewById(R.id.nameInView);
        TextView adressTextView = findViewById(R.id.adressTextView);
        TextView phoneNumberTextView = findViewById(R.id.phoneNumbertextView);
        TextView urltextView = findViewById(R.id.urlTextView);
        TextView aboutMeTextview = findViewById(R.id.aboutMeTextView);
        TextView backButton = findViewById(R.id.backButton);
        FloatingActionButton addToFavoriteButton = findViewById(R.id.addToFavoriteButton);


        Intent intent = getIntent();
        String neightboursName = intent.getStringExtra("neightboursName");
        String neightboursAdress = intent.getStringExtra("neightboursAdress");
        Integer neightboursId = intent.getIntExtra("neightboursId", 999999999);
        String neightboursPhoneNumber = intent.getStringExtra("neightboursPhoneNumber");
        String neightboursAvatarUrl = intent.getStringExtra("neightboursAvatarUrl");
        String neightboursAboutMe = intent.getStringExtra("neightboursAboutMe");


        Glide.with(this).load(neightboursAvatarUrl).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.centerCropTransform()).into(avatarImage);

        nameOnPictureTextView.setText(neightboursName);
        nameInView.setText(neightboursName);
        adressTextView.setText(neightboursAdress);
        phoneNumberTextView.setText(neightboursPhoneNumber);
        urltextView.setText("www.facebook.fr/ " + neightboursName);
        aboutMeTextview.setText(neightboursAboutMe);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}