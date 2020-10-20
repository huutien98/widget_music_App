package com.shinmusic.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerPlaylistNhacAdapter extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();


    public ViewPagerPlaylistNhacAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public  void AddFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
