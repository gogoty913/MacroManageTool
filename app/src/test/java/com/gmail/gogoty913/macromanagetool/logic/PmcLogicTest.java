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

    EatFoodInfoHistory eatFoodInfoHistory;
    List<EatFoodInfoHistory> eatFoodInfoHistoryList;

    @Before
    public void before(){
        eatFoodInfoHistory = new EatFoodInfoHistory();
        eatFoodInfoHistoryList = new ArrayList<EatFoodInfoHistory>();
        eatFoodInfoHistory.foodInfo = new FoodInfo();
        eatFoodInfoHistory.eatFoodsHistory = new EatFoodsHistory();

    }


    @Test
    public void intakeCalorie() {

        eatFoodInfoHistory.foodInfo.calorie = 153;
        eatFoodInfoHistory.foodInfo.displayCapacity = 41;
        eatFoodInfoHistory.foodInfo.allCapacity = 41;
        eatFoodInfoHistory.eatFoodsHistory.eatValuePercent = 4;

        eatFoodInfoHistoryList.add(eatFoodInfoHistory);
        eatFoodInfoHistoryList.add(eatFoodInfoHistory);

        double intakeCalorie = PmcLogic.intakeCalorie(eatFoodInfoHistoryList);

        assertThat(intakeCalorie,is(153d/41d*0.04*41d*2d));
    }

    @Test
    public void intakeCalorie2() {

        eatFoodInfoHistory.foodInfo.calorie = 153;
        eatFoodInfoHistory.foodInfo.displayCapacity = 41;
        eatFoodInfoHistory.foodInfo.allCapacity = 41;
        eatFoodInfoHistory.eatFoodsHistory.eatValueGram = 2;

        eatFoodInfoHistoryList.add(eatFoodInfoHistory);

        double intakeCalorie = PmcLogic.intakeCalorie(eatFoodInfoHistoryList);

        assertThat(intakeCalorie,is(153d/41d*2d));
    }

    @Test
    public void intakeProtein() {
    }

    @Test
    public void intakeLipid() {
    }
}