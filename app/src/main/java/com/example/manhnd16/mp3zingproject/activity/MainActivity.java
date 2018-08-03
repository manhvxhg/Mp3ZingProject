package com.example.manhnd16.mp3zingproject.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.DashboardViewPagerAdapter;
import com.example.manhnd16.mp3zingproject.fragment.HomeFragment;
import com.example.manhnd16.mp3zingproject.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView();
        init();
    }

    /**
     * init view
     */
    private void init() {
        DashboardViewPagerAdapter dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
        dashboardViewPagerAdapter.addFragment(new HomeFragment(), getString(R.string.homepage_title));
        dashboardViewPagerAdapter.addFragment(new SearchFragment(), getString(R.string.search_title));
        mViewPager.setAdapter(dashboardViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        mTabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    /**
     * mapped view
     */
    private void mapView() {
        mTabLayout = findViewById(R.id.home_tablayout);
        mViewPager = findViewById(R.id.home_viewpager);
    }
}
