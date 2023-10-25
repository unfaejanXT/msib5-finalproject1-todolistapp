package com.hactiv8.todolist3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "DB_LIST_TO_DO";
    private static final int DB_VERSION = 1;

    //Table
    private static final String TABLE_ListToDo = "LIST_TO_DO";
    //Colom
    private static final String ID_ListToDo = "idListToDo";
    private static final String NAME_ListToDo = "nameListToDo";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_LIST_TO_DO = "CREATE TABLE " +TABLE_ListToDo+ " ( "
                +ID_ListToDo+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME_ListToDo+ " TEXT ) ";
        System.out.println("Create Table : " +CREATE_TABLE_LIST_TO_DO);
        db.execSQL(CREATE_TABLE_LIST_TO_DO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_ListToDo);
        onCreate(db);
    }

    //menambahkan data tabel
    public void AddListToDo(ListToDoClass listToDoClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_ListToDo, listToDoClass.getNameListToDo());

        db.insert(TABLE_ListToDo, null, contentValues);
        db.close();
    }

    //hapus data tabel
    public void DeleteListToDo(ListToDoClass listToDoClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ListToDo, ID_ListToDo+ " = ? ", new String[]{String.valueOf(listToDoClass.getIdListToDo())});
        db.close();
    }

    //Edit data tabel
    public int EditListToDo(ListToDoClass listToDoClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_ListToDo, listToDoClass.getNameListToDo());

        return db.update(TABLE_ListToDo, contentValues,ID_ListToDo+ " = ? ", new String[]{String.valueOf(listToDoClass.getIdListToDo())});
    }

    //Menampilkan isi dari tabel
    public List<ListToDoClass> getAllListToDo(){
        List<ListToDoClass> toDoClassList = new ArrayList<>();

        String query = " SELECT * FROM " +TABLE_ListToDo;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ListToDoClass listToDoClass = new ListToDoClass();
                listToDoClass.setIdListToDo(Integer.parseInt(cursor.getString(0)));
                listToDoClass.setNameListToDo(cursor.getString(1));

                toDoClassList.add(listToDoClass);
            }while (cursor.moveToNext());
        }
        return toDoClassList;
    }
}
