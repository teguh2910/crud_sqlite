package com.example.crud_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    EditText username,nim,password;
    Button daftar,login;

    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        nim = findViewById(R.id.nim);
        password = findViewById(R.id.password);
        daftar = findViewById(R.id.daftarbtn);
        login = findViewById(R.id.loginback);
        DB = new DBhelper(this);

        daftar.setOnClickListener(v -> {
            String usernameTXT=username.getText().toString();
            String nimTXT=nim.getText().toString();
            String passwordTXT=password.getText().toString();

            boolean checkdaftar=DB.insertuserdata(usernameTXT,nimTXT,passwordTXT);
            if(checkdaftar){
                Toast.makeText(register.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(register.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(v -> {
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
            finish();
        });
    }
}