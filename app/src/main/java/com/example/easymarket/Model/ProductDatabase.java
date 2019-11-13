package com.example.easymarket.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "products.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "products";

    private Context context;

    public ProductDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "category TEXT," +
                "description TEXT," +
                "quantity INTEGER," +
                "price INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            switch (oldVersion + 1) {
                case 2:

                case 3:

            }
        }
    }

    public void addProduct(Product product) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", product.getName());
        values.put("category", product.getCategory());
        values.put("description", product.getDescription());
        values.put("quantity", product.getQuantity());
        values.put("price", product.getPrice());

        long id = db.insert(DB_TABLE, "", values);
        product.setId(id);
        db.close();
    }

    public void editProduct(Product product) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", product.getName());
        values.put("category", product.getCategory());
        values.put("description", product.getDescription());
        values.put("quantity", product.getQuantity());
        values.put("price", product.getPrice());

        String _id = String.valueOf(product.getId());
        int count = db.update(DB_TABLE, values, "_id=?", new String[]{_id});
        db.close();
    }

    public void delProduct(Product product) {

        SQLiteDatabase db = getWritableDatabase();

        String _id = String.valueOf(product.getId());
        int count = db.delete(DB_TABLE, "_id=?", new String[]{_id});
        db.close();
    }

    public List<Product> getProducts() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DB_TABLE, null, null, null, null, null, "name");
        List<Product> products = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("category")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getInt(cursor.getColumnIndex("quantity")),
                        cursor.getInt(cursor.getColumnIndex("price"))
                );

                products.add(product);
            } while (cursor.moveToNext());
        }

        return products;
    }
}

