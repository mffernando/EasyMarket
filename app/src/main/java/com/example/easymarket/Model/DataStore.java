package com.example.easymarket.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static DataStore instance = null;

    private List<Product> products;
    private ProductDatabase database;
    private Context context;

    public static DataStore sharedInstance() {

        if (instance == null) {

            instance = new DataStore();
        }

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
        database = new ProductDatabase(context);
        products = database.getProducts();
    }

    private DataStore() {

        products = new ArrayList<>();
    }

    public List<Product> getProducts() {

        return products;
    }

    public Product getProduct(int position) {

        return products.get(position);
    }

    public void addProduct(Product product) {

        database.addProduct(product);
        products.add(product);
    }

    public void editProduct(Product product, int position) {

        Product oldProduct = getProduct(position);
        oldProduct.setName(product.getName());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setPrice(product.getPrice());

        database.editProduct(oldProduct);
        products.set(position, oldProduct);
    }

    public void delProduct(int position) {

        database.delProduct(getProduct(position));
        products.remove(position);
    }

    public void clear() {

        products.clear();
    }
}
