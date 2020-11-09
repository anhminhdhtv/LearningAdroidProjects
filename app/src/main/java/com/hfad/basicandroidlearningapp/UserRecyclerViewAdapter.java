package com.hfad.basicandroidlearningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserItemViewHolder> {

    private ArrayList<UserInfo> userInfos;

    public UserRecyclerViewAdapter(ArrayList<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_veiw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {

        UserInfo userInfo = userInfos.get(position);

        holder.textViewName.setText(userInfo.getUserName());
        holder.textViewDayOfBirth.setText(userInfo.getDayOfBirth());
        if (userInfo.isMan()){
            holder.imageViewAvatar.setImageResource(R.drawable.ic_man);
        }
        else {
            holder.imageViewAvatar.setImageResource(R.drawable.ic_woman);
        }

    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewAvatar;
        private TextView textViewName;
        private TextView textViewDayOfBirth;

        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);


            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(itemView.getLayoutParams());
            lp.setMargins(0,30,0,0);

            itemView.setLayoutParams(lp);

            imageViewAvatar = itemView.findViewById(R.id.image_avatar);
            textViewName = itemView.findViewById(R.id.text_view_user_name);
            textViewDayOfBirth = itemView.findViewById(R.id.text_view_date_of_birth);
        }
    }
}
