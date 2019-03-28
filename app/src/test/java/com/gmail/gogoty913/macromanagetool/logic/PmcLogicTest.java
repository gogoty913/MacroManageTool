package com.gmail.gogoty913.macromanagetool.logic;

import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PmcLogicTest {

    List<EatFoodInfoHistory> eatFoodInfoHistoryList;
    EatFoodInfoHistory eatFoodInfoHistory;

    @Before
    public void before(){
        eatFoodInfoHistory = new EatFoodInfoHistory();
        eatFoodInfoHistoryList = new ArrayList<>();

        eatFoodInfoHistory.eatFoodsHistory = new ArrayList<EatFoodsHistory>();
        eatFoodInfoHistory.foodInfo = new FoodInfo();
        eatFoodInfoHistory.eatFoodsHistory.add(new EatFoodsHistory());

    }


    @Test
    public void intakeCalorie() {

        eatFoodInfoHistory.foodInfo.calorie = 100;
        eatFoodInfoHistory.foodInfo.displayCapacity = 100;
        eatFoodInfoHistory.foodInfo.allCapacity = 100;
        eatFoodInfoHistory.eatFoodsHistory.get(0).eatValuePercent = 1;
        eatFoodInfoHistory.eatFoodsHistory.add(new EatFoodsHistory());
        eatFoodInfoHistory.eatFoodsHistory.get(1).eatValuePercent = 1;
        eatFoodInfoHistoryList.add(eatFoodInfoHistory);
        eatFoodInfoHistoryList.add(eatFoodInfoHistory);
        double intakeCalorie = PmcLogic.intakeCalorie(eatFoodInfoHistoryList);

        assertThat(intakeCalorie,is(400.0));
    }

    @Test
    public void intakeCarbon() {
    }

    @Test
    public void intakeProtein() {
    }

    @Test
    public void intakeLipid() {
    }
}