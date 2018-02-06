package com.example.aandfcodekata.network;

import com.example.aandfcodekata.model.Product;
import com.example.aandfcodekata.model.ProductList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Srinivas on 2/5/2018.
 */

public interface AandFapiService {
//    @GET("search/repositories?sort=stars?order=desc")
//    Call<Repository> getReposList(@Query("q") String searchingParam);

    @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    Call<ProductList> getProductList();
}
