package com.example.musicapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.musicapp.R;
import com.example.musicapp.fragment.SongFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.favourite, R.string.songs,  R.string.playlist};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                SongFragment objSongFragment0 = new SongFragment(mContext);
                return  objSongFragment0;

            case 1:
                SongFragment objSongFragment1 = new SongFragment(mContext);
                return  objSongFragment1;
            case 2:
                SongFragment objSongFragment2 = new SongFragment(mContext);
                return  objSongFragment2;
            case 3:
                SongFragment objSongFragment3 = new SongFragment(mContext);
                return  objSongFragment3;

        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}