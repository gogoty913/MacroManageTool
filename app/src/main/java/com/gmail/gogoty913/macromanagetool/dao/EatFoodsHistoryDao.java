package com.gmail.gogoty913.macromanagetool.dao;

import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EatFoodsHistoryDao {

    @Query("select * from EatFoodsHistory where eatDay = :eatDay")
    EatFoodsHistory selectEatFoodsHistoryByDay(String eatDay);

    @Query("select count(*) from eatfoodshistory where eatDay = :eatDay")
    long countByEatDay(String eatDay);

    @Query("select count(*) from eatfoodshistory")
    long countAll();

    @Insert
    void insertEatFoodsHistory(EatFoodsHistory eatFoodsHistory);

    @Query("delete from EatFoodsHistory where eatDay = :eatDay")
    void deleteEatFoodsHistoryByDay(String eatDay);

    @Query("delete from EatFoodsHistory where barcodeId = :barcodeId")
    void deleteEatFoodsHistoryByBarcodeId(String barcodeId);

    @Delete
    void deleteEatFoodsHistory(EatFoodsHistory eatFoodsHistory);
}
