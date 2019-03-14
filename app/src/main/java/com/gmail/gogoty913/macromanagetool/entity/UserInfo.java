package com.gmail.gogoty913.macromanagetool.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo
    public String nickname;

    /** 性別 0:New,1:female,2:male */
    @ColumnInfo
    public int style;

    /** 年齢 */
    @ColumnInfo
    public int age;

    /** 体重kg */
    @ColumnInfo
    public float weight;

    /** 身長cm */
    @ColumnInfo
    public float height;

    /** 基礎代謝量 */
    @ColumnInfo
    public float basalMetabolism;

    /** アクティブ度 0:low,1:normal, 2:height */
    @ColumnInfo
    public int activeMode;

    /** 目標 */
    @ColumnInfo
    public int future;

    /** 総カロリー */
    @ColumnInfo
    public float allCalorie;

    /** 目標炭水化物 */
    @ColumnInfo
    public float aimCarbohydrate;

    /**目標脂質*/
    @ColumnInfo
    public float aimLipid;

    /**目標たんぱく質*/
    @ColumnInfo
    public float aimProtein;

}
