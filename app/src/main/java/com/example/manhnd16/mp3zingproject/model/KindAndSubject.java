package com.example.manhnd16.mp3zingproject.model;

/**
 * Created by mac on 7/20/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KindAndSubject {

    @SerializedName("Kind")
    @Expose
    private List<Kind> kind = null;
    @SerializedName("Subject")
    @Expose
    private List<Subject> subject = null;

    public List<Kind> getKind() {
        return kind;
    }

    public void setKind(List<Kind> kind) {
        this.kind = kind;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

}