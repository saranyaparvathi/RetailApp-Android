package com.fordlabs.innovation.retailapp;

import android.databinding.BaseObservable;

import java.util.Objects;

public class CartItemViewModel extends BaseObservable {

    private int id;
    private ProductItemViewModel productItemViewModel;
    private String productName;
    private String productPrice;
    private String formattedProductPrice;

    public CartItemViewModel(int id, ProductItemViewModel productItemViewModel) {
        this.id = id;
        this.productItemViewModel = productItemViewModel;
    }

    public int getId() {
        return id;
    }

    public ProductItemViewModel getProductItemViewModel() {
        return productItemViewModel;
    }

    public String getProductName() {
        return productItemViewModel.getProductName();
    }

    public String getProductPrice() {
        return productItemViewModel.getProductPrice();
    }

    public String getFormattedProductPrice() {
        return "Rs:" + getProductPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemViewModel that = (CartItemViewModel) o;
        return id == that.id &&
                Objects.equals(productItemViewModel, that.productItemViewModel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, productItemViewModel);
    }
}
