package com.example.houserentalmanagement.houseOwner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordOwenr extends AppCompatActivity {

    EditText et_sendEmail;
    Button btn_reset;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_owenr);

        et_sendEmail = findViewById(R.id.et_sendEmail);
        btn_reset = findViewById(R.id.btn_reset);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_reset.setOnClickListener(v -> {
            String email = et_sendEmail.getText().toString();
            if (email.equals("")){
                Toast.makeText(ResetPasswordOwenr.this, "Email is empty", Toast.LENGTH_SHORT).show();
            }else{
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(ResetPasswordOwenr.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPasswordOwenr.this, LoginOwner.class));
                    }else{
                        String error = task.getException().getMessage();
                        Toast.makeText(ResetPasswordOwenr.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}