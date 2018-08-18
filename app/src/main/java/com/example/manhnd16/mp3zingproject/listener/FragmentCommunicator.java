package com.example.manhnd16.mp3zingproject.listener;

import com.example.manhnd16.mp3zingproject.model.Song;

import java.util.ArrayList;

public interface FragmentCommunicator {
    public void passDataToFragment(ArrayList<Song> listSong);
}
