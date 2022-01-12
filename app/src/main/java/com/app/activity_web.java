package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activity_web extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.webview);

        webView.loadUrl("https://zh.wikipedia.org/wiki/%E8%BA%AB%E9%AB%98%E9%AB%94%E9%87%8D%E6%8C%87%E6%95%B8");

        //設定網頁縮放
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings.getDefaultFontSize();


        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}