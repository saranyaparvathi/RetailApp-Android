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
    public ObservableBoolean totalPriceVisibility = new ObservableBoolean(false);

    private List<CartItemViewModel> cartItemViewModelList;
    private ProductCartRepository productCartRepository;
    private CartItemAdapter cartItemAdapter;
    private int price = 0;
    private boolean isProductDeleted;

    public CartViewModel(Application application) {
        super(application);
        cartItemViewModelList = new ArrayList<>();
        productCartRepository = new ProductCartRepository(application);
        cartItemAdapter = new CartItemAdapter(this);
    }

    public LiveData<List<ProductCart>> getData() {
        return productCartRepository.getAllWords();
    }

    public void deleteAll() {
        refreshCartValues();
        productCartRepository.deleteAll();
        totalPriceVisibility.set(false);
        isCartPresent.set(false);
    }

    public void setData(ProductCart productCart) {
        if (isProductDeleted) {
            refreshCartValues();
            isProductDeleted = false;
        }
        ProductItemViewModel productItemViewModel = productCart.getProductItemViewModel();
        if (!isCartPresent.get()) {
            isCartPresent.set(true);
        }
        cartItemViewModelList.add(new CartItemViewModel(productCart.getId(), productItemViewModel));
        price += Integer.valueOf(productItemViewModel.getProductPrice());
        setTotalPrice(price);
        cartItemAdapter.setItems(cartItemViewModelList);
    }

    public List<CartItemViewModel> populateData() {
        isCartPresent.set(!cartItemViewModelList.isEmpty());
        return cartItemViewModelList;
    }

    public CartItemAdapter getAdapter() {
        return cartItemAdapter;
    }

    public void deleteProductFromCart(CartItemViewModel cartItemViewModel) {
        isProductDeleted = true;
        productCartRepository.deleteProduct(new ProductCart(cartItemViewModel.getId(), cartItemViewModel.getProductItemViewModel()));
        price -= Integer.valueOf(cartItemViewModel.getProductPrice());
        isCartPresent.set(price > 0);
        cartItemAdapter.deleteItem(cartItemViewModel);
        setTotalPrice(price);
    }

    private void setTotalPrice(int price) {
        totalPriceVisibility.set(price > 0);
        totalPrice.set("");
        totalPrice.set("Total : Rs." + price);
    }

    private void refreshCartValues() {
        cartItemViewModelList.clear();
        price = 0;
        totalPrice.set("");
    }
}
