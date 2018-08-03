package com.example.manhnd16.mp3zingproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.BannerAdapter;
import com.example.manhnd16.mp3zingproject.model.Advertisement;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac on 7/1/18.
 */

public class BannerFragment extends Fragment {
    View rootView;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private BannerAdapter mBannerAdapter;
    private Runnable mRunnable;
    private Handler mHandler;
    private int mCurrentItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_banner, container, false);
        mapping();
        getData();
        return rootView;
    }

    private void mapping() {
        mViewPager = rootView.findViewById(R.id.banner_viewpager);
        mCircleIndicator = rootView.findViewById(R.id.indicator_banner);
    }

    /**
     * get Data from API
     */
    private void getData() {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Advertisement>> callBack = serviceListener.getDataBanner();
        callBack.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                ArrayList<Advertisement> banners = (ArrayList<Advertisement>) response.body();
                if (banners != null) {
                    mBannerAdapter = new BannerAdapter(getActivity(), banners);

                }
                mViewPager.setAdapter(mBannerAdapter);
                mCircleIndicator.setViewPager(mViewPager);
                mHandler = new Handler();
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mCurrentItem = mViewPager.getCurrentItem();
                        mCurrentItem++;
                        if ((mCurrentItem >= mViewPager.getAdapter().getCount())) {
                            mCurrentItem = 0;
                        }
                        mViewPager.setCurrentItem(mCurrentItem, true);
                        mHandler.postDelayed(mRunnable, 4500);
                    }
                };
                mHandler.postDelayed(mRunnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {
            }
        });
    }
}
