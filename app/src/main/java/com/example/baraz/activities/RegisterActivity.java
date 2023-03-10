package com.example.baraz.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baraz.R;
import com.example.baraz.databinding.ActivityLoginBinding;
import com.example.baraz.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword,register_confirm_pass;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        register_confirm_pass = findViewById(R.id.register_confirm_pass);
        signupButton.setOnClickListener(view -> {
            String user = signupEmail.getText().toString().trim();
            String pass = signupPassword.getText().toString().trim();
            String conf_pass = register_confirm_pass.getText().toString().trim();

            if (user.isEmpty()){
                signupEmail.setError("Email cannot be empty");
            }
            if (pass.isEmpty()){
                signupPassword.setError("Password cannot be empty");
            }
            if (conf_pass.isEmpty()) {
                register_confirm_pass.setError("Field cannot be empty");
            }
            else{
                auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && pass.equals(conf_pass)) {
                            Toast.makeText(RegisterActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loginRedirectText.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}