package com.gmail.gogoty913.macromanagetool.dao;

import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public interface EatFoodsHistoryDao {

    @Query("select * from EatFoodsHistory where eatDay = :eatDay")
    EatFoodsHistory selectAllEatFoodsHistoryByDay(String eatDay);

    @Query("select count(*) from EatFoodsHistory where eatDay = :eatDay")
    long countByEatDay(String eatDay);

    @Query("select count(*) from EatFoodsHistory")
    long countAll();

    /** eatDay : yyyyMMdd */
    @Transaction
    @Query("select * "+
            "from EatFoodsHistory as history "+
            "inner join FoodInfo as info on history.eatFoodId = info.barcodeId " +
            "where history.eatDay = :eatDay " +
            "order by eatTime"
            )
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<EatFoodInfoHistory> selectEatFoodsHistoryByDayWithFoodsInfo(String eatDay);

    @Transaction
    @Query("select * "+
            "from EatFoodsHistory as history "+
            "inner join FoodInfo as info on history.eatFoodId = info.barcodeId " +
            "where history.eatDay = :eatDay " +
            "order by eatTime"
    )
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<EatFoodInfoHistory>> selectEatFoodsHistory(String eatDay);

    @Insert
    void insertEatFoodsHistory(EatFoodsHistory eatFoodsHistory);

    @Query("delete from EatFoodsHistory where eatDay <= :eatDay")
    void deleteEatFoodsHistoryByDay(String eatDay);

    @Query("delete from EatFoodsHistory where eatFoodId = :eatFoodId")
    void deleteEatFoodsHistoryByBarcodeId(String eatFoodId);

    @Delete
    void deleteEatFoodsHistory(EatFoodsHistory eatFoodsHistory);
}
