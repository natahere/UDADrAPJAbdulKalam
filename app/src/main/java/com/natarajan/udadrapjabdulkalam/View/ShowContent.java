package com.natarajan.udadrapjabdulkalam.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.natarajan.udadrapjabdulkalam.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Natarajan on 2/23/2016.
 */
public class ShowContent extends AppCompatActivity {

    int selection;
    @Bind(R.id.content) TextView contentView;
    @Bind(R.id.title) TextView titleText;
    @Bind(R.id.fab)FloatingActionButton fab;
    String title,content,line,quoteNumber;

    @Override
    protected void onCreate(Bundle onSavedInstanceState)
    {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.display_main);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detailed Content");

        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5D67054C9D60DACEA12BCE692A4F71B2")
                .build();
        //adView.loadAd(adRequestBuilder.build());

        adView.loadAd(adRequest);

        Intent intent = getIntent();
        selection = intent.getIntExtra("selection",3);

        quoteNumber = String.valueOf(intent.getIntExtra("quoteNumber",1));
        title = "Dr.Kalam's Quote";// intent.getStringExtra("quoteNumber");
        content = intent.getStringExtra("quoteDisplay");

        if(selection == 10 || selection == 12)
        {
            fab.setVisibility(View.INVISIBLE);
        }else
        {
            fab.setVisibility(View.VISIBLE);
        }

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.PREFS_NAME), Context.MODE_PRIVATE);

        // This is to check during load time if the movie is already is fav
        // if so, set the background accordingly
        if (!preferences.getString(quoteNumber, "").isEmpty()) {
            fab.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_favorite_sel));
        } else {
            fab.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_favorite));
        }

        titleText.setText(title);
        contentView.setText(content);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.PREFS_NAME), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                //if id does not exist, add to preferences (As a favorite), if it does remove it (undo favorite)
                if (preferences.getString(quoteNumber, "").isEmpty()) {
                    editor.putString(quoteNumber, content);
                    editor.commit();
                    fab.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_favorite_sel));
                    Toast.makeText(ShowContent.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else if (!preferences.getString(quoteNumber, "").isEmpty()) {
                    editor.remove(quoteNumber);
                    editor.commit();
                    fab.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_favorite));
                    Toast.makeText(ShowContent.this, "Removed From Favorites", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.

        if(selection == 10)
        {
            NavUtils.navigateUpTo(this, new Intent(this, ShowFavQuotes.class));

        }else {
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));

        }
            return true;
        }
        if(id == R.id.action_share){
            onClickOtherShare();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClickOtherShare() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        line = contentView.getText().toString();
        sendIntent.putExtra(Intent.EXTRA_TEXT, line + " - Dr.APJ  \t  download app from http://bit.ly/1LY0ah2");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share Quote via"));
    }

}
