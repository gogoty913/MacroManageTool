package com.gmail.gogoty913.macromanagetool.entity;

import java.sql.Time;
import java.time.LocalDateTime;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"userId", "eatDay","eatTime"})
public class EatFoodsHistory {

    @ColumnInfo
    @NonNull
    public String userId;

    /** yyyyMMdd*/
    @ColumnInfo
    @NonNull
    public String eatDay;

    /** HHmm */
    @ColumnInfo
    @NonNull
    public String eatTime;

    @ColumnInfo
    public String barcodeId;

    @ColumnInfo
    public int eatVolume;

    @ColumnInfo
    public int eatPercent;

}
