package com.gmail.gogoty913.macromanagetool;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.gogoty913.macromanagetool.adapter.EatFoodHistoryListAdapter;
import com.gmail.gogoty913.macromanagetool.databinding.FragmentEatFoodHistoryListBinding;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;
import com.gmail.gogoty913.macromanagetool.viewModel.EatFoodHistoryListViewModel;

import java.util.List;

public class EatFoodHistoryListFragment extends Fragment {

    private EatFoodHistoryListViewModel mViewModel;
    private EatFoodHistoryListAdapter adapter;
    private FragmentEatFoodHistoryListBinding binding;

    public static EatFoodHistoryListFragment newInstance() {
        return new EatFoodHistoryListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_eat_food_history_list,container,false );

        adapter = new EatFoodHistoryListAdapter();

        binding.eatFoodHistoryList.setAdapter(adapter);
        binding.setIsLoading(true);


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EatFoodHistoryListViewModel.class);
        // TODO: Use the ViewModel
        observeViewModel(mViewModel);

    }

    private void observeViewModel(EatFoodHistoryListViewModel viewModel){

        viewModel.getEatFoodsHistoryListObservable().observe(this, new Observer<List<EatFoodInfoHistory>>() {
            @Override
            public void onChanged(@Nullable List<EatFoodInfoHistory> eatFoodsHistories) {
                if(eatFoodsHistories != null){
                    binding.setIsLoading(false);
                    adapter.setEatFoodInfoHistoryList(eatFoodsHistories);

                }
            }
        });

    }

}
