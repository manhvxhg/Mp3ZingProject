package com.example.manhnd16.mp3zingproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.SearchItemAdapter;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.LoadingDisplay;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac on 6/30/18.
 */

public class SearchFragment extends Fragment {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private TextView mNotResultTxt;
    SearchItemAdapter mSearchItemAdapter;
    private ArrayList<Song> mSongArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mToolbar = rootView.findViewById(R.id.search_toobar);
        mRecyclerView = rootView.findViewById(R.id.search_result_recyclerview);
        mNotResultTxt = rootView.findViewById(R.id.search_not_data_textview);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchKeywordSong(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchKeywordSong (String keyword) {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Song>> callback = serviceListener.getSearchSong(keyword);
        beforeCallApi();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mSongArrayList = (ArrayList<Song>) response.body();
                if (mSongArrayList.size() > 0) {
                    mSearchItemAdapter = new SearchItemAdapter(getActivity(), mSongArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setAdapter(mSearchItemAdapter);
                    mNotResultTxt.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    mNotResultTxt.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
                afterCallApi();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }
    protected void beforeCallApi() {
        if (getActivity() instanceof LoadingDisplay) {
            ((LoadingDisplay) getActivity()).beforeCallApi();
        }
    }

    protected void afterCallApi() {
        if (getActivity() instanceof LoadingDisplay) {
            ((LoadingDisplay) getActivity()).afterCallApi();
        }
    }
}
