package com.example.manhnd16.mp3zingproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Parcelable{

    @SerializedName("song_id")
    @Expose
    private String songId;
    @SerializedName("song_name")
    @Expose
    private String songName;
    @SerializedName("song_image")
    @Expose
    private String songImage;
    @SerializedName("singer_name")
    @Expose
    private String singerName;
    @SerializedName("link_song")
    @Expose
    private String linkSong;
    @SerializedName("like_count")
    @Expose
    private String likeCount;

    protected Song(Parcel in) {
        songId = in.readString();
        songName = in.readString();
        songImage = in.readString();
        singerName = in.readString();
        linkSong = in.readString();
        likeCount = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(songId);
        parcel.writeString(songName);
        parcel.writeString(songImage);
        parcel.writeString(singerName);
        parcel.writeString(linkSong);
        parcel.writeString(likeCount);
    }
}
