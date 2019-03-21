package com.gmail.gogoty913.macromanagetool.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EatFoodInfoHistory {

    @Embedded
    public FoodInfo foodInfo;

    @Relation(parentColumn = "barcodeId", entityColumn = "eatFoodId")
    public List<EatFoodsHistory> eatFoodsHistory;


}
