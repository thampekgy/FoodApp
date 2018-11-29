package com.example.windows10.newproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.windows10.newproject.Model.OrderRecord;
import com.example.windows10.newproject.Database.OrderContract.Order;


import java.util.ArrayList;
import java.util.List;

public class OrderDataSource {

        private SQLiteDatabase database;
        private OrderSQLHelper dbHelper;
        private String[] allColumn = {
                Order.COLUMN_ID,
                Order.COLUMN_PRODUCTNAME,
                Order.COLUMN_QUANTITY,
                Order.COLUMN_PRICE,
                Order.COLUMN_DISCOUNT
        };

        public OrderDataSource(Context context) {
            dbHelper = new OrderSQLHelper(context);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public void insertOrder(OrderRecord orderRecord) {
            ContentValues values = new ContentValues();
            values.put(Order.COLUMN_ID, orderRecord.getProductId());
            values.put(Order.COLUMN_PRODUCTNAME, orderRecord.getProductName());
            values.put(Order.COLUMN_QUANTITY, orderRecord.getQuantity());
            values.put(Order.COLUMN_PRICE, orderRecord.getPrice());
            values.put(Order.COLUMN_DISCOUNT, orderRecord.getDiscount());
            database = dbHelper.getWritableDatabase();
            database.insert(Order.TABLE_NAME, null, values);
            database.close();
        }

        public List<OrderRecord> getAllOrder() {

            List<OrderRecord> records = new ArrayList<>();
            database= dbHelper.getWritableDatabase();
            Cursor cursor = database.query(OrderContract.Order.TABLE_NAME,allColumn,null,null,null,null,null);

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                OrderRecord orderRecord = new OrderRecord();
                orderRecord.setProductId(cursor.getString(0));
                orderRecord.setProductName(cursor.getString(1));
                orderRecord.setQuantity(cursor.getString(2));
                orderRecord.setPrice(cursor.getString(3));
                orderRecord.setDiscount(cursor.getString(4));
                records.add(orderRecord);
                cursor.moveToNext();
            }
            cursor.close();
            return records;
        }
    }
