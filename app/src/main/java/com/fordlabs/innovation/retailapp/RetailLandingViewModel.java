package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;

import data.ProductCart;
import data.ProductCartRepository;

public class RetailLandingViewModel extends AndroidViewModel {

    private List<ProductItemViewModel> productItemViewModelList;
    private ProductCartRepository productCartRepository;
    private LinkedHashMap<CategoryItemViewModel, List<ProductItemViewModel>> sectionList;

    public RetailLandingViewModel(Application application) {
        super(application);
        productItemViewModelList = new ArrayList<>();
        sectionList = new LinkedHashMap<>();
        productItemViewModelList.addAll(populateData());
        productCartRepository = new ProductCartRepository(application);
    }

    public void launchCart(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    public List<ProductItemViewModel> populateData() {
        return Arrays.asList(new ProductItemViewModel(R.drawable.chair, "Chair", "1000"),
                new ProductItemViewModel(R.drawable.fridge, "Fridge", "30300"),
                new ProductItemViewModel(R.drawable.table, "Table", "49000"),
                new ProductItemViewModel(R.drawable.television1, "Television", "23000"),
                new ProductItemViewModel(R.drawable.television2, "Television", "33000"),
                new ProductItemViewModel(R.drawable.microwave, "Microwave Oven", "12200"));
    }

    public List<ProductItemViewModel> populateData2() {
        return Arrays.asList(new ProductItemViewModel(R.drawable.chair, "Chair", "1000"),
                new ProductItemViewModel(R.drawable.fridge, "Fridge", "30300"),
                new ProductItemViewModel(R.drawable.table, "Table", "49000"),
                new ProductItemViewModel(R.drawable.television1, "Television", "23000"),
                new ProductItemViewModel(R.drawable.television2, "Television", "33000"),
                new ProductItemViewModel(R.drawable.microwave, "Microwave Oven", "12200"));
    }

    public LinkedHashMap<CategoryItemViewModel, List<ProductItemViewModel>> getSectionList() {
        sectionList.put(new CategoryItemViewModel("Furniture"), populateData());
        sectionList.put(new CategoryItemViewModel("Electronics"), populateData2());
        return sectionList;
    }

    public SectionAdapter getAdapter() {
        return new SectionAdapter(this);
    }

    public void addProductToCart(ProductItemViewModel productItemViewModel) {
        productCartRepository.insert(new ProductCart(0, productItemViewModel));
    }

    public enum ProductTypeEnum {
        SECTION_HEADER,
        ITEM
    }
}
