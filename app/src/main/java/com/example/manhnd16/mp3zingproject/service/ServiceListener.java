package com.example.manhnd16.mp3zingproject.service;

import com.example.manhnd16.mp3zingproject.model.Advertisement;
import com.example.manhnd16.mp3zingproject.model.Album;
import com.example.manhnd16.mp3zingproject.model.KindAndSubject;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.example.manhnd16.mp3zingproject.model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mac on 7/1/18.
 */

public interface ServiceListener {
    @GET("songbanner.php")
    Call<List<Advertisement>> getDataBanner();

    @GET("playlistforcurrent.php")
    Call<List<PlayList>> getDataPlaylist();

    @GET("chudevatheloai.php")
    Call<KindAndSubject> getKindAndSubject();

    @GET("album.php")
    Call<List<Album>> getAlbum();

    @GET("song.php")
    Call<List<Song>> getSong();
}
