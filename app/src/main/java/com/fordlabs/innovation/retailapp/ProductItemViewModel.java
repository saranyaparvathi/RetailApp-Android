package com.fordlabs.innovation.retailapp;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import static android.provider.Settings.System.getString;

public class ProductItemViewModel extends BaseObservable implements Parcelable {

    private @DrawableRes int productImage;
    private String productName;
    private String productPrice;
    private String formattedProductPrice;
    private boolean addToCartButtonVisibility;

    public ProductItemViewModel(int productImage, String productName, String productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    protected ProductItemViewModel(Parcel in) {
        productImage = in.readInt();
        productName = in.readString();
        productPrice = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productImage);
        dest.writeString(productName);
        dest.writeString(productPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductItemViewModel> CREATOR = new Creator<ProductItemViewModel>() {
        @Override
        public ProductItemViewModel createFromParcel(Parcel in) {
            return new ProductItemViewModel(in);
        }

        @Override
        public ProductItemViewModel[] newArray(int size) {
            return new ProductItemViewModel[size];
        }
    };

    public int getProductImage() {
        return productImage;
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

    public boolean isAddToCartButtonVisibility() {
        return addToCartButtonVisibility;
    }

    public void setAddToCartButtonVisibility(boolean addToCartButtonVisibility) {
        this.addToCartButtonVisibility = addToCartButtonVisibility;
    }
}
