package com.example.manhnd16.mp3zingproject.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.AllPlaylistAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllPlaylistActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<PlayList> mPlayListArrayList;
    private AllPlaylistAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_playlist);
        referView();
        initView();
        getData();
    }

    private void referView() {
        mToolbar = findViewById(R.id.all_playlist_toolbar);
        mRecyclerView = findViewById(R.id.all_playlist_recycler_view);
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Constant.TITLE_PLAYLISTS);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorTint));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * get data from api
     */
    private void getData() {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<PlayList>> callback = serviceListener.getDataAllPlaylist();
        beforeCallApi(ViewAllPlaylistActivity.this);
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mPlayListArrayList = (ArrayList<PlayList>) response.body();
                mAdapter = new AllPlaylistAdapter(ViewAllPlaylistActivity.this, mPlayListArrayList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ViewAllPlaylistActivity.this, 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                afterCallApi();
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {
                afterCallApi();
                Log.d("TAG", "onFailure: ");
            }
        });
    }

    private void beforeCallApi(Context context) {
        // Set up progress before call
        mProgressDialog = new ProgressDialog(ViewAllPlaylistActivity.this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        mProgressDialog.show();
    }

    private void afterCallApi() {
        mProgressDialog.dismiss();
    }
}
