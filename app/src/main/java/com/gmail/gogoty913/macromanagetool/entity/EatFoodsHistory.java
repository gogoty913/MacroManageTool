package com.gmail.gogoty913.macromanagetool.entity;

import java.sql.Time;
import java.time.LocalDateTime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"userId", "eatDay","eatTime"},
        indices = @Index("eatFoodId"))
public class EatFoodsHistory {

    @ColumnInfo
    @NonNull
    public int userId;

    /** yyyyMMdd*/
    @ColumnInfo
    @NonNull
    public String eatDay;

    /** HHmm */
    @ColumnInfo
    @NonNull
    public String eatTime;

    @ColumnInfo
    @NonNull
    public String eatFoodId;

    @ColumnInfo
    @Nullable
    public int eatValueGram;

    @ColumnInfo
    @Nullable
    public int eatValuePercent;
}
