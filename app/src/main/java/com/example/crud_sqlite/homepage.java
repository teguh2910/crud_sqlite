package com.example.crud_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class homepage extends AppCompatActivity {
    TextView usernameTextView;
    Button edit,posting,view_posting,list_user,grub;
    DBhelper DB;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        usernameTextView = findViewById(R.id.username);
        edit = findViewById(R.id.edit_pengguna);
        posting = findViewById(R.id.posting);
        view_posting = findViewById(R.id.view_posting);
        list_user = findViewById(R.id.list_user);
        grub = findViewById(R.id.grub);
        sessionManager = new SessionManager(this);
        DBhelper DB = new DBhelper(this);
        String username = sessionManager.getUsername();

        if (username!=null) {
            usernameTextView.setText(username);
        }else{
            Intent intent = new Intent(homepage.this, login.class);
            startActivity(intent);
            finish();
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, edit_profil.class);
                startActivity(intent);
                finish();
            }
        });
        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, tambah_postingan.class);
                startActivity(intent);
                finish();
            }
        });
        grub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, tambah_grub.class);
                startActivity(intent);
                finish();
            }
        });
        view_posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getpostingan();
                if (res.getCount()==0){
                    Toast.makeText(homepage.this, "Belum ada postingan", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Author : "+res.getString(0)+"\n");
                    buffer.append("Judul : "+res.getString(1)+"\n");
                    buffer.append("Isi : "+res.getString(2)+"\n");
                    buffer.append("Like "+"\n");
                    buffer.append("Share "+"\n");
                    buffer.append("Komentar "+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(homepage.this);
                builder.setCancelable(true);
                builder.setTitle("List Postingan");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        list_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(homepage.this, "Belum ada postingan", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("username : "+res.getString(0)+"\n");
                    buffer.append("nim : "+res.getString(1)+"\n");
                    buffer.append("Follow "+"\n");
                    buffer.append("Message "+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(homepage.this);
                builder.setCancelable(true);
                builder.setTitle("List Postingan");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}