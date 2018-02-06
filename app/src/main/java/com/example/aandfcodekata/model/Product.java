
package com.example.aandfcodekata.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("backgroundImage")
    private String mBackgroundImage;
    @SerializedName("bottomDescription")
    private String mBottomDescription;
    @SerializedName("content")
    private List<Content> mContent;
    @SerializedName("promoMessage")
    private String mPromoMessage;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("topDescription")
    private String mTopDescription;

    public String getBackgroundImage() {
        return mBackgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        mBackgroundImage = backgroundImage;
    }

    public String getBottomDescription() {
        return mBottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        mBottomDescription = bottomDescription;
    }

    public List<Content> getContent() {
        return mContent;
    }

    public void setContent(List<Content> content) {
        mContent = content;
    }

    public String getPromoMessage() {
        return mPromoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        mPromoMessage = promoMessage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTopDescription() {
        return mTopDescription;
    }

    public void setTopDescription(String topDescription) {
        mTopDescription = topDescription;
    }

}
