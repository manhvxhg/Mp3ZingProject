package com.example.manhnd16.mp3zingproject.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ViewAllPlaylistActivity;
import com.example.manhnd16.mp3zingproject.adapter.SongHotAdapter;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.LoadingDisplay;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongHotFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SongHotAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_song_hot, container, false);
        mRecyclerView = rootView.findViewById(R.id.song_hot_recyclerview);
        getData();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    /**
     * get data playlist from api
     */
    private void getData() {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Song>> callBack = serviceListener.getSong();
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
                mAdapter = new SongHotAdapter(getActivity(), songArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                afterCallApi();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                afterCallApi();
            }
        });

    }

    protected void beforeCallApi() {
        if (getActivity() instanceof LoadingDisplay) {
            ((LoadingDisplay) getActivity()).beforeCallApi();
        }
    }

    protected void afterCallApi() {
        if (getActivity() instanceof LoadingDisplay) {
            ((LoadingDisplay) getActivity()).afterCallApi();
        }
    }
}
