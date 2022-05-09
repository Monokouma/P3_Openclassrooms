package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import java.util.Locale;

public class NeightbourInfoActivity extends AppCompatActivity {

    private static final String ARGS_NEIGHBOURID = "ARGS_NEIGHBOURID";

    public static Intent navigate(Context c, long id) {
        Intent intent = new Intent(c, NeightbourInfoActivity.class);
        intent.putExtra(ARGS_NEIGHBOURID, id);
        return intent;
    }

    private final NeighbourRepository neighbourRepository = DI.getNeighbourApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neightbour_info);

        setSupportActionBar(findViewById(R.id.activity_neighbour_info_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView profileImage = findViewById(R.id.activity_neighbour_info_toolbarImage);
        CollapsingToolbarLayout titleInToolbar = findViewById(R.id.activity_neighbour_info_collapsingToolbar);
        TextView titleInInfoStackView = findViewById(R.id.activity_neighbour_info_text_view_neighbour_name_in_cardview);
        TextView neighbourAdress = findViewById(R.id.activity_neighbour_info_cardview_adress);
        TextView neighbourPhoneNumber = findViewById(R.id.activity_neighbour_info_cardview_phone_number);
        TextView neighbourFacebookLink = findViewById(R.id.activity_neighbour_info_cardview_link);
        TextView neighbourAboutMe = findViewById(R.id.activity_neighbour_info_neighbourAboutMe);
        FloatingActionButton addToFavoriteButton = findViewById(R.id.activity_neighbour_info_addToFavoriteButton);

        Neighbour neighbour = neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0));

        titleInToolbar.setTitle(neighbour.getName());
        Glide.with(this).load(neighbour.getAvatarUrl()).placeholder(R.drawable.ic_account).into(profileImage);
        titleInInfoStackView.setText(neighbour.getName());
        neighbourAdress.setText(neighbour.getAddress());
        neighbourPhoneNumber.setText(neighbour.getPhoneNumber());
        neighbourFacebookLink.setText("www.facebook.fr/" + neighbour.getName().toLowerCase(Locale.ROOT));
        neighbourAboutMe.setText(neighbour.getAboutMe());
        Log.i("Monokouma", String.valueOf(neighbourRepository.getFavoriteNeighbours().contains(neighbour)));

        neighbourAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NeightbourInfoActivity.this, "on a cliqué sur l'addresse", Toast.LENGTH_SHORT).show();
            }
        });

        updateFavorite(neighbour, addToFavoriteButton);

        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                neighbourRepository.toggleNeighbourFavorite(neighbour.getId());

                updateFavorite(neighbour, addToFavoriteButton);
            }
        });
    }

    private void updateFavorite(Neighbour neighbour, FloatingActionButton addToFavoriteButton) {
        if (neighbourRepository.isNeighbourFavorite(neighbour.getId())) {
            addToFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            addToFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}