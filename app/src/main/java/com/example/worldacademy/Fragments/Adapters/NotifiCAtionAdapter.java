package com.example.worldacademy.Fragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Models.HomeVerticalAdapter;
import com.example.worldacademy.Fragments.Models.NotificationModel;
import com.example.worldacademy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotifiCAtionAdapter extends RecyclerView.Adapter<NotifiCAtionAdapter.AdapterVertical> {
    ArrayList<NotificationModel> mItem;
    Context mContext;
    DatabaseReference mDatabaseReference;
    SessionMangment mangment;

    public NotifiCAtionAdapter(ArrayList<NotificationModel> mItem, Context mContext) {
        this.mItem = mItem;
        this.mContext = mContext;


    }


    public NotifiCAtionAdapter() {
    }

    @NonNull
    @Override
    public AdapterVertical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_shap, parent, false);
        NotifiCAtionAdapter.AdapterVertical adapter = new NotifiCAtionAdapter.AdapterVertical(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterVertical holder, final int position) {
        holder.mReport.setText(mItem.get(position).getmReport());



    }

    @Override
    public int getItemCount() {
        return (mItem == null) ? 0 : mItem.size();
    }

    class AdapterVertical extends RecyclerView.ViewHolder {


        TextView mReport;


        public AdapterVertical(@NonNull View itemView) {
            super(itemView);

            mReport = itemView.findViewById(R.id.Report);


        }
    }


}
