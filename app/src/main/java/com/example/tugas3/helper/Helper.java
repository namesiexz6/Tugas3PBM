package com.example.tugas3.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME ="crud";

    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "Create TABLE mahasiswa (number INTEGER PRIMARY KEY autoincrement, npm INT NOT NULL, nama TEXT NOT NULL, jurusan TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(sqLiteDatabase);

    }
    public ArrayList<HashMap<String, String>> getAll(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM mahasiswa";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("number", cursor.getString(0));
                map.put("npm", cursor.getString(1));
                map.put("nama", cursor.getString(2));
                map.put("jurusan", cursor.getString(3));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void insert(String npm,String nama,String jurusan){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO mahasiswa (npm,nama,jurusan) VALUES('"+npm+"','"+nama+"','"+jurusan+"')";
        database.execSQL(QUERY);
    }
    public void  update(int number,String npm, String nama, String jurusan){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPADTE mahasiswa SET npm='"+npm+"','"+nama+"','"+jurusan+"' WHERE number = "+number;
        database.execSQL(QUERY);
    }
    public void delete(int number){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM mahasiswa WHERE number = "+number;
        database.execSQL(QUERY);
    }
}
