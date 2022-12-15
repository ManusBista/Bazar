package com.example.baraz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baraz.R;
import com.example.baraz.activities.ProductDetailActivity;
import com.example.baraz.databinding.ItemProductBinding;
import com.example.baraz.model.Product;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.imagePro);
        holder.binding.valuePro.setText(product.getName());
        holder.binding.pricePro.setText("$"+product.getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, ProductDetailActivity.class);
            i.putExtra("name",product.getName());
            i.putExtra("image",product.getImage());
            i.putExtra("id",product.getId());
            i.putExtra("price",product.getPrice());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ItemProductBinding binding;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemProductBinding.bind(itemView);
        }
    }
}
