package com.example.manhnd16.mp3zingproject.model;

/**
 * Created by mac on 7/20/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kind implements Serializable{

    @SerializedName("kind_id")
    @Expose
    private String kindId;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("kind_name")
    @Expose
    private String kindName;
    @SerializedName("kind_image")
    @Expose
    private String kindImage;

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getKindImage() {
        return kindImage;
    }

    public void setKindImage(String kindImage) {
        this.kindImage = kindImage;
    }

}
