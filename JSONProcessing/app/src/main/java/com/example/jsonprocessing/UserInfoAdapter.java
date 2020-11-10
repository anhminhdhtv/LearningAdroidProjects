package com.example.jsonprocessing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserItemViewHolder> {

    private ArrayList<UserInfo> userInfos;

    public UserInfoAdapter(ArrayList<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewAge;
        public TextView textViewEmail;

        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_user_name);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
        }
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_veiw, parent, false);

        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {

        holder.textViewName.setText("User name: " + userInfos.get(position).getUserName());
        holder.textViewAge.setText("Age: " + String.valueOf(userInfos.get(position).getUserAge()));
        holder.textViewEmail.setText("Email: " + userInfos.get(position).getUserEmail());
    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }
}
