package com.example.manhnd16.mp3zingproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by mac on 6/30/18.
 */

public class DashboardViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragmentArrayList  = new ArrayList<>();
    private ArrayList<String> mFragmentTitle = new ArrayList<>();
    public DashboardViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }
    public void addFragment(Fragment fragment, String title) {
        mFragmentArrayList.add(fragment);
        mFragmentTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position).toString();
    }
}
