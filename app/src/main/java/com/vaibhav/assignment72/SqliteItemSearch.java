package com.vaibhav.assignment72;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class SqliteItemSearch extends SQLiteOpenHelper {
    private static final String DB_NAME = "Products.db";
    private static final int DB_VERSION_NUMBER = 1;
    private static final String DB_TABLE_NAME = "itemsearch";
    private static final String DB_COLUMN_1_NAME = "item_name";

    private static final String DB_CREATE_SCRIPT = "create table "
            + DB_TABLE_NAME
            + " (_id integer primary key autoincrement, item_name text not null);)";

    private SQLiteDatabase sqliteDBInstance = null;

    SqliteItemSearch(Context context) {
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDBInstance) {
        Log.i("onCreate", "Creating the database...");
        sqliteDBInstance.execSQL(DB_CREATE_SCRIPT);

        this.sqliteDBInstance=sqliteDBInstance;
        insertitemSearch("Color Monitor");
        insertitemSearch("Compact Disk");
        insertitemSearch("Computer");
        insertitemSearch("Hard Disk");
        insertitemSearch("HP Printer");
        insertitemSearch("HP Laser Printer");
        insertitemSearch("HP Injet Printer");
    }

    void openDB() throws SQLException {
        Log.i("openDB", "Checking sqliteDBInstance...");
        if (this.sqliteDBInstance == null) {
            Log.i("openDB", "Creating sqliteDBInstance...");
            this.sqliteDBInstance = this.getWritableDatabase();
        }
    }

    private long insertitemSearch(String ItemBrandName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN_1_NAME, ItemBrandName);
        Log.i(this.toString() + " - insertitmSearch", "Inserting: "
                + ItemBrandName);
        return this.sqliteDBInstance.insert(DB_TABLE_NAME, null, contentValues);
    }

    String[] getAllItemFilter() {
        Cursor cursor = this.sqliteDBInstance.query(DB_TABLE_NAME, new String[] { DB_COLUMN_1_NAME }, null,
                        null, null, null, null);

        if (cursor.getCount() > 0) {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext()) {
                str[i] = cursor.getString(cursor
                        .getColumnIndex(DB_COLUMN_1_NAME));
                i++;
            }
            cursor.close();
            return str;
        } else {
            return new String[] {};
        }
    }
}
