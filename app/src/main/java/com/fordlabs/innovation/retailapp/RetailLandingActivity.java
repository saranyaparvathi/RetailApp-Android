package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fordlabs.innovation.retailapp.databinding.ActivityRetailLandingBinding;

import java.util.List;

import data.ProductCart;

public class RetailLandingActivity extends AppCompatActivity {

    RetailLandingViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRetailLandingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_retail_landing);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ViewModelProviderFactory factory =
                new ViewModelProviderFactory(this.getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(RetailLandingViewModel.class);
        binding.setViewModel(viewModel);
    }

    class ViewModelProviderFactory implements ViewModelProvider.Factory {
        private Application application;

        public ViewModelProviderFactory(Application application) {
            this.application = application;
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(RetailLandingViewModel.class)) {
                return (T) new RetailLandingViewModel(application);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
