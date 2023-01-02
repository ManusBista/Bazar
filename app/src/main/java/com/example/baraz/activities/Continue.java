package com.example.baraz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
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
        // Create a new shape drawable
        ShapeDrawable drawable = new ShapeDrawable();

        float radius = 15;
        drawable.setShape(new RoundRectShape(new float[]{radius, radius, radius, radius, radius, radius, radius, radius}, null, null));
// Set the shape's stroke width and color
        drawable.getPaint().setStrokeWidth(8);
        drawable.getPaint().setColor(Color.BLACK);
        drawable.getPaint().setStyle(Paint.Style.STROKE);



// Set the button's background to the drawable
        binding.signUpBtn.setBackground(drawable);

        binding.cuntBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Continue.this, MainActivity.class);
            startActivity(intent);
        });

//        binding.img.setImageResource(R.drawable.logo);
    }
}