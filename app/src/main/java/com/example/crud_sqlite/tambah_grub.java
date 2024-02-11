package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah_grub extends AppCompatActivity {
    EditText nama,deskripsi;
    Button save,back;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_grub);
        nama = findViewById(R.id.judul_grub);
        deskripsi = findViewById(R.id.isi_grub);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        DB = new DBhelper(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaTXT = nama.getText().toString();
                String deskripsiTXT = deskripsi.getText().toString();
                Boolean checkinsertgrub = DB.insertgrub(namaTXT,deskripsiTXT);
                if (checkinsertgrub==true){
                    Toast.makeText(tambah_grub.this, "Suskes", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tambah_grub.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tambah_grub.this, homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}