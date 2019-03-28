package com.gmail.gogoty913.macromanagetool;

import android.app.Application;

import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FoodInfoViewModel extends AndroidViewModel {

    private final LiveData<FoodInfo> foodInfoObservable;
    private final String barcodeId;

    public ObservableField<FoodInfo> foodInfo = new ObservableField<>();

    public FoodInfoViewModel(@NonNull Application application, final String barcodeId){
        super(application);
        this.barcodeId = barcodeId;
        foodInfoObservable = AppRepository.getInstance(getApplication()).getFoodInfo(barcodeId);
    }

    public LiveData<FoodInfo> getFoodInfoObservable() {
        return foodInfoObservable;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setFoodInfoObservable(FoodInfo foodInfo){
        this.foodInfo.set(foodInfo);
    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        private final String barcodeId;

        public Factory(@NonNull Application application, String barcodeId) {
            this.application = application;
            this.barcodeId = barcodeId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new FoodInfoViewModel(application, barcodeId);
        }
    }
}
