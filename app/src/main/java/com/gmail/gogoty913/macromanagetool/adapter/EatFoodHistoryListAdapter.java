package com.gmail.gogoty913.macromanagetool.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.gogoty913.macromanagetool.R;
import com.gmail.gogoty913.macromanagetool.databinding.FoodListInfoBinding;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class EatFoodHistoryListAdapter extends RecyclerView.Adapter<EatFoodHistoryListAdapter.EatFoodHistoryHolder> {

    List<? extends EatFoodInfoHistory> eatFoodInfoHistoryList;


    public void setEatFoodInfoHistoryList(List<EatFoodInfoHistory> eatFoodInfoHistoryList){
        if(this.eatFoodInfoHistoryList == null){
            this.eatFoodInfoHistoryList = eatFoodInfoHistoryList;
            notifyItemRangeInserted(0,eatFoodInfoHistoryList.size());
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return EatFoodHistoryListAdapter.this.eatFoodInfoHistoryList.size();
                }

                @Override
                public int getNewListSize() {
                    return eatFoodInfoHistoryList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

                    EatFoodInfoHistory oldHistory = EatFoodHistoryListAdapter.this.eatFoodInfoHistoryList.get(oldItemPosition);
                    EatFoodInfoHistory newHistory = eatFoodInfoHistoryList.get(newItemPosition);

                    if(oldHistory.eatFoodsHistory.userId == newHistory.eatFoodsHistory.userId &&
                    oldHistory.eatFoodsHistory.eatDay == newHistory.eatFoodsHistory.eatDay &&
                    oldHistory.eatFoodsHistory.eatTime == newHistory.eatFoodsHistory.eatTime){
                        return true;
                    }

                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    EatFoodInfoHistory oldHistory = EatFoodHistoryListAdapter.this.eatFoodInfoHistoryList.get(oldItemPosition);
                    EatFoodInfoHistory newHistory = eatFoodInfoHistoryList.get(newItemPosition);

                    if(areItemsTheSame(oldItemPosition,newItemPosition) &&
                            oldHistory.eatFoodsHistory.eatFoodId == newHistory.eatFoodsHistory.eatFoodId){
                        return true;
                    }
                    return false;
                }
            });
            this.eatFoodInfoHistoryList = eatFoodInfoHistoryList;

            result.dispatchUpdatesTo(this);
        }

    }

    @Override
    public EatFoodHistoryHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){

        FoodListInfoBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.food_list_info, viewGroup, false);

        return new EatFoodHistoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EatFoodHistoryHolder holder, int position) {

        holder.binding.setEatFoodInfoHistory(eatFoodInfoHistoryList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return eatFoodInfoHistoryList == null ? 0 : eatFoodInfoHistoryList.size() ;
    }


    static class EatFoodHistoryHolder extends RecyclerView.ViewHolder{

        FoodListInfoBinding binding;

        public EatFoodHistoryHolder(FoodListInfoBinding binding){
            super(binding.getRoot());
            this.binding = binding;

        }
    }





}
