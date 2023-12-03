package com.example.cse489_2023_3_2020260187;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import androidx.annotation.Nullable;

class ClassSummaryDB extends SQLiteOpenHelper{

//    private Context context;

    public ClassSummaryDB(@Nullable Context context) {
        super(context, "ClassSummaryDB.db", null, 1);
//        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        System.out.println("DB@OnCreate");
        String sql = "CREATE TABLE ClassSummary  ("
                + "ID TEXT PRIMARY KEY,"
                + "name TEXT,"
                + "course TEXT,"
                + "type TEXT,"
                + "date TEXT,"
                + "lecture INTEGER,"
                + "topic TEXT,"
                + "summary TEXT"
                + ")";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        System.out.println("Write code to modify database schema here");
//        // db.execSQL("ALTER table my_table  ......");
//        // db.execSQL("CREATE TABLE  ......");

    }
        public void insertClassSummary(String ID, String name, String course, String type, String date, int lecture, String topic, String summary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cols = new ContentValues();
        cols.put("ID", ID);
        cols.put("name", name);
        cols.put("course", course);
        cols.put("type", type);
        cols.put("date", date);
        cols.put("lecture", lecture);
        cols.put("topic", topic);


        cols.put("summary", summary);
        db.insert("ClassSummary", null ,  cols);
        db.close();
    }

    public void updateClassSummary(String ID, String name, String course, String type, String date, int lecture,String topic, String summary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ContentValues cols = new ContentValues();
        cols.put("ID", ID);
        cols.put("name", name);
        cols.put("course", course);
        cols.put("type", type);
        cols.put("date", date);
        cols.put("lecture", lecture);
        cols.put("topic", topic);


        cols.put("summary", summary);
        db.update("ClassSummary", cols, "ID=?", new String[ ] {ID} );
        db.close();
    }

    public void deleteClassSummary(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ClassSummary", "ID=?", new String[ ] {ID} );
        db.close();
    }

        public Cursor selectClassSummary(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery(query, null);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }



}

