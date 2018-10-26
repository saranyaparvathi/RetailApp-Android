package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import data.ProductCart;
import data.ProductCartRepository;

public class RetailLandingViewModel extends AndroidViewModel {

    public ObservableField<String> textArea = new ObservableField<>();

    private List<ProductItemViewModel> productItemViewModelList;
    private ProductCartRepository productCartRepository;
    private LinkedHashMap<CategoryItemViewModel, List<ProductItemViewModel>> sectionList;

    public RetailLandingViewModel(Application application) {
        super(application);
        productItemViewModelList = new ArrayList<>();
        sectionList = new LinkedHashMap<>();
        productItemViewModelList.addAll(populateData());
        productCartRepository = new ProductCartRepository(application);
        getData();
    }

    public LiveData<List<ProductCart>> getData() {
        return productCartRepository.getAllWords();
    }

    public void setData(ProductItemViewModel productItemViewModel) {
        textArea.set("" + productItemViewModel.getProductName());
    }

    public List<ProductItemViewModel> populateData() {
        return Arrays.asList(new ProductItemViewModel(R.drawable.chair, "Chair", "Rs.1000"),
                new ProductItemViewModel(R.drawable.fridge, "Fridge", "Rs.30300"),
                new ProductItemViewModel(R.drawable.table, "Table", "Rs.49000"),
                new ProductItemViewModel(R.drawable.television1, "Television", "Rs.23000"),
                new ProductItemViewModel(R.drawable.television2, "Television", "Rs.33000"),
                new ProductItemViewModel(R.drawable.microwave, "Microwave Oven", "Rs.12200"));
    }

    public List<ProductItemViewModel> populateData2() {
        return Arrays.asList(new ProductItemViewModel(R.drawable.chair, "Chair", "Rs.1000"),
                new ProductItemViewModel(R.drawable.fridge, "Fridge", "Rs.30300"),
                new ProductItemViewModel(R.drawable.table, "Table", "Rs.49000"),
                new ProductItemViewModel(R.drawable.television1, "Television", "Rs.23000"),
                new ProductItemViewModel(R.drawable.television2, "Television", "Rs.33000"),
                new ProductItemViewModel(R.drawable.microwave, "Microwave Oven", "Rs.12200"));
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
