package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourRepository mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    private static final String ARGS_IS_FAVORITE_MODE = "ARGS_IS_FAVORITE_MODE";

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavoriteMode) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean(ARGS_IS_FAVORITE_MODE, isFavoriteMode);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        boolean isFavoriteMode = getArguments().getBoolean(ARGS_IS_FAVORITE_MODE);
        if (isFavoriteMode) {
            mNeighbours = mApiService.getNeighbours();
        } else {
            mNeighbours = mApiService.getFavoriteNeighbours();
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, (MyNeighbourRecyclerViewAdapter.OnNeightBourListener) getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        boolean isFavoriteMode = getArguments().getBoolean(ARGS_IS_FAVORITE_MODE);

        if (isFavoriteMode) {
            mApiService.deleteNeighbour(event.neighbour);
        } else {
            mApiService.deleteFavNeighbour(event.neighbour);
        }
        initList();
    }
}
