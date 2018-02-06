package com.example.aandfcodekata.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aandfcodekata.R;
import com.example.aandfcodekata.adapter.ProductAdapter;
import com.example.aandfcodekata.model.Product;
import com.example.aandfcodekata.model.ProductList;
import com.example.aandfcodekata.network.AandFApiClient;
import com.example.aandfcodekata.network.AandFapiService;
import com.example.aandfcodekata.network.AnFAsynctask;
import com.example.aandfcodekata.network.OnTaskCompleted;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnTaskCompleted {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        AnFAsynctask anFAsynctask = new AnFAsynctask(MainActivity.this, this);
        anFAsynctask.execute();


    }

    @Override
    public void onTaskCompleted(List<Product> productList) {
        for(int i=0;i<productList.size();i++){
            System.out.println("title "+ productList.get(i).getTitle()+" promo: "+productList.get(i).getPromoMessage()
                    +" bottom "+productList.get(i).getTopDescription());
        }

        ProductAdapter productAdapter = new ProductAdapter(productList, MainActivity.this);
        mRecyclerView.setAdapter(productAdapter);


    }
}
