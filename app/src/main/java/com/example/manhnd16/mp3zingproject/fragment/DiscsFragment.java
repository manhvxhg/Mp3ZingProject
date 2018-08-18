package com.example.manhnd16.mp3zingproject.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.manhnd16.mp3zingproject.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiscsFragment extends Fragment {
    private CircleImageView mCircleImageView;
    private ObjectAnimator mObjectAnimator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discs, container, false);
        mCircleImageView = rootView.findViewById(R.id.discs_imageview_circle);
        mObjectAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0, 360);
        mObjectAnimator.setDuration(10000);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.start();
        return rootView;
    }

    /**
     * set image to circle imageview
     * @param songImage
     */
    public void playSong(String songImage) {
        Picasso.with(getActivity()).load(songImage).into(mCircleImageView);
    }
}
