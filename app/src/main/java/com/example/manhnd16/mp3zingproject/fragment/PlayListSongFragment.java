package com.example.manhnd16.mp3zingproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.PlaySongAdapter;
import com.example.manhnd16.mp3zingproject.listener.FragmentCommunicator;
import com.example.manhnd16.mp3zingproject.model.Song;

import java.util.ArrayList;

public class PlayListSongFragment extends Fragment implements FragmentCommunicator{
    private RecyclerView mRecyclerView;
    private static ArrayList<Song> mSongArrayList =  new ArrayList<>();
    private PlaySongAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play_list_song, container, false);
        referView(rootView);
        if (mSongArrayList != null) {
            mAdapter = new PlaySongAdapter(getActivity(), mSongArrayList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mAdapter);
        }
        return rootView;
    }

    private void referView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.playlist_song_recyclerview);
    }

    @Override
    public void passDataToFragment(ArrayList<Song> listSong) {
        mSongArrayList = listSong;
    }
}
