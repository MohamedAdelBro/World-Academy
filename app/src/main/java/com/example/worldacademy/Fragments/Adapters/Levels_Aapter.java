package com.example.worldacademy.Fragments.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.worldacademy.Activities.Levels;
import com.example.worldacademy.Activities.PaymentMethodActivity;
import com.example.worldacademy.Activities.Video;
import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Models.LevelsModel;
import com.example.worldacademy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.worldacademy.Common.SessionMangment.KEY_ID;


public class Levels_Aapter extends RecyclerView.Adapter<Levels_Aapter.LevelsAdapter> {
    ArrayList<LevelsModel> mModel;
    Context mContext;
    static String mPrice;
    DatabaseReference mDatabaseReference;
     boolean mFlage;
    SessionMangment mangment;



    public Levels_Aapter(ArrayList<LevelsModel> mModel, Context mContext) {
        this.mModel = mModel;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LevelsAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.floder_shap, parent, false);
        LevelsAdapter adapter = new LevelsAdapter(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull LevelsAdapter holder, final int position) {
        holder.mPrice.setText("Price : " + mModel.get(position).getmPrice());
        mPrice = mModel.get(position).getmPrice();
        holder.mNumberOfLevels.setText("Level Number : " + mModel.get(position).getmNumberOfLevels());


        Glide.with(mContext).load(mModel.get(position).getmImage()).into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Check(position);


            }
        });
    }


    @Override
    public int getItemCount() {
        return mModel.size();
    }


    //----------------------------------------------------------------------------------------------

    class LevelsAdapter extends RecyclerView.ViewHolder {
        TextView mNumberOfLevels, mPrice;
        ImageView mImageView;

        public LevelsAdapter(@NonNull View itemView) {
            super(itemView);

            mNumberOfLevels = itemView.findViewById(R.id.LevelNumber);
            mPrice = itemView.findViewById(R.id.Price);
            mImageView = itemView.findViewById(R.id.LevelCover);
        }
    }

    //----------------------------------------------------------------------------------------------


    private void Check(final int position) {

        mangment = new SessionMangment(mContext);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Courses").child(Levels.mItemId).child("Folders").child(mModel.get(position).getmName()).child("Users Who Bought");
        ref.orderByChild("mId").equalTo(mangment.getUserDetails().get(KEY_ID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                mFlage = false;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("mId").getValue(String.class).equalsIgnoreCase(mangment.getUserDetails().get(KEY_ID))) {
                        mFlage = true;
                        break;
                    }
                }

                BuyCourse(position);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void BuyCourse(int position) {
        Log.e("ddddddddddddddddddddddddddddddddd", String.valueOf(mFlage));
        if (mFlage) {
            Intent mIntent = new Intent(mContext, Video.class);
            mIntent.putExtra("Name", mModel.get(position).getmName());
            mContext.startActivity(mIntent);

        }

        if (!mFlage) {
            Intent mIntent2 = new Intent(mContext, PaymentMethodActivity.class);
            mIntent2.putExtra("Price", mModel.get(position).getmPrice());
            mIntent2.putExtra("Name", mModel.get(position).getmName());
            mContext.startActivity(mIntent2);

        }
    }


}
