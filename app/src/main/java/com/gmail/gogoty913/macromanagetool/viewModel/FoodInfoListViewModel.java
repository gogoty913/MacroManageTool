package com.gmail.gogoty913.macromanagetool.viewModel;

import android.app.Application;

import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FoodInfoListViewModel extends AndroidViewModel {

    private final LiveData<List<FoodInfo>> foodInfoObservable;

    public ObservableField<List<FoodInfo>> foodInfoList = new ObservableField<>();

    public FoodInfoListViewModel(@NonNull Application application){
        super(application);
        foodInfoObservable = AppRepository.getInstance(getApplication()).getFoodInfoList();
    }

    public LiveData<List<FoodInfo>> getFoodInfoObservable() {
        return foodInfoObservable;
    }

    public void setFoodInfoObservable(List<FoodInfo> foodInfoList){
        this.foodInfoList.set(foodInfoList);
    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new FoodInfoListViewModel(application);
        }
    }
}
