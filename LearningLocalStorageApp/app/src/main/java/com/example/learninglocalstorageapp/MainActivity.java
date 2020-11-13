package com.example.learninglocalstorageapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  UserInfoAdapter userInfoAdapter;
    public static final int REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnAddUser = findViewById(R.id.button_add_user);

        recyclerView = findViewById(R.id.recycler_view_user);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                intent.putExtra(UserFormActivity.EXTRA_START_MODE, UserFormActivity.VALUE_START_MODE_INSERT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        userInfoAdapter = new UserInfoAdapter(this);
        recyclerView.setAdapter(userInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getAllUserInfoList();
    }

    public void delete (UserInfo userInfo){
        new deleteAsyncTask(UserDatabase.getDatabase(getApplication()).userInfoDao()).execute(userInfo);
    }

    private class deleteAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private UserInfoDao mUserInfoDao;

        public deleteAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getAllUserInfoList();
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            mUserInfoDao.delete(userInfos[0]);
            return null;
        }
    }

    private void getAllUserInfoList(){
        new getAllUsersAsyncTask(UserDatabase.getDatabase(getApplication()).userInfoDao(),this).execute();
    }

    private class getAllUsersAsyncTask extends AsyncTask<Void, Void, List<UserInfo>>{

        private UserInfoDao mUserInfoDao;
        private Context mContext;

        public getAllUsersAsyncTask(UserInfoDao dao, Context context) {
            this.mUserInfoDao = dao;
            this.mContext = context;

        }

        @Override
        protected void onPostExecute(List<UserInfo> userInfos) {
            super.onPostExecute(userInfos);
            userInfoAdapter.SetUserList(userInfos);
        }

        @Override
        protected List<UserInfo> doInBackground(Void... voids) {
            return mUserInfoDao.getAll();
        }
    }

    private void deleteAllUsers (){
        new deleteAllUsersAsyncTask(UserDatabase.getDatabase(getApplication()).userInfoDao()).execute();
    }

    private class deleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void>{

        private UserInfoDao mUserInfoDao;

        public deleteAllUsersAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserInfoDao.deleteAll();
            return null;
        }
    }

    public void updateUser(int  userId){
        Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
        intent.putExtra(UserFormActivity.EXTRA_START_MODE, UserFormActivity.VALUE_START_MODE_UPDATE);
        intent.putExtra(UserFormActivity.EXTRA_UPDATED_USER_ID, userId);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            getAllUserInfoList();
        }
    }
}