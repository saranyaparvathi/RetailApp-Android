package com.fordlabs.innovation.retailapp;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;

public class ProductItemViewModel extends BaseObservable {

    private @DrawableRes int productImage;
    private String productName;
    private String productPrice;

    public ProductItemViewModel(int productImage, String productName, String productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
