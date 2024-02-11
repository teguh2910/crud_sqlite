package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah_postingan extends AppCompatActivity {
    EditText judul,isi;
    Button posting,back;
    DBhelper DB;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_postingan);
        judul = findViewById(R.id.judul_postingan);
        isi = findViewById(R.id.isi_postingan);
        posting = findViewById(R.id.posting);
        back = findViewById(R.id.back);
        DB = new DBhelper(this);
        sessionManager = new SessionManager(this);

        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judulTXT = judul.getText().toString();
                String username = sessionManager.getUsername();
                String isiTXT = isi.getText().toString();
                Boolean checkpostingan = DB.insertpostingan(username,judulTXT,isiTXT);
                if (checkpostingan==true){
                    Toast.makeText(tambah_postingan.this, "Sukses Posting", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tambah_postingan.this, "Gagal Posting", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tambah_postingan.this, homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}