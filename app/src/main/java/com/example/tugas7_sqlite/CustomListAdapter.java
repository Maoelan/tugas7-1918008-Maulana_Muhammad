package com.example.tugas7_sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Tumbuhan> Tumbuhan;
    public CustomListAdapter(Activity activity, List<Tumbuhan> Tumbuhan) {
        this.activity = activity;
        this.Tumbuhan = Tumbuhan;
    }
    @Override
    public int getCount() {
        return Tumbuhan.size();
    }
    @Override
    public Object getItem(int location) {
        return Tumbuhan.get(location);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_list, null);
        TextView nama = (TextView)
                convertView.findViewById(R.id.text_nama);
        TextView kelas = (TextView)
                convertView.findViewById(R.id.text_harga);
        ImageView imageView = (ImageView)
                convertView.findViewById(R.id.iconid);
        Tumbuhan t = Tumbuhan.get(position);
        nama.setText("Nama Tumbuhan : "+ t.get_nama());
        kelas.setText("Harga Tumbuhan : "+ t.get_harga());
        return convertView;
    }
}
