package com.example.baraz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.baraz.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}