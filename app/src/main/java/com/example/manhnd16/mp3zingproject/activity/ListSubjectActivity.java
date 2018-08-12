package com.example.manhnd16.mp3zingproject.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.ListSongAdapter;
import com.example.manhnd16.mp3zingproject.adapter.SubjectsAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.example.manhnd16.mp3zingproject.model.Subject;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSubjectActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ProgressDialog mProgressDialog;
    private ArrayList<Subject> mSubjectArrayList;
    private SubjectsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_subject);
        init();
        callSubjectApi();
    }

    /**
     * Call API all subject
     */
    private void callSubjectApi() {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Subject>> callback = serviceListener.getSubjects();
        beforeCallApi(ListSubjectActivity.this);
        callback.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                mSubjectArrayList = (ArrayList<Subject>) response.body();
                mAdapter = new SubjectsAdapter(ListSubjectActivity.this, mSubjectArrayList);
                mRecyclerView.setLayoutManager(new GridLayoutManager(ListSubjectActivity.this, 1));
                mRecyclerView.setAdapter(mAdapter);
                afterCallApi();
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {

            }
        });
    }

    /**
     * init view
     */
    private void init() {
        mRecyclerView = findViewById(R.id.subject_list_recyclerview);
        mToolbar = findViewById(R.id.subject_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Constant.TITLE_SUBJECTS);
    }

    private void beforeCallApi(Context context) {
        // Set up progress before call
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        mProgressDialog.show();
    }

    private void afterCallApi() {
        mProgressDialog.dismiss();
    }
}
