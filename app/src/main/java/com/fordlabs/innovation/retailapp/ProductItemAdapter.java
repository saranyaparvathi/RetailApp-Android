package com.fordlabs.innovation.retailapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fordlabs.innovation.retailapp.databinding.ItemProductCardBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemViewHolder> {

    private List<ProductItemViewModel> productItemViewModelList;
    private RetailLandingViewModel retailLandingViewModel;

    public ProductItemAdapter(RetailLandingViewModel retailLandingViewModel) {
        this.retailLandingViewModel = retailLandingViewModel;
        productItemViewModelList = new ArrayList<>();
        productItemViewModelList.addAll(retailLandingViewModel.populateData());
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemProductCardBinding itemProductCardBinding = ItemProductCardBinding.inflate(layoutInflater, viewGroup, false);
        return new ProductItemViewHolder(itemProductCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int i) {
        ProductItemViewModel productItemViewModel = getItemForPosition(i);
        holder.bind(retailLandingViewModel, productItemViewModel);

    }

    @Override
    public int getItemCount() {
        return (null != productItemViewModelList ? productItemViewModelList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_product_card;
    }

    public void setItems(List<ProductItemViewModel> itemViewModelList) {
        this.productItemViewModelList = itemViewModelList;
        notifyDataSetChanged();
    }

    private ProductItemViewModel getItemForPosition(int position) {
        return productItemViewModelList.get(position);
    }
}
