package com.natarajan.udadrapjabdulkalam.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.natarajan.udadrapjabdulkalam.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Natarajan on 2/23/2016.
 */
public class WebDisplayContent extends AppCompatActivity {

    @Bind(R.id.webView) WebView webView;
    @Bind(R.id.webtitle) TextView webTitle;
    int selection;
    String title;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle OnSavedInstanceState)
    {
        super.onCreate(OnSavedInstanceState);
        setContentView(R.layout.web_main);
        ButterKnife.bind(this);

        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5D67054C9D60DACEA12BCE692A4F71B2")
                .build();
        //adView.loadAd(adRequestBuilder.build());

        adView.loadAd(adRequest);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detailed Content");

        Intent intent = getIntent();
        selection = intent.getIntExtra("selection",3);
        //webView.setWebViewClient(new WebViewClient() );

        switch (selection)
        {
            case 1:
                webView.loadUrl("file:///android_asset/prof.html");
                title = "Profile";
                break;

            case 2:
                webView.loadUrl("file:///android_asset/respbooks.html");
                title = "Books";
                break;

            case 4:
                webView.loadUrl("file:///android_asset/respawards.html");
                title = "Awards";
                break;
        }

        webTitle.setText(title);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
