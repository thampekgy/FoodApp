package com.example.windows10.newproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class FavorSQLHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "OrderDetail.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavorContract.Favor1.TABLE_NAME + "(" +
                    FavorContract.Favor1.COLUMN_ID + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FavorContract.Favor1.TABLE_NAME;


    public FavorSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);



    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
