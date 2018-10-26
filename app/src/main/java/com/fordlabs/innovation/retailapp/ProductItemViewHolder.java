package com.fordlabs.innovation.retailapp;

import android.support.v7.widget.RecyclerView;

import com.fordlabs.innovation.retailapp.databinding.ItemProductCardBinding;

public class ProductItemViewHolder extends RecyclerView.ViewHolder {

     ItemProductCardBinding binding;

    public ProductItemViewHolder(ItemProductCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.executePendingBindings();
    }

    public void bind(RetailLandingViewModel retailLandingViewModel, ProductItemViewModel itemViewModel) {
        binding.setItemViewModel(itemViewModel);
        binding.setViewModel(retailLandingViewModel);
        binding.executePendingBindings();
    }
}
