package com.fordlabs.innovation.retailapp;

import android.databinding.BaseObservable;

public class CartItemViewModel extends BaseObservable {

    private String productName;
    private String productPrice;
    private String formattedProductPrice;

    public CartItemViewModel(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getFormattedProductPrice() {
        return "Rs:" + getProductPrice();
    }
}
