package com.example.manhnd16.mp3zingproject.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.DashboardViewPagerAdapter;
import com.example.manhnd16.mp3zingproject.fragment.HomeFragment;
import com.example.manhnd16.mp3zingproject.fragment.SearchFragment;
import com.example.manhnd16.mp3zingproject.service.LoadingDisplay;

public class MainActivity extends AppCompatActivity implements LoadingDisplay{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ProgressDialog mProgressDialog;
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

    @Override
    public void beforeCallApi() {
        // Set up progress before call
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        mProgressDialog.show();
    }

    @Override
    public void afterCallApi() {
        mProgressDialog.dismiss();
    }
}
