package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import data.ProductCart;
import data.ProductCartRepository;

public class CartViewModel extends AndroidViewModel {

    public ObservableBoolean isCartPresent = new ObservableBoolean(false);
    public ObservableField<String> totalPrice = new ObservableField<>();

    private List<CartItemViewModel> cartItemViewModelList;
    private ProductCartRepository productCartRepository;
    private CartItemAdapter cartItemAdapter;
    private int price = 0;
    private String modifiedPrice = "Total : Rs. ";

    public CartViewModel(Application application) {
        super(application);
        cartItemViewModelList = new ArrayList<>();
        productCartRepository = new ProductCartRepository(application);
        cartItemAdapter = new CartItemAdapter(this);
        getData();
    }

    public LiveData<List<ProductCart>> getData() {
        return productCartRepository.getAllWords();
    }

    public void deleteAll() {
        price = 0;
        productCartRepository.deleteAll();
        isCartPresent.set(false);
    }

    public void setData(ProductItemViewModel productItemViewModel) {
        if (!isCartPresent.get()) {
            isCartPresent.set(true);
        }
        cartItemViewModelList.add(new CartItemViewModel(productItemViewModel.getProductName(),
                productItemViewModel.getProductPrice()));
        price += Integer.valueOf(productItemViewModel.getProductPrice());
        modifiedPrice = "Rs:" + price;
        totalPrice.set("");
        totalPrice.set(modifiedPrice);
        cartItemAdapter.setItems(cartItemViewModelList);
        cartItemAdapter.notifyDataSetChanged();
    }

    public List<CartItemViewModel> populateData() {
        isCartPresent.set(!cartItemViewModelList.isEmpty());
        return cartItemViewModelList;
    }

    public CartItemAdapter getAdapter() {
        return cartItemAdapter;
    }
}
