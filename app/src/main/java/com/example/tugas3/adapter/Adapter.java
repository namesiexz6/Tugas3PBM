package com.example.tugas3.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugas3.R;
import com.example.tugas3.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null && inflater != null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.lists_mahasiswa, null);
        }
        if (view != null) {
            TextView npm = view.findViewById(R.id.text_npm);
            TextView nama = view.findViewById(R.id.text_nama);
            TextView jurusan = view.findViewById(R.id.text_jurusan);
            Data data = lists.get(i);
            npm.setText(data.getNpm());
            nama.setText(data.getNama());
            jurusan.setText(data.getJurusan());
        }
        return view;
    }
}
