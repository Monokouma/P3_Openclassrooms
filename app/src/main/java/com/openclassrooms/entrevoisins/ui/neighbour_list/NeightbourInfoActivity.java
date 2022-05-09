package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import java.util.Locale;

public class NeightbourInfoActivity extends AppCompatActivity  {

    private static final String ARGS_NEIGHBOURID = "ARGS_NEIGHBOURID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neightbour_info);
        NeighbourRepository neighbourRepository = DI.getNeighbourApiService();
        ImageView profileImage = findViewById(R.id.activity_neighbour_info_toolbarImage);
        CollapsingToolbarLayout titleInToolbar = findViewById(R.id.activity_neighbour_info_collapsingToolbar);
        TextView titleInInfoStackView = findViewById(R.id.activity_neighbour_info__text_view_neighbour_name_in_cardview);
        TextView neighbourAdress = findViewById(R.id.activity_neighbour_info_cardview_adress);
        TextView neighbourPhoneNumber = findViewById(R.id.activity_neighbour_info_cardview_phone_number);
        TextView neighbourFacebookLink = findViewById(R.id.activity_neighbour_info_cardview_link);
        TextView neighbourAboutMe = findViewById(R.id.activity_neighbour_info_neighbourAboutMe);
        FloatingActionButton addToFavoriteButton = findViewById(R.id.activity_neighbour_info_addToFavoriteButton);
        TextView backButton = findViewById(R.id.activity_neighbour_info_backButton);

        titleInToolbar.setTitle(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getName());
        Glide.with(this).load(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getAvatarUrl()).placeholder(R.drawable.ic_account)
                .into(profileImage);
        titleInInfoStackView.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getName());
        neighbourAdress.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getAddress());
        neighbourPhoneNumber.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getPhoneNumber());
        neighbourFacebookLink.setText("www.facebook.fr/"+ neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getName().toLowerCase(Locale.ROOT));
        neighbourAboutMe.setText(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getAboutMe());
        Log.i("Monokouma", String.valueOf(neighbourRepository.getFavoriteNeighbours().contains(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)))));

        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (neighbourRepository.getFavoriteNeighbours().contains(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)))) {
                    Toast.makeText(getApplicationContext(),"Vous avez déjà ajouter ce voisin à vos favoris !",Toast.LENGTH_SHORT).show();
                } else {
                    neighbourRepository.toggleNeighbourFavorite(neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).getId());
                    finish();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static Intent navigate(Context c, long id) {
        Intent intent = new Intent(c, NeightbourInfoActivity.class);
        intent.putExtra(ARGS_NEIGHBOURID, id);
        return intent;
    }


}