package com.example.worldacademy.Fragments.Adapters;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.worldacademy.Fragments.Models.VideoModel;
import com.example.worldacademy.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class VideoAdapter extends Adapter<VideoAdapter.Adapter> {
    ArrayList<VideoModel> mModels;
    Context mContext;
    Uri mUri;


    public VideoAdapter(ArrayList<VideoModel> mModels, Context mContext) {
        this.mModels = mModels;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vedio_shap, parent, false);
        Adapter mAdapter = new Adapter(view);
        return mAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter holder, int position) {
        MediaController mediaController = new MediaController(mContext);
        mediaController.setAnchorView(holder.mVideoView);

        RelativeLayout.LayoutParams videoviewlp = new RelativeLayout.LayoutParams(400, 250);
        videoviewlp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        videoviewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        holder.mVideoView.setLayoutParams(videoviewlp);
        holder.mVideoView.invalidate();

        mUri = Uri.parse(mModels.get(position).getmLink());

        holder.mVideoView.setMediaController(mediaController);
        holder.mVideoView.setVideoURI(mUri);

        holder.mVideoView.start();

        holder.mDescribtion.setText(mModels.get(position).getmDescribtion());




    }



    @Override
    public int getItemCount() {
        return mModels.size();
    }

    //----------------------------------------------------------------------------------------------

    class Adapter extends RecyclerView.ViewHolder {
        VideoView mVideoView;
        TextView mDescribtion;

        public Adapter(@NonNull View itemView) {
            super(itemView);
            mDescribtion = itemView.findViewById(R.id.Describtion);
            mVideoView = itemView.findViewById(R.id.vedioview);
        }
    }

    //----------------------------------------------------------------------------------------------


}
