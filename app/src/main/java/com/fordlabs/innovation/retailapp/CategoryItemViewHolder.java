package com.fordlabs.innovation.retailapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fordlabs.innovation.retailapp.databinding.ItemCategoryBinding;

public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

    public CategoryItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    ItemCategoryBinding binding;

    public CategoryItemViewHolder(ItemCategoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.executePendingBindings();
    }

    public void bind(RetailLandingViewModel retailLandingViewModel, CategoryItemViewModel itemViewModel) {
        binding.setItemViewModel(itemViewModel);
        binding.setViewModel(retailLandingViewModel);
        binding.executePendingBindings();
    }
}
