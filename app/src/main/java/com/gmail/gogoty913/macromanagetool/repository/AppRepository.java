package com.gmail.gogoty913.macromanagetool.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.gmail.gogoty913.macromanagetool.AppAsyncTask;
import com.gmail.gogoty913.macromanagetool.database.AppDatabase;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.entity.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class AppRepository {
    private static final AppRepository ourInstance = new AppRepository();
    private AppDatabase db;
    private UserInfo userInfo;

    public static AppRepository getInstance(Context context) {
        if(ourInstance.getDb() == null) {
            ourInstance.setDb(context);
        }
        return ourInstance;
    }

    private AppRepository() {
    }

    private void setDb(Context context){
        db = Room.databaseBuilder(context,AppDatabase.class,"macro_manage_tool_db").build();
    }

    public AppDatabase getDb(){
        return db;
    }

    public void setUserInfo(UserInfo userInfo){this.userInfo =userInfo;}

    public UserInfo getUserInfo(){return userInfo;}


    public LiveData<List<EatFoodInfoHistory>> getEatFoodsHistoryList(String eatDay) {
        return db.eatFoodsHistoryDao().selectEatFoodsHistory(eatDay);
    }

    public LiveData<FoodInfo> getFoodInfo(String foodId){
        return db.foodInfoDao().selectFoodInfoLive(foodId);
    }
}
