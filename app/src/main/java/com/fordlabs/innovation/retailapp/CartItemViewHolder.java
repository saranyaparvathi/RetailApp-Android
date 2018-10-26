package com.fordlabs.innovation.retailapp;

import android.support.v7.widget.RecyclerView;

import com.fordlabs.innovation.retailapp.databinding.ItemCartBinding;
import com.fordlabs.innovation.retailapp.databinding.ItemProductCardBinding;

public class CartItemViewHolder extends RecyclerView.ViewHolder {

     ItemCartBinding binding;

    public CartItemViewHolder(ItemCartBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.executePendingBindings();
    }

    public void bind(CartItemViewModel itemViewModel) {
        binding.setItemViewModel(itemViewModel);
        binding.executePendingBindings();
    }
}
