package com.example.agridost;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agridost.models.SignupRequest;
import com.example.agridost.models.SignupResponse;
import com.example.agridost.network.ApiService;
import com.example.agridost.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etDob, etAddress;
    private Button btnSignup;
    private com.google.firebase.auth.FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmailSignup);
        etPassword = findViewById(R.id.etPasswordSignup);
        etDob = findViewById(R.id.etDob);
        etAddress = findViewById(R.id.etAddress);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                        new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(
                                    @androidx.annotation.NonNull com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Signup Successful! Please Login.",
                                            Toast.LENGTH_SHORT).show();
                                    finish(); // Go back to Login
                                } else {
                                    Toast.makeText(SignupActivity.this,
                                            "Signup Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });
    }
}
