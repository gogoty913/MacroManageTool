package com.gmail.gogoty913.macromanagetool;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EatFoodHistoryListFragment extends Fragment {

    private EatFoodHistoryListViewModel mViewModel;

    public static EatFoodHistoryListFragment newInstance() {
        return new EatFoodHistoryListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_eat_food_history_list,container,false );

        return inflater.inflate(R.layout.fragment_eat_food_history_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EatFoodHistoryListViewModel.class);
        // TODO: Use the ViewModel
    }

}
