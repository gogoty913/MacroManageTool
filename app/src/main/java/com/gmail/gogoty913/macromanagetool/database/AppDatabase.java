package com.gmail.gogoty913.macromanagetool.database;

import com.gmail.gogoty913.macromanagetool.dao.EatFoodsHistoryDao;
import com.gmail.gogoty913.macromanagetool.dao.FoodInfoDao;
import com.gmail.gogoty913.macromanagetool.dao.UserInfoDao;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.entity.UserInfo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {UserInfo.class, FoodInfo.class, EatFoodsHistory.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserInfoDao userInfoDao();
    public abstract FoodInfoDao foodInfoDao();
    public abstract EatFoodsHistoryDao EatFoodsHistoryDao();
}
