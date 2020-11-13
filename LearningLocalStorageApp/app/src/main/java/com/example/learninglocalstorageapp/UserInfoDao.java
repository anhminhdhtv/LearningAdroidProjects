package com.example.learninglocalstorageapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserInfoDao {

    @Query("SELECT * FROM user_table")
    public List<UserInfo> getAll();

    @Query("SELECT * FROM user_table WHERE id LIKE :id ")
    public UserInfo findById(int id);

    @Insert()
    public void insert(UserInfo userInfo);

    @Delete
    public void delete(UserInfo userInfo);

    @Update
    public void update(UserInfo userInfo);

    @Query("DELETE FROM user_table")
    void deleteAll();
}
