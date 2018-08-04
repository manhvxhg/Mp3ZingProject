package com.example.manhnd16.mp3zingproject.model;

/**
 * Created by mac on 7/1/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Advertisement implements Serializable{

    @SerializedName("advertisement_id")
    @Expose
    private String advertisementId;
    @SerializedName("advertisement_image")
    @Expose
    private String advertisementImage;
    @SerializedName("advertisement_content")
    @Expose
    private String advertisementContent;
    @SerializedName("song_id")
    @Expose
    private String songId;
    @SerializedName("song_name")
    @Expose
    private String songName;
    @SerializedName("song_image")
    @Expose
    private String songImage;

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getAdvertisementImage() {
        return advertisementImage;
    }

    public void setAdvertisementImage(String advertisementImage) {
        this.advertisementImage = advertisementImage;
    }

    public String getAdvertisementContent() {
        return advertisementContent;
    }

    public void setAdvertisementContent(String advertisementContent) {
        this.advertisementContent = advertisementContent;
    }

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

}
