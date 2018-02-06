package com.example.aandfcodekata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aandfcodekata.R;
import com.example.aandfcodekata.activities.WebViewActivity;
import com.example.aandfcodekata.model.Content;
import com.example.aandfcodekata.model.Product;
import com.example.aandfcodekata.util.Util;
import java.util.List;

/**
 * Created by Srinivas on 2/5/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private Context context;

    public ProductAdapter(List<Product> prods, Context context) {
        this.products = prods;
        this.context = context;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView topDescription;
        ImageView prodImage;
        TextView title;
        TextView bDescription1, bDescription2, promoMessage;
        Button shopMen, shopWomen;

        public ProductViewHolder(View v) {
            super(v);
            topDescription = (TextView) v.findViewById(R.id.topDescription);
            prodImage = (ImageView) v.findViewById(R.id.image);
            bDescription1 = (TextView) v.findViewById(R.id.bottomDescription1);
            bDescription2 = (TextView) v.findViewById(R.id.bottomDescription2);
            title = (TextView) v.findViewById(R.id.title);
            promoMessage = (TextView) v.findViewById(R.id.promoMessage);
            shopMen = (Button) v.findViewById(R.id.shopMen);
            shopWomen = (Button) v.findViewById(R.id.shopWomen);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product prodItem = products.get(position);

        if (holder.title != null) {
            if (!holder.title.equals("")) {
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(prodItem.getTitle());
            } else {
                holder.title.setVisibility(View.GONE);
            }
        } else {
            holder.title.setVisibility(View.GONE);
        }


        if (prodItem.getBackgroundImage() != null) {

            if (!prodItem.getBackgroundImage().equals("")) {
                holder.prodImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(prodItem.getBackgroundImage())
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.prodImage);

            } else {
                holder.prodImage.setVisibility(View.GONE);
            }
        } else {
            holder.prodImage.setVisibility(View.GONE);
        }
        if (prodItem.getTopDescription() != null) {
            if (!prodItem.getTopDescription().equals("")) {
                holder.topDescription.setVisibility(View.VISIBLE);
                holder.topDescription.setText(prodItem.getTopDescription());
            } else {
                holder.topDescription.setVisibility(View.GONE);
            }
        } else {
            holder.topDescription.setVisibility(View.GONE);
        }
        if (prodItem.getBottomDescription() != null) {
            if (!prodItem.getBottomDescription().equals("")) {
                holder.bDescription1.setVisibility(View.VISIBLE);
                holder.bDescription2.setVisibility(View.VISIBLE);

                final List<String> urls = Util.extractUrls(prodItem.getBottomDescription());
                if (urls.get(0).endsWith("\\")) {
                    urls.add(0, urls.get(0).substring(0, urls.get(0).length() - 1));
                }
                holder.bDescription2.setText(Util.addUnderLine("Exclusions apply. See Details."));

                System.out.println("url: " + urls.get(0));

                holder.bDescription2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("url", urls.get(0));
                        context.startActivity(intent);
                    }

                });


            } else {
                holder.bDescription1.setVisibility(View.GONE);
                holder.bDescription2.setVisibility(View.GONE);
            }
        } else

        {
            holder.bDescription1.setVisibility(View.GONE);
            holder.bDescription2.setVisibility(View.GONE);
        }

        if (prodItem.getPromoMessage() != null)

        {
            if (!prodItem.getPromoMessage().equals("")) {
                holder.promoMessage.setVisibility(View.VISIBLE);
                holder.promoMessage.setText("" + prodItem.getPromoMessage());
            } else {
                holder.promoMessage.setVisibility(View.GONE);
            }
        } else

        {
            holder.promoMessage.setVisibility(View.GONE);
        }

        final List<Content> contents = prodItem.getContent();
        if (contents != null)

        {
            if (contents.size() > 1) {
                holder.shopMen.setVisibility(View.VISIBLE);
                holder.shopWomen.setVisibility(View.VISIBLE);
                holder.shopMen.setText("SHOP MEN");
                holder.shopWomen.setText("SHOP WOMEN");

                holder.shopMen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("url", contents.get(0).getTarget());
                        context.startActivity(intent);
                    }
                });

                holder.shopWomen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("url", contents.get(1).getTarget());
                        context.startActivity(intent);
                    }
                });


            } else {
                holder.shopMen.setVisibility(View.VISIBLE);
                holder.shopMen.setText("SHOP NOW");
                holder.shopWomen.setVisibility(View.GONE);
                holder.shopMen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("url", contents.get(0).getTarget());
                        context.startActivity(intent);
                    }
                });

            }
        } else

        {
            holder.shopWomen.setVisibility(View.GONE);
            holder.shopMen.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
