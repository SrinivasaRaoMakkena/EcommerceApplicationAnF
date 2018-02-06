package com.example.aandfcodekata.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.aandfcodekata.R;

public class WebViewActivity extends AppCompatActivity {

    WebView browser;
    ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        browser = (WebView) findViewById(R.id.website);
        progDailog = ProgressDialog.show(WebViewActivity.this, "Loading", "Please wait...", true);
        progDailog.setCancelable(true);

        Intent i = getIntent();
        System.out.println("url " + i.getStringExtra("url"));
        browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        configureWebView(browser);
        browser.loadUrl(i.getStringExtra("url"));
        setTitle("Abercrombie & Fitch Web");
    }


    private void configureWebView(WebView webview) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webview.setWebViewClient(new InnerWebViewClient());
        webview.setInitialScale(100);

    }

    private class InnerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progDailog.show();
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, final String url) {
            progDailog.dismiss();
        }

    }
}
