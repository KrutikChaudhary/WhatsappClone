package com.example.whatsaapcloneapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.whatsaapcloneapp.R;
import com.example.whatsaapcloneapp.databinding.ActivityLoginBinding;
import com.example.whatsaapcloneapp.viewmodel.MyViewModel;

public class LoginActivity extends AppCompatActivity {
    MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        activityLoginBinding.setVModel(viewModel);
    }
}