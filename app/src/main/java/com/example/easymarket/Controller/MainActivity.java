package com.example.easymarket.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.easymarket.Model.DataStore;
import com.example.easymarket.R;
import com.example.easymarket.View.RcvProductsAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvProducts;
    private RcvProductsAdapter rcvProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStore.sharedInstance().setContext(this);

        rcvProducts = findViewById(R.id.rcvProducts);
        rcvProductsAdapter = new RcvProductsAdapter(DataStore.sharedInstance().getProducts(), this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rcvProducts.setLayoutManager(manager);
        rcvProducts.setAdapter(rcvProductsAdapter);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.registerReceiver(updateProducts, new IntentFilter("updateProducts"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {

            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
            broadcastManager.unregisterReceiver(updateProducts);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mnuAdd:

                Intent intent = new Intent(this, AddProductActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;

            case R.id.mnuClear:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Atenção");
                builder.setMessage("Tem certeza que deseja limpar a lista de cidades?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DataStore.sharedInstance().clear();
                        rcvProductsAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Não", null);
                builder.show();
                break;
        }

        return true;
    }

    private BroadcastReceiver updateProducts = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            rcvProductsAdapter.notifyDataSetChanged();
        }
    };

    private BroadcastReceiver updatePlaces = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            rcvProductsAdapter.notifyDataSetChanged();

        }
    };
}