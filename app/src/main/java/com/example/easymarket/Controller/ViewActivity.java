package com.example.easymarket.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.easymarket.Model.DataStore;
import com.example.easymarket.Model.Product;
import com.example.easymarket.R;

public class ViewActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtCategory;
    private TextView txtDescription;
    private TextView txtQuantity;
    private TextView txtPrice;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        position = getIntent().getIntExtra("productPosition", 0);
        Product product = DataStore.sharedInstance().getProduct(position);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtDescription = findViewById(R.id.txtDescription);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtPrice = findViewById(R.id.txtPrice);

        txtName.setText(product.getName());
        txtCategory.setText(product.getCategory());
        txtDescription.setText(product.getDescription());
        txtQuantity.setText(String.valueOf(product.getQuantity()));
        txtPrice.setText(String.valueOf(product.getPrice()));

    }

    public void btnEditOnClick(View view) {

        Intent intent = new Intent(this, AddProductActivity.class);
        intent.putExtra("type", 2);
        intent.putExtra("cityPosition", position);
        startActivity(intent);
        finish();
    }

    public void btnDelOnClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage("Tem certeza que deseja remover esta cidade?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DataStore.sharedInstance().delProduct(position);
                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(ViewActivity.this);
                manager.sendBroadcast(new Intent("updateCities"));
                finish();
            }
        });
        builder.setNegativeButton("Não", null);
        builder.show();
    }
}
