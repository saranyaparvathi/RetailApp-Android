package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import dagger.Module;
import data.ProductCart;
import data.ProductCartRepository;
import data.ProductCartRoomDatabaseProvider;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RetailLandingViewModelTest {

    @Mock
    Application application;

    @Mock
    ProductCartRepository productCartRepository;

    private RetailLandingViewModel subject;

    @Before
    public void setup() {
        subject = new RetailLandingViewModel(application);
        when(productCartRepository.getAllWords()).thenReturn(any(LiveData.class));
    }

    @Test
    public void addProductToCart_insertsProductIntoProductCartRepository() {
        ProductItemViewModel productItemViewModel = new ProductItemViewModel(R.drawable.microwave, "Micrwave", "Rs.2000");

        subject.addProductToCart(productItemViewModel);

        verify(productCartRepository).insert(new ProductCart(0, productItemViewModel));
    }



}