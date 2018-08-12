package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ListKindBySubjectActivity;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Subject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Subject> mSubjectArrayList;

    public SubjectsAdapter(Context mContext, ArrayList<Subject> subjectArrayList) {
        this.mContext = mContext;
        this.mSubjectArrayList = subjectArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.all_subject_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subject subject = mSubjectArrayList.get(position);
        Picasso.with(mContext).load(subject.getSubjectImage()).into(holder.subjectImage);
    }

    @Override
    public int getItemCount() {
        return mSubjectArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView subjectImage;
        public ViewHolder(View itemView) {
            super(itemView);
            subjectImage = itemView.findViewById(R.id.subject_imageview_row);
            subjectImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(mContext, ListKindBySubjectActivity.class);
            intent.putExtra(Constant.INTENT_NAME_KIND_BY_SUBJECT, mSubjectArrayList.get(position));
            mContext.startActivity(intent);
        }
    }
}
