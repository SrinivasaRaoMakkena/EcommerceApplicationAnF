package com.example.aandfcodekata.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.aandfcodekata.R;
import com.example.aandfcodekata.adapter.ProductAdapter;
import com.example.aandfcodekata.model.Product;
import com.example.aandfcodekata.network.AnFAsynctask;
import com.example.aandfcodekata.network.OnTaskCompleted;


import java.util.List;



public class MainActivity extends AppCompatActivity implements OnTaskCompleted {


    private RecyclerView mRecyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        AnFAsynctask anFAsynctask = new AnFAsynctask(MainActivity.this, this);
        if (checkingInternetConnection()) {
            anFAsynctask.execute();
        } else {
            Toast.makeText(MainActivity.this, "Internet not available", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onTaskCompleted(List<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {
            System.out.println("title " + productList.get(i).getTitle() + " promo: " + productList.get(i).getPromoMessage()
                    + " bottom " + productList.get(i).getTopDescription());
        }
        ProductAdapter productAdapter = new ProductAdapter(productList, MainActivity.this);
        mRecyclerView.setAdapter(productAdapter);

    }

    public boolean checkingInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
