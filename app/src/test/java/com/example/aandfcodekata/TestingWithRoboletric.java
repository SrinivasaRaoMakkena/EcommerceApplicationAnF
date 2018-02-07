package com.example.aandfcodekata;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.aandfcodekata.activities.MainActivity;
import com.example.aandfcodekata.activities.WebViewActivity;
import com.example.aandfcodekata.adapter.ProductAdapter;
import com.example.aandfcodekata.model.Content;
import com.example.aandfcodekata.model.Product;
import org.apache.tools.ant.Main;
import org.assertj.android.recyclerview.v7.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Srinivas on 2/6/2018.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
@RunWith(RobolectricTestRunner.class)
public class TestingWithRoboletric {

    private Context context;
    private MainActivity mainActivity;
    private ActivityController<MainActivity> controller;
    private Button mShowMen;
    ProductAdapter productAdapter;
    RecyclerView rvParent;
    Product product1,product2;
    ProductAdapter.ProductViewHolder viewHolder;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;

        product1 = new Product();
        product1.setBackgroundImage("Image1");
        product1.setBottomDescription("bDesc1");

        Content content = new Content();
        content.setTarget("http://www.google.com");
        content.setTitle("Google");

        product1.setContent(Arrays.asList(content, new Content()));

        product1.setPromoMessage("12345");
        product1.setTopDescription("Top1");
        product1.setTitle(null);

        product2 = new Product();
        product2.setBackgroundImage("Image2");
        product2.setBottomDescription("bDesc2");

        Content content2 = new Content();
        content2.setTarget("http://www.gmail.com");
        content2.setTitle("Facebook");

        product2.setContent(Arrays.asList(content, new Content()));

        product2.setPromoMessage(null);
        product2.setTopDescription("Top1");
        product2.setTitle("Mens Sale");

        List<Product> products = Arrays.asList(
                product1,product2
        );


        productAdapter = new ProductAdapter(products, context);

        rvParent = new RecyclerView(context);
        rvParent.setLayoutManager(new LinearLayoutManager(context));

        viewHolder =
                productAdapter.onCreateViewHolder(rvParent, 0);
    }

    @Test
    public void testingRecyclerViewRowZero() {


        productAdapter.onBindViewHolder(viewHolder, 0);


        assertEquals(((TextView) viewHolder.itemView.findViewById(R.id.promoMessage)).getText(), product1.getPromoMessage());
        assertEquals(((TextView) viewHolder.itemView.findViewById(R.id.topDescription)).getText(), product1.getTopDescription());

        if (product1.getTitle() != null) {
            assertEquals((viewHolder.itemView.findViewById(R.id.showTitle)).getVisibility(), View.VISIBLE);
        }

        org.junit.Assert.assertEquals(viewHolder.itemView.findViewById(R.id.shopMen).getVisibility(), View.VISIBLE);



    }



    @Test
    public void testingRecyclerViewRowOne() {
        productAdapter.onBindViewHolder(viewHolder, 1);

        assertNotEquals(((TextView) viewHolder.itemView.findViewById(R.id.topDescription)).getText(), product2.getTitle());
        assertEquals(((TextView) viewHolder.itemView.findViewById(R.id.topDescription)).getText(), product2.getTopDescription());
    }

}
