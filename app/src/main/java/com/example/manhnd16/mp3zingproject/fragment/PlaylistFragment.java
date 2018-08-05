package com.example.manhnd16.mp3zingproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ListSongActivity;
import com.example.manhnd16.mp3zingproject.adapter.PlaylistAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac on 7/17/18.
 */

public class PlaylistFragment extends Fragment {
    private View mView;
    private ListView mListView;
    private TextView mTitlePlaylist, mLoadmorePlaylist;
    private PlaylistAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_playlist, container, false);
        mListView = mView.findViewById(R.id.playlist_listview);
        mTitlePlaylist = mView.findViewById(R.id.txt_title_playlist);
        mLoadmorePlaylist = mView.findViewById(R.id.loadmore_playlist_textview);
        getData();
        return mView;
    }

    /**
     * get data playlist from api
     */
        private void getData() {
            ServiceListener serviceListener = ApiService.getService();
            Call<List<PlayList>> callBack = serviceListener.getDataPlaylist();
            callBack.enqueue(new Callback<List<PlayList>>() {
                @Override
                public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                    final ArrayList<PlayList> playListArrayList = (ArrayList<PlayList>) response.body();
                    if (playListArrayList != null) {
                        mAdapter = new PlaylistAdapter(getActivity(), R.layout.playlist_row, playListArrayList);
                        mListView.setAdapter(mAdapter);
                        setListViewHeightBasedOnChildren(mListView);
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                Intent intent = new Intent(getActivity(), ListSongActivity.class);
                                Toast.makeText(getActivity(), "ABCABC", Toast.LENGTH_SHORT).show();
                                intent.putExtra(Constant.INTENT_NAME_PLAYLIST, playListArrayList.get(position));
                                startActivity(intent);
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<List<PlayList>> call, Throwable t) {
                }
            });
        }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
