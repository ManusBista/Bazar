package com.example.baraz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.baraz.R;
import com.example.baraz.databinding.ActivityPaymentBinding;
import com.example.baraz.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.splashBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(Splash.this, LoginActivity.class);
//            startActivity(intent);
//        });

        Intent intent = new Intent(Splash.this, Continue.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        },3000);
    }
}