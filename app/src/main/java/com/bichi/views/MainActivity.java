package com.bichi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bichi.R;
import com.bichi.databinding.ActivityMainBinding;
import com.bichi.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        activityMainBinding.setViewModel(userViewModel);
        activityMainBinding.executePendingBindings();
    }
}
