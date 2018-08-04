package com.example.manhnd16.mp3zingproject.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Advertisement;

public class ListSongActivity extends AppCompatActivity {
    private Advertisement mAdvertisement;
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        fetchDataIntent();
//        referView();
    }

    /**
     * Reference view
     */
    private void referView() {
        mCoordinatorLayout = findViewById(R.id.list_song_coordinator_layout);
        mCollapsingToolbarLayout = findViewById(R.id.list_song_collapsing_toolbar_layout);
        mToolbar = findViewById(R.id.list_song_toolbar);
        mRecyclerView = findViewById(R.id.list_song_recyclerview);
        mFloatingActionButton = findViewById(R.id.list_song_floating_action_button);
    }

    /**
     * Get data from intent
     */
    private void fetchDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constant.INTENT_NAME_BANNER)) {
                mAdvertisement = (Advertisement) intent.getSerializableExtra(Constant.INTENT_NAME_BANNER);
            }
        }
    }
}
