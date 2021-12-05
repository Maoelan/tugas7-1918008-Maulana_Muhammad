package com.example.tugas7_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Tumbuhan> ListTumbuhan = new ArrayList<Tumbuhan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListTumbuhan);
        mListView = (ListView) findViewById(R.id.list_tumbuhan);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListTumbuhan.clear();
        List<Tumbuhan> tumbuhan = db.ReadTumbuhan();
        for (Tumbuhan tmh : tumbuhan) {
            Tumbuhan daftar = new Tumbuhan();
            daftar.set_id(tmh.get_id());
            daftar.set_nama(tmh.get_nama());
            daftar.set_harga(tmh.get_harga());
            ListTumbuhan.add(daftar);
            if ((ListTumbuhan.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Tumbuhan detailTmh = (Tumbuhan) o;
        String Sid = detailTmh.get_id();
        String Snama = detailTmh.get_nama();
        String Sharga = detailTmh.get_harga();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListTumbuhan.clear();
        mListView.setAdapter(adapter_off);
        List<Tumbuhan> tumbuhan = db.ReadTumbuhan();
        for (Tumbuhan tmh : tumbuhan) {
            Tumbuhan daftar = new Tumbuhan();
            daftar.set_id(tmh.get_id());
            daftar.set_nama(tmh.get_nama());
            daftar.set_harga(tmh.get_harga());
            ListTumbuhan.add(daftar);
            if ((ListTumbuhan.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}

