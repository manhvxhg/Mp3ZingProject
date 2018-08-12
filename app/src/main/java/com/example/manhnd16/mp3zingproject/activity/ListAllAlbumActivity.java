package com.example.manhnd16.mp3zingproject.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.AlbumsAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Album;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAllAlbumActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private ArrayList<Album> mAlbumArrayList;
    private AlbumsAdapter mAlbumsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_album);
        initView();
        callAlbumsApi();
    }

    /**
     * Call album api
     */
    private void callAlbumsApi() {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Album>> callback = serviceListener.getAlbums();
        beforeCallApi(ListAllAlbumActivity.this);
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                mAlbumArrayList = (ArrayList<Album>) response.body();
                mAlbumsAdapter = new AlbumsAdapter(ListAllAlbumActivity.this, mAlbumArrayList);
                mRecyclerView.setLayoutManager(new GridLayoutManager(ListAllAlbumActivity.this, 2));
                mRecyclerView.setAdapter(mAlbumsAdapter);
                afterCallApi();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                afterCallApi();
            }
        });
    }

    /**
     * init view
     */
    private void initView() {
        mToolbar = findViewById(R.id.all_album_toolbar);
        mRecyclerView = findViewById(R.id.all_album_recycler_view);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Constant.TITLE_ALBUMS);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void beforeCallApi(Context context) {
        // Set up progress before call
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        mProgressDialog.show();
    }

    private void afterCallApi() {
        mProgressDialog.dismiss();
    }
}
