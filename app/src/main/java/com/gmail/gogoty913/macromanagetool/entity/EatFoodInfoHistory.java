package com.gmail.gogoty913.macromanagetool.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EatFoodInfoHistory {

    @Embedded
    public EatFoodsHistory eatFoodsHistory;

    @Embedded
    public FoodInfo foodInfo;


}
