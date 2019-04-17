package com.gmail.gogoty913.macromanagetool.dao;

import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FoodInfoDao {

    @Query("SELECT * FROM foodInfo where barcodeId = :barcodeId")
    FoodInfo selectFoodInfo(String barcodeId);

    @Query("SELECT * FROM foodInfo where barcodeId = :barcodeId")
    LiveData<FoodInfo> selectFoodInfoLive(String barcodeId);

    @Query("SELECT * FROM foodInfo")
    LiveData<List<FoodInfo>> selectFoodInfoList();


    @Insert
    void InsertFoodsInfo(FoodInfo... foodInfo);

    @Delete
    void deleteFoodInfo(FoodInfo foodInfo);
}
