package com.example.manhnd16.mp3zingproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ListSongActivity;
import com.example.manhnd16.mp3zingproject.activity.ListSubjectActivity;
import com.example.manhnd16.mp3zingproject.activity.ViewAllPlaylistActivity;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Kind;
import com.example.manhnd16.mp3zingproject.model.KindAndSubject;
import com.example.manhnd16.mp3zingproject.model.Subject;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac on 7/21/18.
 */

public class SubjectAndKindFragment extends Fragment implements View.OnClickListener{
    private HorizontalScrollView mHorizontalScrollView;
    private TextView mLoadmoreSubjectAndKind;
    ArrayList<Kind> kindArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subject_kind, container, false);
        mHorizontalScrollView = rootView.findViewById(R.id.subject_and_kind_horizontal_scrollview);
        mLoadmoreSubjectAndKind = rootView.findViewById(R.id.loadmore_subject_and_kind_textview);
        mLoadmoreSubjectAndKind.setOnClickListener(this);
        getData();
        return rootView;
    }

    private void getData() {
        ServiceListener serviceListener = ApiService.getService();
        Call<KindAndSubject> callBack = serviceListener.getKindAndSubject();
        callBack.enqueue(new Callback<KindAndSubject>() {
            @Override
            public void onResponse(Call<KindAndSubject> call, Response<KindAndSubject> response) {
                KindAndSubject kindAndSubject = response.body();
                ArrayList<Subject> subjectArrayList = new ArrayList<>();
                subjectArrayList = (ArrayList<Subject>) kindAndSubject.getSubject();
                kindArrayList = (ArrayList<Kind>) kindAndSubject.getKind();
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < subjectArrayList.size(); i++) {
                    CardView  cardView =  new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView =  new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if ( subjectArrayList.get(i).getSubjectImage() != null) {
                        Picasso.with(getActivity()).load(subjectArrayList.get(i).getSubjectImage()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                for (int j = 0; j < kindArrayList.size(); j++) {
                    CardView  cardView =  new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView =  new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if ( kindArrayList.get(j).getKindImage() != null) {
                        Picasso.with(getActivity()).load(kindArrayList.get(j).getKindImage()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int position = j;
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), ListSongActivity.class);
                            intent.putExtra(Constant.INTENT_NAME_SUBJECT_AND_KIND, kindArrayList.get(position));
                            startActivity(intent);
                        }
                    });
                }
                mHorizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<KindAndSubject> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loadmore_subject_and_kind_textview:
                Intent intent = new Intent(getActivity(), ListSubjectActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
