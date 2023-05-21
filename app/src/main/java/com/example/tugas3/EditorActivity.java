package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas3.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editNpm,editNama,editJurusan;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String number,npm,nama,jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editNpm = findViewById(R.id.edit_npm);
        editNama = findViewById(R.id.edit_nama);
        editJurusan = findViewById(R.id.edit_jurusan);
        btnSave = findViewById(R.id.btn_save);

        number = getIntent().getStringExtra("number");
        npm = getIntent().getStringExtra("npm");
        nama = getIntent().getStringExtra("nama");
        jurusan = getIntent().getStringExtra("jurusan");

        if(number == null || number.equals("")){
            setTitle("Tambah Mahasiswa");
        }else{
            setTitle("Edit Mahasiswa");
            editNpm.setText(npm);
            editNama.setText(nama);
            editJurusan.setText(jurusan);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (number == null || number.equals("")){
                        save();
                    }else {
                        edit();
                    }

                }catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }
    private void save(){
        if (String.valueOf(editNpm.getText()).equals("") || String.valueOf(editNama.getText()).equals("") || String.valueOf(editJurusan.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Silakan isi Semua Data!", Toast.LENGTH_LONG).show();
        }else {
            db.insert(editNpm.getText().toString(), editNama.getText().toString(), editJurusan.getText().toString());
            finish();
        }
    }
    private void edit(){
        if (String.valueOf(editNpm.getText()).equals("") || String.valueOf(editNama.getText()).equals("") || String.valueOf(editJurusan.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Silakan isi Semua Data!", Toast.LENGTH_LONG).show();
        }else {
            db.update(Integer.parseInt(number), editNpm.getText().toString(), editNama.getText().toString(), editJurusan.getText().toString());
            finish();
        }
    }
}