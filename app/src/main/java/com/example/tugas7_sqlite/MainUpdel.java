package com.example.tugas7_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainUpdel extends AppCompatActivity {
    private MyDatabase db;
    private String Sid, Snama, Sharga;
    private EditText Enama, Eharga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_updel);
        db = new MyDatabase(this);
        Intent i = this.getIntent();
        Sid = i.getStringExtra("Iid");
        Snama = i.getStringExtra("Inama");
        Sharga = i.getStringExtra("Iharga");
        Enama = (EditText) findViewById(R.id.updel_nama);
        Eharga = (EditText) findViewById(R.id.updel_harga);
        Enama.setText(Snama);
        Eharga.setText(Sharga);
        Button btnUpdate = (Button) findViewById(R.id.btn_up);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Sharga = String.valueOf(Eharga.getText());
                if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi nama tumbuhan", Toast.LENGTH_SHORT).show();
                } else if (Sharga.equals("")){
                    Eharga.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi harga tumbuhan", Toast.LENGTH_SHORT).show();
                } else {
                    db.UpdateTumbuhan(new Tumbuhan(Sid, Snama, Sharga));
                    Toast.makeText(MainUpdel.this, "Data telah diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Button btnDelete = (Button) findViewById(R.id.btn_del);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeleteTumbuhan(new Tumbuhan(Sid, Snama, Sharga));
                Toast.makeText(MainUpdel.this, "Data telah tumbuhan dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
