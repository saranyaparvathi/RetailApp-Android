package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fordlabs.innovation.retailapp.databinding.ActivityCartBinding;
import com.fordlabs.innovation.retailapp.databinding.ActivityRetailLandingBinding;

import java.util.List;

import data.ProductCart;

public class CartActivity extends AppCompatActivity {

    CartViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCartBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartActivity.ViewModelProviderFactory factory =
                new ViewModelProviderFactory(this.getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(CartViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getData().observe(this, new Observer<List<ProductCart>>() {
            @Override
            public void onChanged(@Nullable List<ProductCart> productCarts) {
                for (ProductCart productCart : productCarts) {
                    viewModel.setData(productCart);
                }
            }
        });
    }

    class ViewModelProviderFactory implements ViewModelProvider.Factory {
        private Application application;

        public ViewModelProviderFactory(Application application) {
            this.application = application;
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(CartViewModel.class)) {
                return (T) new CartViewModel(application);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
