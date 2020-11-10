package com.hfad.basicandroidlearningapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserListViewAdapter extends BaseAdapter {

    private ArrayList<UserInfo> listUser;

    public UserListViewAdapter(ArrayList<UserInfo> listUser) {
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if(convertView == null){
            view = View.inflate(parent.getContext(), R.layout.user_item_veiw, null);
        }
        else {
            view = convertView;
        }

        UserInfo userInfo = (UserInfo) getItem(position);
        ((TextView)view.findViewById(R.id.text_view_user_name)).setText(userInfo.getUserName());
        ((TextView)view.findViewById(R.id.text_view_date_of_birth)).setText(userInfo.getDayOfBirth());
        ((TextView)view.findViewById(R.id.text_view_email)).setText(userInfo.getUserEmail());

        if(userInfo.isMan()){
            ((ImageView)view.findViewById(R.id.image_avatar)).setImageResource(R.drawable.ic_man);

        }
        else {
            ((ImageView)view.findViewById(R.id.image_avatar)).setImageResource(R.drawable.ic_woman);
        }

        return view;
    }
}
