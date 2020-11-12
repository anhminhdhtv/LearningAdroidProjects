package com.example.learninglocalstorageapp;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class UserInfoRepository {
    private UserInfoDao mUserInfoDao;
    private List<UserInfo> mAllUserInfos;
    private Application mApplication;


    UserInfoRepository(Application application){
        this.mApplication = application;
    }

    List<UserInfo> getAllUserInfos(){
        return mAllUserInfos;
    }

    public void insert (UserInfo userInfo){
        new insertAsyncTask(UserDatabase.getDatabase(mApplication).userInfoDao()).execute(userInfo);
    }

    public void update (UserInfo userInfo){
        new updateAsyncTask(UserDatabase.getDatabase(mApplication).userInfoDao()).execute(userInfo);
    }

    public void delete (UserInfo userInfo){
        new deleteAsyncTask(UserDatabase.getDatabase(mApplication).userInfoDao()).execute(userInfo);
    }

    public List<UserInfo> getmAllUserInfos (){
        getAllUsersAsyncTask getAllUsersAsyncTask =  new getAllUsersAsyncTask(UserDatabase.getDatabase(mApplication).userInfoDao());
        return (List<UserInfo>) getAllUsersAsyncTask.execute();
    }

    public void deleteAllUsers (){
        new deleteAllUsersAsyncTask(UserDatabase.getDatabase(mApplication).userInfoDao()).execute();
    }

    private static class insertAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private UserInfoDao mUserInfoDao;

        public insertAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            mUserInfoDao.insert(userInfos[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private UserInfoDao mUserInfoDao;

        public updateAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            mUserInfoDao.update(userInfos[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private UserInfoDao mUserInfoDao;

        public deleteAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            mUserInfoDao.delete(userInfos[0]);
            return null;
        }
    }

    private static class deleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void>{

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

    private static class getAllUsersAsyncTask extends AsyncTask<Void, Void, List<UserInfo>>{

        private UserInfoDao mUserInfoDao;

        public getAllUsersAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }


        @Override
        protected List<UserInfo> doInBackground(Void... voids) {

            return mUserInfoDao.getAll();
        }
    }
}
