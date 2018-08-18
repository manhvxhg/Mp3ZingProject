package com.example.manhnd16.mp3zingproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.PlayListSongViewPagerAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.fragment.DiscsFragment;
import com.example.manhnd16.mp3zingproject.fragment.PlayListSongFragment;
import com.example.manhnd16.mp3zingproject.listener.FragmentCommunicator;
import com.example.manhnd16.mp3zingproject.model.Song;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private Song mSong;
    private ArrayList<Song> mSongArrayList;
    private TextView mTimeSong, mTotalTimeSong;
    private SeekBar mSeekBar;
    private ViewPager mViewPager;
    private ImageButton mSuffleBtn, mPlayBtn, mNextBtn, mPreviewBtn, mRepeatBtn;
    public FragmentCommunicator mFragmentCommunicator;
    public PlayListSongViewPagerAdapter mViewPagerAdapter;
    public ArrayList<Fragment> mFragmentArrayList;
    private MediaPlayer mMediaPlayer;
    private DiscsFragment mDiscsFragment;
    private int mPosition = 0;
    private boolean isRepeat = false;
    private boolean isRandom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        fetchIntent();
        initView();
        eventClick();
    }

    /**
     * Event click
     */
    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mViewPagerAdapter.getItem(1) != null) {
                    if (mSongArrayList.size() > 0) {
                        mDiscsFragment.playSong(mSongArrayList.get(0).getSongImage());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        mPlayBtn.setOnClickListener(this);
        mPreviewBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mRepeatBtn.setOnClickListener(this);
        mSuffleBtn.setOnClickListener(this);
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
                mMediaPlayer.stop();
                mSongArrayList.clear();
            }
        });
        mToolbar.setTitleTextColor(Color.WHITE);

        mTimeSong = findViewById(R.id.play_music_time_song_textview);
        mTotalTimeSong = findViewById(R.id.play_music_total_time_song_textview);
        mSeekBar = findViewById(R.id.play_music_time_seek_bar);
        mSuffleBtn = findViewById(R.id.play_music_shuffle_button);
        mRepeatBtn = findViewById(R.id.play_music_repeat_button);
        mPreviewBtn = findViewById(R.id.play_music_preview_button);
        mPlayBtn = findViewById(R.id.play_music_play_button);
        mNextBtn = findViewById(R.id.play_music_next_button);
        mViewPager = findViewById(R.id.play_music_viewpager);
        setUpViewPager();
        mDiscsFragment = (DiscsFragment) mViewPagerAdapter.getItem(1);
        if (mSongArrayList != null) {
            getSupportActionBar().setTitle(mSongArrayList.get(0).getSongName());
            new PlayMp3().execute(mSongArrayList.get(0).getLinkSong());
            mPlayBtn.setImageResource(R.drawable.iconpause);
        }
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMediaPlayer.seekTo(mSeekBar.getProgress());
            }
        });
    }

    private void setUpViewPager() {
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(new PlayListSongFragment());
        mFragmentArrayList.add(new DiscsFragment());
        mViewPagerAdapter = new PlayListSongViewPagerAdapter(getSupportFragmentManager(), mFragmentArrayList);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    /**
     * get data from intent
     */
    private void fetchIntent() {
        mFragmentCommunicator = new PlayListSongFragment();
        Intent intent = getIntent();
        if (intent != null) {
            mSongArrayList = new ArrayList<>();
            if (intent.hasExtra(Constant.INTENT_SONG)) {
                mSong = intent.getParcelableExtra(Constant.INTENT_SONG);
                mSongArrayList.add(mSong);
                mFragmentCommunicator.passDataToFragment(mSongArrayList);
            }
            if (intent.hasExtra(Constant.INTENT_LIST_SONG)) {
                mSongArrayList = intent.getParcelableArrayListExtra(Constant.INTENT_LIST_SONG);
                mFragmentCommunicator.passDataToFragment(mSongArrayList);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_music_play_button:
                onClickPlay();
                break;
            case R.id.play_music_repeat_button:
                onClickRepeat();
                break;
            case R.id.play_music_shuffle_button:
                onClickSuffle();
                break;
            case R.id.play_music_next_button:
                onClickNext();
                break;
            case R.id.play_music_preview_button:
                onClickPreview();
                break;
            default:
                break;
        }
    }

    /**
     * processing tap next button
     */
    private void onClickNext() {
        if (mSongArrayList != null) {
            if (mMediaPlayer.isPlaying() || mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
            if (mPosition < mSongArrayList.size()) {
                mPlayBtn.setImageResource(R.drawable.iconpause);
                mPosition++;
                if (isRepeat) {
                    if (mPosition == 0) {
                        mPosition = mSongArrayList.size();
                    }
                    mPosition -= 1;
                }
                if (isRandom) {
                    Random random = new Random();
                    int index = random.nextInt(mSongArrayList.size());
                    if (index == mPosition) {
                        mPosition = index - 1;
                    }
                    mPosition = index;
                }
                if (mPosition > mSongArrayList.size() - 1) {
                    mPosition = 0;
                }
                new PlayMp3().execute(mSongArrayList.get(mPosition).getLinkSong());
                mDiscsFragment.playSong(mSongArrayList.get(mPosition).getSongImage());
                getSupportActionBar().setTitle(mSongArrayList.get(mPosition).getSongName());
            }
            mPreviewBtn.setClickable(false);
            mNextBtn.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPreviewBtn.setClickable(true);
                    mNextBtn.setClickable(true);
                }
            }, 3000);
        }
    }

    /**
     * processing tap preview button
     */
    private void onClickPreview() {
        if (mSongArrayList != null) {
            if (mMediaPlayer.isPlaying() || mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
            if (mPosition < mSongArrayList.size()) {
                mPlayBtn.setImageResource(R.drawable.iconpause);
                mPosition--;
                if (mPosition < 0) {
                    mPosition = mSongArrayList.size() - 1;
                }
                if (isRepeat) {
                    if (mPosition == 0) {
                        mPosition = mSongArrayList.size();
                    }
                    mPosition += 1;
                }
                if (isRandom) {
                    Random random = new Random();
                    int index = random.nextInt(mSongArrayList.size());
                    if (index == mPosition) {
                        mPosition = index - 1;
                    }
                    mPosition = index;
                }

                new PlayMp3().execute(mSongArrayList.get(mPosition).getLinkSong());
                mDiscsFragment.playSong(mSongArrayList.get(mPosition).getSongImage());
                getSupportActionBar().setTitle(mSongArrayList.get(mPosition).getSongName());
            }
            mPreviewBtn.setClickable(false);
            mNextBtn.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPreviewBtn.setClickable(true);
                    mNextBtn.setClickable(true);
                }
            }, 3000);
        }
    }

    /**
     * processing tap suffle button
     */
    private void onClickSuffle() {
        if (!isRandom) {
            if (isRepeat) {
                isRepeat = false;
                mSuffleBtn.setImageResource(R.drawable.iconshuffled);
                mRepeatBtn.setImageResource(R.drawable.iconrepeat);
            }
            mSuffleBtn.setImageResource(R.drawable.iconshuffled);
            isRandom = true;
        } else {
            mSuffleBtn.setImageResource(R.drawable.iconsuffle);
            isRandom = false;
        }
    }

    /**
     * processing tap repeat button
     */
    private void onClickRepeat() {
        if (!isRepeat) {
            if (isRandom) {
                isRandom = false;
                mRepeatBtn.setImageResource(R.drawable.iconsyned);
                mSuffleBtn.setImageResource(R.drawable.iconsuffle);
            }
            mRepeatBtn.setImageResource(R.drawable.iconsyned);
            isRepeat = true;
        } else {
            mRepeatBtn.setImageResource(R.drawable.iconrepeat);
            isRepeat = false;
        }
    }

    /**
     * play music
     */
    private void onClickPlay() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayBtn.setImageResource(R.drawable.iconplay);
        } else {
            mMediaPlayer.start();
            mPlayBtn.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mMediaPlayer.stop();
                        mMediaPlayer.reset();
                    }
                });
                mMediaPlayer.setDataSource(song);
                mMediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.start();
            timeSong();
        }

        @Override
        protected String doInBackground(String... strings) {

            return strings[0];
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        mTotalTimeSong.setText(simpleDateFormat.format(mMediaPlayer.getDuration()));
        mSeekBar.setMax(mMediaPlayer.getDuration());
    }
}
