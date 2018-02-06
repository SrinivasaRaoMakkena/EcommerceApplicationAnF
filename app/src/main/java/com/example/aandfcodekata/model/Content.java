
package com.example.aandfcodekata.model;


import com.google.gson.annotations.SerializedName;


public class Content {

    @SerializedName("target")
    private String mTarget;
    @SerializedName("title")
    private String mTitle;

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        mTarget = target;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
