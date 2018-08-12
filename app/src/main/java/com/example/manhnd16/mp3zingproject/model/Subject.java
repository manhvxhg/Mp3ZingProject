package com.example.manhnd16.mp3zingproject.model;

/**
 * Created by mac on 7/20/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subject implements Serializable {

    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("subject_image")
    @Expose
    private String subjectImage;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectImage() {
        return subjectImage;
    }

    public void setSubjectImage(String subjectImage) {
        this.subjectImage = subjectImage;
    }

}
