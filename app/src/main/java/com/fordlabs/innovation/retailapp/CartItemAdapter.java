package com.fordlabs.innovation.retailapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fordlabs.innovation.retailapp.databinding.ItemCartBinding;

import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    private List<CartItemViewModel> cartItemViewModelList;
    private CartViewModel cartViewModel;
    private CartItemViewHolder cartItemViewHolder;

    public CartItemAdapter(CartViewModel cartViewModel) {
        this.cartViewModel = cartViewModel;
        cartItemViewModelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(layoutInflater, viewGroup, false);
        return new CartItemViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int i) {
        cartItemViewHolder = holder;
        CartItemViewModel cartItemViewModel = getItemForPosition(i);
        holder.bind(cartItemViewModel, cartViewModel);
    }

    @Override
    public int getItemCount() {
        return (null != cartItemViewModelList ? cartItemViewModelList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void setItems(List<CartItemViewModel> itemViewModelList) {
        this.cartItemViewModelList.clear();
        this.cartItemViewModelList.addAll(itemViewModelList);
        notifyDataSetChanged();
    }

    public void deleteItem(CartItemViewModel cartItemViewModel) {
        Log.d("position", "" + cartItemViewModel.getProductName() + " " + cartItemViewModel.getId());
        int position = cartItemViewModelList.indexOf(cartItemViewModel);
        cartItemViewModelList.remove(position);
        notifyDataSetChanged();
    }

    private CartItemViewModel getItemForPosition(int position) {
        return cartItemViewModelList.get(position);
    }
}
