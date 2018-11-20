package com.example.windows10.newproject.Database;

import android.provider.BaseColumns;

public final class OrderContract {

    public OrderContract() {}
        public static abstract class Order implements BaseColumns {
            public static final String TABLE_NAME = "OrderDetail";
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_PRODUCTNAME = "productName";
            public static final String COLUMN_QUANTITY = "quantity";
            public static final String COLUMN_PRICE = "price";
            public static final String COLUMN_DISCOUNT = "discount";


        }


}