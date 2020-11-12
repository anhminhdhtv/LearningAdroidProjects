package com.example.learninglocalstorageapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserItemViewHolder> {

    private List<UserInfo> mUserInfoList;
    private Context mContext;

    public UserInfoAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_veiw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {

        UserInfo userInfo = mUserInfoList.get(position);

        holder.textViewName.setText(userInfo.getUserName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
        String birthDay =  simpleDateFormat.format(userInfo.getDayOfBirth().getTime());

        holder.textViewDayOfBirth.setText(birthDay);
        if (userInfo.isMan()){
            holder.imageViewAvatar.setImageResource(R.drawable.ic_man);
        }
        else {
            holder.imageViewAvatar.setImageResource(R.drawable.ic_woman);
        }

        holder.textViewEmail.setText(userInfo.getUserName());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).delete(userInfo);
            }
        });


    }

    public void SetUserList(List<UserInfo> userInfoList){
        mUserInfoList = userInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mUserInfoList != null){
            return mUserInfoList.size();
        }
        else {
            return 0;
        }

    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewAvatar;
        private TextView textViewName;
        private TextView textViewDayOfBirth;
        private TextView textViewEmail;

        private ImageButton btnDelete;


        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(itemView.getLayoutParams());
            lp.setMargins(0,30,0,0);

            itemView.setLayoutParams(lp);

            imageViewAvatar = itemView.findViewById(R.id.image_avatar);
            textViewName = itemView.findViewById(R.id.text_view_user_name);
            textViewDayOfBirth = itemView.findViewById(R.id.text_view_date_of_birth);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
            btnDelete = itemView.findViewById(R.id.button_delete_user);

        }
    }
}
