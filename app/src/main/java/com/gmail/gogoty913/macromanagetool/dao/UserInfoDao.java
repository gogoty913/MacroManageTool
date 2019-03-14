package com.gmail.gogoty913.macromanagetool.dao;

import com.gmail.gogoty913.macromanagetool.entity.UserInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserInfoDao {

    @Query("select * from UserInfo")
    List<UserInfo> selectAll();

//    @Query("update userinfo set")
//    void updateUserInf(UserInfo userInfo);

    @Insert
    void insertUserInfo(UserInfo userInfo);

    @Delete
    void deleteUserInfo(UserInfo userInfo);
}
