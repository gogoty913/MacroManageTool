package com.gmail.gogoty913.macromanagetool.logic;

import com.gmail.gogoty913.macromanagetool.entity.UserInfo;

import androidx.annotation.NonNull;

public class PmcLogic {

    /**
     * 基礎代謝の算出
     * @return
     */
    public static float calculateBasalMetabolism(@NonNull UserInfo userInfo){

        // 性別による係数の算出
        int coefficient = 0;
        if(userInfo.style == 1){
            coefficient = 5;
        } else if (userInfo.style == 2){
            coefficient = -161;
        }

        return 10 * userInfo.weight + 6.25f * userInfo.height - 5 * userInfo.age + coefficient ;
    }

    public static float calculateAllCalorie(@NonNull UserInfo userInfo){
        // 性別による係数の算出
        float coefficient = 1;
        if(userInfo.future == 0){
            coefficient = 0.8f;
        } else if (userInfo.future == 2){
            coefficient = 1.2f;
        }

        return userInfo.basalMetabolism * coefficient;
    }

    public static float calculateAimCarbohydrate(@NonNull UserInfo userInfo){
        return ( userInfo.allCalorie -(userInfo.aimLipid *9 + userInfo.aimProtein * 4 ))/ 4;
    }

    public static float calculateAimLipid(@NonNull UserInfo userInfo){
        return userInfo.allCalorie * 0.25f / 9;
    }
    public static float calculateAimProtein(@NonNull UserInfo userInfo){
        return userInfo.weight * 2;
    }

    public static void setAimPMC(@NonNull UserInfo userInfo){
        userInfo.basalMetabolism = calculateBasalMetabolism(userInfo);
        userInfo.allCalorie = calculateAllCalorie(userInfo);
        userInfo.aimProtein = calculateAimProtein(userInfo);
        userInfo.aimLipid = calculateAimLipid(userInfo);
        userInfo.aimCarbohydrate = calculateAimCarbohydrate(userInfo);

    }
}
