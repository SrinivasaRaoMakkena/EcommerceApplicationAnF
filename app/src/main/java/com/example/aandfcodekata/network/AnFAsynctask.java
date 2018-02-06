package com.example.aandfcodekata.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.aandfcodekata.adapter.ProductAdapter;
import com.example.aandfcodekata.model.Product;
import com.example.aandfcodekata.model.ProductList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srinivas on 2/5/2018.
 */

public class AnFAsynctask extends AsyncTask<Void, Void, String> {
    private ProgressDialog mProgressDialog;
    private Context mContext;
   public OnTaskCompleted onTaskCompleted;

    public AnFAsynctask(Context context,OnTaskCompleted taskCompleted) {
        this.mContext = context;
       this.onTaskCompleted = taskCompleted;

    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(mContext, "", "Fetching Data...");
    }

    @Override
    protected String doInBackground(Void... voids) {
        System.out.println("Good");
        URL url;
        HttpURLConnection httpURLConnection;
        String s = "";
        try {

            url = new URL("https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            System.out.println(httpURLConnection.getResponseCode());
            try {
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

                s = readStream(in);


                //System.out.println(s);
            } finally {
                httpURLConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;

    }

    @Override
    protected void onPostExecute(String s) {
        mProgressDialog.dismiss();

        JSONArray array;
        List<Product> productList = new ArrayList<>();
        try {
            array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObj = array.getJSONObject(i);
                Gson g = new Gson();
                Product p = g.fromJson(jsonObj.toString(),Product.class);
                productList.add(p);

                System.out.println(jsonObj.get("title"));
            }
            onTaskCompleted.onTaskCompleted(productList);
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        array.


//        Gson gson = new Gson();
//        ProductList prod =  gson.fromJson(s, ProductList.class);
//        System.out.println(prod.getList()[0].getTitle());


    }


    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
