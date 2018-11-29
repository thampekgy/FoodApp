package com.example.windows10.newproject.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.example.windows10.newproject.Model.Favor;


import java.util.ArrayList;
import java.util.List;

public class FavorDataSource {

    private SQLiteDatabase database;
    private FavorSQLHelper dbHelper;
    private String[] allColumn = {
            FavorContract.Favor1.COLUMN_ID
    };

    public FavorDataSource(Context context) {
        dbHelper = new FavorSQLHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertFavor(String foodId) {
        ContentValues values = new ContentValues();
        //values.put(Order.COLUMN_ID, orderRecord.getProductId());
        values.put(FavorContract.Favor1.COLUMN_ID, foodId);
        database = dbHelper.getWritableDatabase();
        database.insert(FavorContract.Favor1.TABLE_NAME, null, values);
        database.close();
    }

    public List<Favor> getAllFavor() {

        List<Favor> records = new ArrayList<>();
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(FavorContract.Favor1.TABLE_NAME, allColumn, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Favor favorRecord = new Favor();
            favorRecord.setFoodID(cursor.getString(0));
            records.add(favorRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }

    public void removeFavor(String foodId){
        database = dbHelper.getWritableDatabase();
        String query = String.format(" DELETE FROM favorDetail WHERE foodId='%s';", foodId);
        database.execSQL(query);
    }


    public Boolean isFavor(String foodId) {

        database = dbHelper.getWritableDatabase();
        String query = String.format(" SELECT * FROM favorDetail WHERE foodId='%s';", foodId);
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }
}