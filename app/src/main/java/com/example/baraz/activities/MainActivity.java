package com.example.baraz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.baraz.R;
import com.example.baraz.adapter.CategoryAdapter;
import com.example.baraz.adapter.ProductAdapter;
import com.example.baraz.databinding.ActivityMainBinding;
import com.example.baraz.model.Category;
import com.example.baraz.model.Product;
import com.example.baraz.utils.Constants;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;

    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategories();
        initProduct();
        initSlider();
    }

    private void initSlider() {
        getRecentOffers();
    }

    void getRecentOffers() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_OFFERS_URL, response -> {
            try {
//                    Log.e("err",response);
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equals("success")) {
                    JSONArray categoriesArray = jsonObject.getJSONArray("news_infos");
                    for (int i = 0; i < categoriesArray.length(); i++) {
                        JSONObject object2 = categoriesArray.getJSONObject(i);
                        binding.carousel.addData(
                                new CarouselItem(
                                        Constants.NEWS_IMAGE_URL + object2.getString("image"),
                                        object2.getString("title")
                                )
                        );
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });
        queue.add(request);
    }

    void initCategories() {
        categories = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(this, categories);

        getCategories();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);

    }

    void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, response -> {
            try {
//                    Log.e("err",response);
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equals("success")) {
                    JSONArray categoriesArray = jsonObject.getJSONArray("categories");
                    for (int i = 0; i < categoriesArray.length(); i++) {
                        JSONObject object = categoriesArray.getJSONObject(i);
                        Category category = new Category(
                                object.getString("name"),
                                Constants.CATEGORIES_IMAGE_URL + object.getString("icon"),
                                object.getString("color"),
                                object.getString("brief"),
                                object.getInt("id")
                        );
                        categories.add(category);
                    }
                    categoryAdapter.notifyDataSetChanged();
                } else {
//                        aa
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        queue.add(request);
    }

    void initProduct() {
        products = new ArrayList<>();

        productAdapter = new ProductAdapter(this, products);

        getRecentProducts();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.ProductList.setLayoutManager(layoutManager);
        binding.ProductList.setAdapter(productAdapter);
    }

    void getRecentProducts() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Constants.GET_PRODUCTS_URL + "?count=18";
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getString("status").equals("success")) {
                    JSONArray productsArray = object.getJSONArray("products");
                    for (int i = 0; i < productsArray.length(); i++) {
                        JSONObject object1 = productsArray.getJSONObject(i);
                        Product product = new Product(
                                object1.getString("name"),
                                Constants.PRODUCTS_IMAGE_URL + object1.getString("image"),
                                object1.getString("status"),
                                object1.getDouble("price"),
                                object1.getDouble("price_discount"),
                                object1.getInt("stock"),
                                object1.getInt("id")

                        );
                        products.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });

        queue.add(request);
    }


}