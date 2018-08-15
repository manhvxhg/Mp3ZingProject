package com.example.manhnd16.mp3zingproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Song;

import java.util.ArrayList;
import java.util.List;

public class PlayMusicActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Song mSong;
    private ArrayList<Song> mSongArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        fetchIntent();
        initView();
    }

    /**
     * init view
     */
    private void initView() {
        mToolbar = findViewById(R.id.play_music_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mSong != null) {
            getSupportActionBar().setTitle(mSong.getSongName());
        } else {
            getSupportActionBar().setTitle("List Song");
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * get data from intent
     */
    private void fetchIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.INTENT_SONG)) {
            mSong = intent.getParcelableExtra(Constant.INTENT_SONG);
        }
        if (intent.hasExtra(Constant.INTENT_LIST_SONG)) {
            mSongArrayList = intent.getParcelableArrayListExtra(Constant.INTENT_LIST_SONG);
        }
    }

}
