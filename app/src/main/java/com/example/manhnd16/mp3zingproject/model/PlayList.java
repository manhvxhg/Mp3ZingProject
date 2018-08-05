package com.example.manhnd16.mp3zingproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mac on 7/16/18.
 */

public class PlayList implements Serializable {

    @SerializedName("playlist_id")
    @Expose
    private String playlistId;
    @SerializedName("playlist_name")
    @Expose
    private String playlistName;
    @SerializedName("playlist_image")
    @Expose
    private String playlistImage;
    @SerializedName("playlist_icon")
    @Expose
    private String playlistIcon;

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistImage() {
        return playlistImage;
    }

    public void setPlaylistImage(String playlistImage) {
        this.playlistImage = playlistImage;
    }

    public String getPlaylistIcon() {
        return playlistIcon;
    }

    public void setPlaylistIcon(String playlistIcon) {
        this.playlistIcon = playlistIcon;
    }

}
