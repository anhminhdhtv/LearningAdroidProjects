package com.example.learninglocalstorageapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserInfo.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserInfoDao userInfoDao();

    private static UserDatabase INSTANCE;

    static UserDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_table").build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final UserInfoDao mUserInfoDao;

        public PopulateDbAsync(UserDatabase db) {
            this.mUserInfoDao = db.userInfoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            mUserInfoDao.deleteAll();
            return null;
        }
    }
}
