package com.gmail.gogoty913.macromanagetool.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.gogoty913.macromanagetool.R;
import com.gmail.gogoty913.macromanagetool.callback.FoodInfoCardCallBack;
import com.gmail.gogoty913.macromanagetool.databinding.EatFoodListInfoBinding;
import com.gmail.gogoty913.macromanagetool.databinding.FoodInfoListBinding;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FoodInfoListAdapter extends RecyclerView.Adapter<FoodInfoListAdapter.FoodInfoHolder> {

    List<? extends FoodInfo> foodInfoList;

    @Nullable
    FoodInfoCardCallBack foodInfoCardCallBack;

    public FoodInfoListAdapter(FoodInfoCardCallBack foodInfoCardCallBack){
        this.foodInfoCardCallBack = foodInfoCardCallBack;
    }



    public void setFoodInfoList(List<FoodInfo> foodInfoList) {
        if (this.foodInfoList == null) {
            this.foodInfoList = foodInfoList;
            notifyItemRangeInserted(0, foodInfoList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return FoodInfoListAdapter.this.foodInfoList.size();
                }

                @Override
                public int getNewListSize() {
                    return foodInfoList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

                    FoodInfo oldHistory = FoodInfoListAdapter.this.foodInfoList.get(oldItemPosition);
                    FoodInfo newHistory = foodInfoList.get(newItemPosition);

                    if (oldHistory.barcodeId == newHistory.barcodeId) {
                        return true;
                    }

                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    FoodInfo oldHistory = FoodInfoListAdapter.this.foodInfoList.get(oldItemPosition);
                    FoodInfo newHistory = foodInfoList.get(newItemPosition);

                    if (areItemsTheSame(oldItemPosition, newItemPosition) &&
                            oldHistory.barcodeId == newHistory.barcodeId) {
                        return true;
                    }
                    return false;
                }
            });
            this.foodInfoList = foodInfoList;

            result.dispatchUpdatesTo(this);
        }

    }

    @Override
    public FoodInfoListAdapter.FoodInfoHolder onCreateViewHolder(ViewGroup viewGroup,
                                                                 int viewType) {

        FoodInfoListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.food_info_list, viewGroup, false);

        binding.setCallback(foodInfoCardCallBack);
        return new FoodInfoListAdapter.FoodInfoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodInfoHolder holder, int position) {

        holder.binding.setFoodInfo(foodInfoList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return foodInfoList == null ? 0 : foodInfoList.size();
    }


    static class FoodInfoHolder extends RecyclerView.ViewHolder {

        FoodInfoListBinding binding;

        public FoodInfoHolder(FoodInfoListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
