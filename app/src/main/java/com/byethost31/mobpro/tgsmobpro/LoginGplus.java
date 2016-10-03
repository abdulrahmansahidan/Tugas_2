package com.byethost31.mobpro.tgsmobpro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by MANZ07 on 03/10/2016.
 */

public class LoginGplus extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_gplus);

        WebView browser = (WebView) findViewById(R.id.wvGplus);

        WebSettings settings = browser.getSettings();
        settings.setJavaScriptEnabled(true);

        browser.setWebChromeClient(new WebChromeClient());
        browser.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // Load the webpage
        browser.loadUrl("https://accounts.google.com/");
    }
}
