package com.gmail.gogoty913.macromanagetool;

import android.app.Application;

import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EatFoodHistoryListViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private final LiveData<List<EatFoodsHistory>> eatFoodsHistoryListObservable;

    public EatFoodHistoryListViewModel(Application application){
        super(application);

        String eatDay = new SimpleDateFormat("yyyyMMdd").format(new Date());

        eatFoodsHistoryListObservable = AppRepository.getInstance(getApplication()).getEatFoodsHistoryList(eatDay);
    }

    public LiveData<List<EatFoodsHistory>> getEatFoodsHistoryListObservable() {
        return eatFoodsHistoryListObservable;
    }
}