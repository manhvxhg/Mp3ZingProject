package com.example.manhnd16.mp3zingproject.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.KindsAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Kind;
import com.example.manhnd16.mp3zingproject.model.Subject;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKindBySubjectActivity extends AppCompatActivity {
    private Subject mSubject;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private ArrayList<Kind> mKindArrayList;
    private KindsAdapter mKindAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kind_by_subject);
        fetchIntentData();
        initView();
        callKindApi();
    }

    /**
     * call Kind api
     */
    private void callKindApi() {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Kind>> callback = serviceListener.getListKindBySubject(mSubject.getSubjectId());
        beforeCallApi(ListKindBySubjectActivity.this);
        callback.enqueue(new Callback<List<Kind>>() {
            @Override
            public void onResponse(Call<List<Kind>> call, Response<List<Kind>> response) {
                mKindArrayList = (ArrayList<Kind>) response.body();
                mKindAdapter = new KindsAdapter(ListKindBySubjectActivity.this, mKindArrayList);
                mRecyclerView.setLayoutManager(new GridLayoutManager(ListKindBySubjectActivity.this, 2));
                mRecyclerView.setAdapter(mKindAdapter);
                afterCallApi();
            }

            @Override
            public void onFailure(Call<List<Kind>> call, Throwable t) {

            }
        });
    }

    /**
     * Init view
     */
    private void initView() {
        mToolbar = findViewById(R.id.kind_by_subject_toolbar);
        mRecyclerView = findViewById(R.id.kind_by_subject_list_recyclerview);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(Constant.TITLE_KINDS);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Get data from intent
     */
    private void fetchIntentData() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.INTENT_NAME_KIND_BY_SUBJECT)) {
            mSubject = (Subject) intent.getSerializableExtra(Constant.INTENT_NAME_KIND_BY_SUBJECT);
        }
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
