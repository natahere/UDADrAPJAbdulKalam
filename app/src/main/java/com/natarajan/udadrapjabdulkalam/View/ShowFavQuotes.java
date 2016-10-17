package com.natarajan.udadrapjabdulkalam.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.natarajan.udadrapjabdulkalam.R;
import com.natarajan.udadrapjabdulkalam.Util.APJQuote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Natarajan on 3/4/2016.
 */
public class ShowFavQuotes extends AppCompatActivity {

    List<APJQuote> apjQuoteList;
    public static ArrayList<String> quoteNum = new ArrayList<>();
    public static ArrayList<String> quote = new ArrayList<>();
    String quoteNumber,quoteDisplay;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5D67054C9D60DACEA12BCE692A4F71B2")
                .build();
        //adView.loadAd(adRequestBuilder.build());

        adView.loadAd(adRequest);

        gridView = (GridView) findViewById(R.id.gridView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.PREFS_NAME), Context.MODE_PRIVATE);

        // read all the quotes and save into the list

//        for(int i=0;i<preferences.getAll().size();i++)
//        {
//            quote.add(preferences.getString(String.valueOf(i),"quote"));
//
//        }

        quoteNum.clear();
        quote.clear();
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            quoteNum.add(entry.getKey());
            quote.add(entry.getValue().toString());
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }

        // read from the list and get the individual

        Log.i("quote value is", "is" + quote.toString());


        com.natarajan.udadrapjabdulkalam.DO.FavAdapter adapt = new com.natarajan.udadrapjabdulkalam.DO.FavAdapter(getApplicationContext(), quoteNum, quote);
        adapt.notifyDataSetChanged();
        gridView.setAdapter(adapt);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int gridItemSelected = position;
                quoteNumber = quoteNum.get(position);
                quoteDisplay = quote.get(position);

                Intent intent = new Intent("android.intent.action.SHOWDRAPJCONTENT");
                intent.putExtra("selection", 10);
                intent.putExtra("quoteNumber", String.valueOf(quoteNumber));
                intent.putExtra("quoteDisplay", quoteDisplay);
                startActivity(intent);
            }
        });

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
