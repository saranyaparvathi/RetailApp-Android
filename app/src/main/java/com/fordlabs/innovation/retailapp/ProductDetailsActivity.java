package com.fordlabs.innovation.retailapp;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fordlabs.innovation.retailapp.databinding.ActivityProductDetailsBinding;

public class ProductDetailsActivity extends AppCompatActivity {

    ProductDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        ViewModelProviderFactory factory =
                new ViewModelProviderFactory(this.getApplication(), this);
        viewModel = ViewModelProviders.of(this, factory).get(ProductDetailsViewModel.class);
        binding.setViewModel(viewModel);
    }

    class ViewModelProviderFactory implements ViewModelProvider.Factory {
        private Application application;
        private Context context;

        public ViewModelProviderFactory(Application application, Context context) {
            this.application = application;
            this.context = context;
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ProductDetailsViewModel.class)) {
                return (T) new ProductDetailsViewModel(application, context);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
