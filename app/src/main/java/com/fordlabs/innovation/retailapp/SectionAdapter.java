package com.fordlabs.innovation.retailapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fordlabs.innovation.retailapp.RetailLandingViewModel.ProductTypeEnum;
import com.fordlabs.innovation.retailapp.databinding.ItemCategoryBinding;
import com.fordlabs.innovation.retailapp.databinding.ItemProductCardBinding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.fordlabs.innovation.retailapp.RetailLandingViewModel.ProductTypeEnum.ITEM;
import static com.fordlabs.innovation.retailapp.RetailLandingViewModel.ProductTypeEnum.SECTION_HEADER;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> productSectionList;
    private LinkedHashMap<CategoryItemViewModel, List<ProductItemViewModel>> sectionMap;
    private List<ProductTypeEnum> productTypeEnumList;
    private RetailLandingViewModel retailLandingViewModel;

    public SectionAdapter(RetailLandingViewModel retailLandingViewModel) {
        this.retailLandingViewModel = retailLandingViewModel;
        productSectionList = new ArrayList<>();
        productTypeEnumList = new ArrayList<>();
        sectionMap = retailLandingViewModel.getSectionList();

        for (Map.Entry<CategoryItemViewModel, List<ProductItemViewModel>> entry : sectionMap.entrySet()) {
            CategoryItemViewModel categoryItemViewModel = entry.getKey();
            List<ProductItemViewModel> productItemViewModelList = entry.getValue();
            productSectionList.add(categoryItemViewModel);
            productSectionList.addAll(productItemViewModelList);
        }

        for (Object section : productSectionList) {
            if (section instanceof CategoryItemViewModel) {
                productTypeEnumList.add(SECTION_HEADER);
            } else {
                productTypeEnumList.add(ITEM);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == ITEM.ordinal()) {
            ItemProductCardBinding itemProductCardBinding = ItemProductCardBinding.inflate(layoutInflater, viewGroup, false);
            return new ProductItemViewHolder(itemProductCardBinding);
        } else {
            ItemCategoryBinding itemCategoryBinding = ItemCategoryBinding.inflate(layoutInflater, viewGroup, false);
            return new CategoryItemViewHolder(itemCategoryBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ProductItemViewHolder) {
            ((ProductItemViewHolder) viewHolder).bind(retailLandingViewModel, (ProductItemViewModel) productSectionList.get(i));
        } else {
            ((CategoryItemViewHolder) viewHolder).bind(retailLandingViewModel, (CategoryItemViewModel) productSectionList.get(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return productTypeEnumList.get(position).ordinal();

    }

    @Override
    public int getItemCount() {
        return productSectionList.size();
    }
}
