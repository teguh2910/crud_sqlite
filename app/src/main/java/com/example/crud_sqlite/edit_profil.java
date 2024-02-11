package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit_profil extends AppCompatActivity {
    EditText username,nim,password;
    Button update,back;
    DBhelper DB;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        username = findViewById(R.id.username);
        nim = findViewById(R.id.nim);
        password = findViewById(R.id.password);
        update = findViewById(R.id.btn_update);
        back = findViewById(R.id.btn_back);
        DB = new DBhelper(this);
        sessionManager = new SessionManager(this);
        String usernamelogin = sessionManager.getUsername();
        username.setText(usernamelogin);
        update.setOnClickListener(v -> {
            String usernameTxt = username.getText().toString();
            String nimTxt = nim.getText().toString();
            String passwordTxt = password.getText().toString();
            boolean chekupdate = DB.updateuserdata(usernameTxt,nimTxt,passwordTxt);
            if (chekupdate){
                Toast.makeText(edit_profil.this, "Sukses Update Data", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(edit_profil.this, "Gagal update data", Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(v -> {
            Intent intent = new Intent(edit_profil.this, homepage.class);
            startActivity(intent);
            finish();
        });
    }
}