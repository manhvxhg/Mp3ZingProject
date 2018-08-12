package com.example.manhnd16.mp3zingproject.service;

import com.example.manhnd16.mp3zingproject.model.Advertisement;
import com.example.manhnd16.mp3zingproject.model.Album;
import com.example.manhnd16.mp3zingproject.model.Kind;
import com.example.manhnd16.mp3zingproject.model.KindAndSubject;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.example.manhnd16.mp3zingproject.model.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("listsongads.php")
    Call<List<Song>> getListSong(@Field("idAds") String idAds);

    @FormUrlEncoded
    @POST("listsongads.php")
    Call<List<Song>> getListSongByPlaylist(@Field("idPlaylist") String idPlaylist);

    @FormUrlEncoded
    @POST("listsongads.php")
    Call<List<Song>> getListSongByKindSubject(@Field("idKind") String idKind);

    @GET("allplaylist.php")
    Call<List<PlayList>> getDataAllPlaylist();

    @GET("allsubject.php")
    Call<List<Subject>> getSubjects();

    @FormUrlEncoded
    @POST("kindarraybysubject.php")
    Call<List<Kind>> getListKindBySubject(@Field("idSubject") String idSubject);
}
