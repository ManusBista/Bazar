package com.example.baraz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.baraz.R;
import com.example.baraz.databinding.ActivityContinueBinding;
import com.example.baraz.databinding.ActivitySplashBinding;

public class Continue extends AppCompatActivity {

    ActivityContinueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContinueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cuntBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Continue.this, MainActivity.class);
            startActivity(intent);
        });

        binding.loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Continue.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.signupBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Continue.this, RegisterActivity.class);
            startActivity(intent);
        });


//        binding.img.setImageResource(R.drawable.logo);
    }
}