package com.gmail.gogoty913.macromanagetool.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodInfo {

    /** バーコードID or オリジナルID */
    @PrimaryKey
    @NonNull
    public String barcodeId;

    /** true:barcode, false:original */
    @ColumnInfo
    public boolean idFlg;

    /** 食べ物名前 */
    @ColumnInfo
    public String foodName;

    /** カロリー　kcal */
    @ColumnInfo
    public double calorie;

    /** 脂質 */
    @ColumnInfo
    public double lipid;

    /** たんぱく質 */
    @ColumnInfo
    public double protein;

    /** 炭水化物 */
    @ColumnInfo
    public double carbohydrate;

    @ColumnInfo
    public double displayCapacity;

    @ColumnInfo
    public double allCapacity;

}