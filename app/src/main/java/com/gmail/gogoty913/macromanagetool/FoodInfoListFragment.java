package com.gmail.gogoty913.macromanagetool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.gogoty913.macromanagetool.adapter.FoodInfoListAdapter;
import com.gmail.gogoty913.macromanagetool.databinding.FragmentFoodInfoListBinding;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FoodInfoListFragment extends Fragment {
    private FoodInfoListViewModel mViewModel;
    private FoodInfoListAdapter adapter;
    private FragmentFoodInfoListBinding binding;

    public static FoodInfoListFragment newInstance(){
        return new FoodInfoListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_info_list,container,false );
        adapter = new FoodInfoListAdapter();
        binding.foodInfoList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FoodInfoListViewModel.class);
        // TODO: Use the ViewModel
        observeViewModel(mViewModel);

    }

    private void observeViewModel(FoodInfoListViewModel viewModel){

        viewModel.getFoodInfoObservable().observe(this, new Observer<List<FoodInfo>>() {
            @Override
            public void onChanged(@Nullable List<FoodInfo> foodInfoList) {
                if(foodInfoList != null){
                    adapter.setFoodInfoList(foodInfoList);
                }
            }
        });

    }
}
