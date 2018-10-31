package com.fordlabs.innovation.retailapp;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;

import data.ProductCart;
import data.ProductCartRepository;

public class ProductDetailsViewModel extends AndroidViewModel {

    public ObservableField<String> productName = new ObservableField<>();
    public ObservableField<String> productPrice = new ObservableField<>();
    public ObservableInt productImage = new ObservableInt();

    private ProductItemViewModel productItemViewModel;
    private ProductCartRepository productCartRepository;

    public ProductDetailsViewModel(@NonNull Application application, Context context) {
        super(application);
        productCartRepository = new ProductCartRepository(application);
        setDataToView((Activity) context);
    }

    private void setDataToView(Activity context) {
        Bundle bundle = context.getIntent().getExtras();
        productItemViewModel = bundle.getParcelable("product");
        if (productItemViewModel != null) {
            productName.set(productItemViewModel.getProductName());
            productPrice.set("Rs:" + productItemViewModel.getProductPrice());
            productImage.set(productItemViewModel.getProductImage());
        }
    }

    public void launchCart(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    public void addProductToCart() {
        productCartRepository.insert(new ProductCart(0, productItemViewModel));
    }
}
