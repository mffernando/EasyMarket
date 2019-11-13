package com.example.easymarket.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easymarket.Controller.ViewActivity;
import com.example.easymarket.Model.Product;
import com.example.easymarket.R;

import java.util.List;

public class RcvProductsAdapter extends RecyclerView.Adapter<RcvProductsAdapter.ProductHolder> {

    private List<Product> products;
    private Context context;

    public RcvProductsAdapter(List<Product> products, Context context) {

        this.products = products;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rcv_products, viewGroup, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, final int position) {

        Product product = products.get(position);

        holder.txtName.setText(product.getName());
        holder.txtCategory.setText(product.getCategory());
        holder.txtDescription.setText(product.getDescription());
        holder.txtQuantity.setText(String.valueOf(product.getQuantity()));
        holder.txtPrice.setText(String.valueOf(product.getPrice()));
        holder.layCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra("cityPosition", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        public LinearLayout layCity;
        public TextView txtName;
        public TextView txtCategory;
        public TextView txtDescription;
        public TextView txtQuantity;
        public TextView txtPrice;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            layCity = itemView.findViewById(R.id.layCity);
            txtName = itemView.findViewById(R.id.txtName);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}