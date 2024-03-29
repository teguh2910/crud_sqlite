package com.example.crud_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;

public class login extends AppCompatActivity {
    Button btnlogin,register;
    EditText username,password;
    DBhelper DB;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin = findViewById(R.id.btnlogin);
        register = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        DB = new DBhelper(this);
        sessionManager = new SessionManager(this);

        btnlogin.setOnClickListener(v -> {
            String usernameTXT = username.getText().toString();
            String passwordTXT = password.getText().toString();
            sessionManager.setUsername(usernameTXT);
            boolean check = DB.checklogin(usernameTXT,passwordTXT);
            if (check==true){
                Toast.makeText(login.this, "Sukses", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, homepage.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, register.class);
            startActivity(intent);
            finish();
        });
    }
}