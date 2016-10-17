package com.natarajan.udadrapjabdulkalam.View;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.natarajan.udadrapjabdulkalam.DO.FBImageAdapter;
import com.natarajan.udadrapjabdulkalam.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Natarajan on 2/29/2016.
 */
public class ShowFBPageList extends AppCompatActivity {

    @Bind(R.id.OathgridView)
    GridView gridView;
    Intent intent;

    //added

    String urlFb;

    private String pageidbb = "122173544592374"; // - Billion Beats
    // to get FB Page id - https://graph.facebook.com/<AbdulkalamOfficialPage>
    private String pageid = "789883617717001";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oath_main);
        ButterKnife.bind(this);

        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5D67054C9D60DACEA12BCE692A4F71B2")
                .build();
        //adView.loadAd(adRequestBuilder.build());

        adView.loadAd(adRequest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView.setAdapter(new FBImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                intent = new Intent("android.intent.action.SHOWDRAPJFBPAGE");
//                intent.putExtra("selection", position);
//                startActivity(intent);

                switch (position) {

                    case 0:

                        urlFb = "fb://page/" + pageidbb;
                        onClickFBBBbtn();
                        break;

                    case 1:
                        urlFb = "fb://page/" + pageid;
                        onClickFBPage();
                        break;

                    default:
                        break;
                }

            }
        });
    }




    public void onClickFBBBbtn() {

        final String urlFb = "fb://page/" + pageidbb;
        // final String urlFb =
        // "https://www.facebook.com/AbdulkalamOfficialPage";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFb));
        Toast.makeText(this, "Loading FaceBook Page", Toast.LENGTH_LONG).show();
        // If a Facebook app is installed, use it. Otherwise, launch
        // a browser
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            // final String urlBrowser =
            // "https://www.facebook.com/kalamBillionbeats";
            final String urlBrowser = "https://www.facebook.com/kalamBillionbeats";
            intent.setData(Uri.parse(urlBrowser));
        }

        startActivity(intent);

    }

    public void onClickFBPage() {
        // Intent intent = new Intent("android.intent.action.SHOWFBPAGE");
        // intent.putExtra("selection", 4);
        // startActivity(intent);
        final String urlFb = "fb://page/" + pageid;
        // final String urlFb =
        // "https://www.facebook.com/AbdulkalamOfficialPage";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFb));
        Toast.makeText(this, "Loading FaceBook Page", Toast.LENGTH_LONG).show();
        // If a Facebook app is installed, use it. Otherwise, launch
        // a browser
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            // final String urlBrowser =
            // "https://www.facebook.com/kalamBillionbeats";
            final String urlBrowser = "https://www.facebook.com/AbdulkalamOfficialPage";
            intent.setData(Uri.parse(urlBrowser));
        }

        startActivity(intent);

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

