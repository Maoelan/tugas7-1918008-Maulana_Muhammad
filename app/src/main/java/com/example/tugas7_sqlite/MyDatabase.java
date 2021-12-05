package com.example.tugas7_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_kebun";
    private static final String tb_tumbuhan = "tb_tumbuhan";
    private static final String tb_tumbuhan_id = "id";
    private static final String tb_tumbuhan_nama = "nama";
    private static final String tb_tumbuhan_harga = "kelas";
    private static final String CREATE_TABLE_TUMBUHAN = "CREATE TABLE "
            + tb_tumbuhan +"("
            + tb_tumbuhan_id + " INTEGER PRIMARY KEY ,"
            + tb_tumbuhan_nama + " TEXT ,"
            + tb_tumbuhan_harga + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TUMBUHAN);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreateTumbuhan(Tumbuhan data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_tumbuhan_id, data.get_id());
        values.put(tb_tumbuhan_nama, data.get_nama());
        values.put(tb_tumbuhan_harga, data.get_harga());
        db.insert(tb_tumbuhan, null, values);
        db.close();
    }
    public List<Tumbuhan> ReadTumbuhan() {
        List<Tumbuhan> listMhs = new ArrayList<Tumbuhan>();
        String selectQuery = "SELECT * FROM " + tb_tumbuhan;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Tumbuhan data = new Tumbuhan();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int UpdateTumbuhan (Tumbuhan data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_tumbuhan_nama, data.get_nama());
        values.put(tb_tumbuhan_harga, data.get_harga());
        return db.update(tb_tumbuhan, values, tb_tumbuhan_id + " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteTumbuhan(Tumbuhan data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_tumbuhan,tb_tumbuhan_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}

