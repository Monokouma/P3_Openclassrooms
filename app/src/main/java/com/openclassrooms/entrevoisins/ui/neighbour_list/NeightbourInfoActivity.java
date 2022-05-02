package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.DummyNeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

public class NeightbourInfoActivity extends AppCompatActivity  {

    private static final String ARGS_NEIGHBOURID = "ARGS_NEIGHBOURID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neightbour_info);
        Log.i("OUI", String.valueOf(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)));
        NeighbourRepository neighbourRepository = DI.getNeighbourApiService();
        Log.i("OUI", neighbourRepository.getNeighbourByID(getIntent().getLongExtra(ARGS_NEIGHBOURID, 0)).toString());
        
    }

    public static Intent navigate(Context c, long id) {
        Intent intent = new Intent(c, NeightbourInfoActivity.class);
        intent.putExtra(ARGS_NEIGHBOURID, id);
        return intent;
    }

}