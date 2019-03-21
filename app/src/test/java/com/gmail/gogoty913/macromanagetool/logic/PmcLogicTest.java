package com.gmail.gogoty913.macromanagetool.logic;

import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PmcLogicTest {

    @Test
    public void intakeCalorie() {

        EatFoodInfoHistory eatFoodInfoHistory = new EatFoodInfoHistory();
        List<EatFoodInfoHistory> eatFoodInfoHistoryList = new ArrayList<>();

        double intakeCalorie = PmcLogic.intakeCalorie(eatFoodInfoHistoryList);

//        assertThat(intakeCalorie);


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