package com.example.tugas3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.tugas3.adapter.Adapter;
import com.example.tugas3.helper.Helper;
import com.example.tugas3.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String number = lists.get(i).getNumber();
                final String npm = lists.get(i).getNpm();
                final String nama = lists.get(i).getNama();
                final String jurusan = lists.get(i).getJurusan();
                final CharSequence[] dialogitem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("number",number);
                                intent.putExtra("npm", npm);
                                intent.putExtra("nama", nama);
                                intent.putExtra("jurusan", jurusan);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(number));
                                lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }
    private void getData(){
        ArrayList<HashMap<String, String>> rows =  db.getAll();
        for (int i = 0; i< rows.size(); i++){
            String number = rows.get(i).get("number");
            String npm = rows.get(i).get("npm");
            String nama = rows.get(i).get("nama");
            String jurusan = rows.get(i).get("jurusan");

            Data data = new Data();
            data.setNumber(number);
            data.setNpm(npm);
            data.setNama(nama);
            data.setJurusan(jurusan);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}