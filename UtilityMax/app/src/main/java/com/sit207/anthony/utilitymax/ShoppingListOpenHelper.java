//Anthony Ea 214126186 (aea@deakin.edu.au)
//Assignment 2 - SIT207
//Code for creating and managing the database.
//References used: pratical video tutorials.

package com.sit207.anthony.utilitymax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShoppingListOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEM = "item";
    public static final String COLUMN_AMOUNT = "amount";

    //Creates a database with the values above and properties below.
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_ITEM + " text not null, " + COLUMN_AMOUNT
            + " integer not null);";

    public ShoppingListOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}