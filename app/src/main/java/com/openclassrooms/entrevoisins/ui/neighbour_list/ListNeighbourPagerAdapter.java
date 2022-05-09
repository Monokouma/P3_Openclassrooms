package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */
    @Override
    public Fragment getItem(int position) {
        boolean isNormalMode = true;
        if (position == 0) {
            isNormalMode = true;
        } else if (position == 1) {
            isNormalMode = false;
        }

        return NeighbourFragment.newInstance(isNormalMode);
    }

    /**
     * @return the number of pages
     */
    @Override
    public int getCount() {
        return 2;
    }


}