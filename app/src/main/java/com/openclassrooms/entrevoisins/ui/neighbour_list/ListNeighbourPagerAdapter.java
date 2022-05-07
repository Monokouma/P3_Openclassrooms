package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */
    @Override
    public Fragment getItem(int position) {
        Log.i("Monokouma", String.valueOf(position));
        if (position == 0) {
            return NeighbourFragment.newInstance(true);
        } else if (position == 1) {
            return NeighbourFragment.newInstance(false);
        }
        return NeighbourFragment.newInstance(true);
    }

    /**
     * @return the number of pages
     */
    @Override
    public int getCount() {
        return 2;
    }


}