package com.example.easymarket.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.easymarket.Model.DataStore;
import com.example.easymarket.Model.Product;
import com.example.easymarket.R;


public class AddProductActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtCategory;
    private TextView txtDescription;
    private TextView txtQuantity;
    private TextView txtPrice;

    private int type;
    private int productPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtDescription = findViewById(R.id.txtDescription);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtPrice = findViewById(R.id.txtPrice);

        type = getIntent().getIntExtra("type", 0);
        if (type == 2) {

            productPosition = getIntent().getIntExtra("productPosition", 0);
            Product product = DataStore.sharedInstance().getProduct(productPosition);
            txtName.setText(product.getName());
            txtCategory.setText(product.getCategory());
            txtDescription.setText(product.getDescription());
            txtQuantity.setText(String.valueOf(product.getQuantity()));
            txtPrice.setText(String.valueOf(product.getPrice()));

        }
    }

    public void btnSaveOnClick(View view) {

        Product product = new Product(
                txtName.getText().toString(),
                txtCategory.getText().toString(),
                txtDescription.getText().toString(),
                Integer.parseInt(txtQuantity.getText().toString()),
                Integer.parseInt(txtPrice.getText().toString())
        );
        if (type == 1)
            DataStore.sharedInstance().addProduct(product);
        else
            DataStore.sharedInstance().editProduct(product, productPosition);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(new Intent("updateProducts"));
        finish();
    }
}
