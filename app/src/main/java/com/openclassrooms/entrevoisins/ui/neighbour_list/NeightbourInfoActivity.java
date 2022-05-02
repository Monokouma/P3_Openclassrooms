package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import java.util.Locale;

public class NeightbourInfoActivity extends AppCompatActivity  {

    private static final String ARGS_NEIGHBOURID = "ARGS_NEIGHBOURID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neightbour_info);
        NeighbourRepository neighbourRepository = DI.getNeighbourApiService();
        ImageView profileImage = findViewById(R.id.toolbarImage);
        CollapsingToolbarLayout titleInToolbar = findViewById(R.id.collapsingToolbar);
        TextView titleInInfoStackView = findViewById(R.id.neighbourName);
        TextView neighbourAdress = findViewById(R.id.neighbourAdress);
        TextView neighbourPhoneNumber = findViewById(R.id.neighbourPhoneNumber);
        TextView neighbourFacebookLink = findViewById(R.id.neighbourFacebookLink);
        TextView neighbourAboutMe = findViewById(R.id.neighbourAboutMe);
        FloatingActionButton addToFavoriteButton = findViewById(R.id.addToFavoriteButton);

        titleInToolbar.setTitle(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getName());
        Glide.with(this).load(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getAvatarUrl()).placeholder(R.drawable.ic_account)
                .into(profileImage);
        titleInInfoStackView.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getName());
        neighbourAdress.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getAddress());
        neighbourPhoneNumber.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getPhoneNumber());
        neighbourFacebookLink.setText("www.facebook.fr/"+ neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getName().toLowerCase(Locale.ROOT));
        neighbourAboutMe.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getAboutMe());

    }

    public static Intent navigate(Context c, long id) {
        Intent intent = new Intent(c, NeightbourInfoActivity.class);
        intent.putExtra(ARGS_NEIGHBOURID, id);
        return intent;
    }



}