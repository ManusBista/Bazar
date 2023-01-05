package com.example.baraz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baraz.R;
import com.example.baraz.databinding.ItemCartBinding;
import com.example.baraz.databinding.ItemCategoriesBinding;
import com.example.baraz.databinding.QuantityBinding;
import com.example.baraz.model.Category;
import com.example.baraz.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<Product> products;
    CartListener cartListener;
    Cart cart;

    public interface CartListener {
        public void onQuantityChanged();
    }

    public CartAdapter(Context context, ArrayList<Product> products, CartListener cartListener) {
        this.context = context;
        this.products = products;
        this.cartListener = cartListener;
        cart = TinyCartHelper.getCart();

    }


    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.imgCart);

        holder.binding.cartName.setText(product.getName());
        holder.binding.cartPrice.setText("$" + product.getPrice());
//        holder.binding.quantity.setText(product.getQuantity() + " item(s)");

//        ItemCartBinding itemCartBinding  = ItemCartBinding.inflate(LayoutInflater.from(context));

//        itemCartBinding.productName.setText(product.getName());
//        itemCartBinding.productStock.setText("Stock: " + product.getStock());
        holder.binding.quantity.setText(String.valueOf(product.getQuantity()));

        int stock = product.getStock();

        holder.binding.plusBtn.setOnClickListener(view1 -> {
            int quantity = product.getQuantity();
            quantity++;

            if (quantity > product.getStock()) {
                Toast.makeText(context, "Max stock available: " + product.getStock(), Toast.LENGTH_SHORT).show();
                return;
            } else {
                product.setQuantity(quantity);
                holder.binding.quantity.setText(String.valueOf(quantity));
            }

            notifyDataSetChanged();
            cart.updateItem(product, product.getQuantity());
            cartListener.onQuantityChanged();
        });

        holder.binding.minusBtn.setOnClickListener(view12 -> {
            int quantity = product.getQuantity();
            if (quantity > 1)
                quantity--;
            product.setQuantity(quantity);
            holder.binding.quantity.setText(String.valueOf(quantity));


            notifyDataSetChanged();
            cart.updateItem(product, product.getQuantity());
            cartListener.onQuantityChanged();
        });

//        holder.bindingcart.clearCart();
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding binding;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCartBinding.bind(itemView);
        }

    }


}

