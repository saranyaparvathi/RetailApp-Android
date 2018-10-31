package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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
        productItemViewModelList.addAll(populateFurniture());
        productCartRepository = new ProductCartRepository(application);
    }

    public void launchCart(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    public void launchProductDetails(View view, ProductItemViewModel productItemViewModel) {
        Context context = view.getContext();
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra("product", productItemViewModel);
        context.startActivity(intent);
    }

    public LinkedHashMap<CategoryItemViewModel, List<ProductItemViewModel>> getSectionList() {
        sectionList.put(new CategoryItemViewModel("Furniture"), populateFurniture());
        sectionList.put(new CategoryItemViewModel("Electronics"), populateElectronics());
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

    private List<ProductItemViewModel> populateFurniture() {
        return Arrays.asList(new ProductItemViewModel(R.drawable.chair, "Chair", "1000"),
                new ProductItemViewModel(R.drawable.furniture, "Combo Furniture", "100300"),
                new ProductItemViewModel(R.drawable.table, "Table", "49000"),
                new ProductItemViewModel(R.drawable.recliner_chair, "Recliner Chair", "34000"));
    }

    private List<ProductItemViewModel> populateElectronics() {
        return Arrays.asList(new ProductItemViewModel(R.drawable.fridge, "Fridge", "30300"),
                new ProductItemViewModel(R.drawable.television1, "Television", "23000"),
                new ProductItemViewModel(R.drawable.television2, "Television", "33000"),
                new ProductItemViewModel(R.drawable.microwave, "Microwave Oven", "12200"));
    }
}
