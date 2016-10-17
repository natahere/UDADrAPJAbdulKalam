package com.natarajan.udadrapjabdulkalam.View;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.natarajan.udadrapjabdulkalam.Model.Comment;
import com.natarajan.udadrapjabdulkalam.Model.Quote;
import com.natarajan.udadrapjabdulkalam.Model.QuoteTable;
import com.natarajan.udadrapjabdulkalam.Util.APIRetroFit;
import com.natarajan.udadrapjabdulkalam.Util.APJQuote;
import com.natarajan.udadrapjabdulkalam.R;

import java.util.ArrayList;
import java.util.List;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.natarajan.udadrapjabdulkalam.Model.Quote;
import com.natarajan.udadrapjabdulkalam.Model.QuoteTable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://www.androidfloor.com").build();
    APIRetroFit quoteapi;
    List<APJQuote> apjQuoteList;
    APJQuote apjQuoteOne;
    int selection;
    Intent intent;
    AlertDialog.Builder builder;
    GridView gridView;
    String quoteDisplay;
    int quoteNumber;
    ProgressDialog mProgressDialog;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    final int REQUEST_INVITE = 100;
    String TAG = "UDADrKalamApp";

    public static ArrayList<Integer> quoteNum = new ArrayList<>();
    public static ArrayList<String> quote = new ArrayList<>();

    Quote newQuote = new Quote();
    List<Comment> comments = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5D67054C9D60DACEA12BCE692A4F71B2")
                .build();
        //adView.loadAd(adRequestBuilder.build());

        adView.loadAd(adRequest);


        //set the preferences file
        preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.PREFS_FULL), Context.MODE_PRIVATE);

        //check if runs for first time,
        SharedPreferences runCheck = getSharedPreferences("hasRunBefore", 0); // load
        // the preferences
        Boolean hasRun = runCheck.getBoolean("hasRun", false); // see if it's
        // run before, default no
        if (!hasRun) {
            callQuote();

        } else {
            loadQuote();
            showQuote();
        }

        //callQuote();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void callQuote() {
        quoteapi = restadapter.create(APIRetroFit.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading Dr.Kalam Quotes...");
        mProgressDialog.show();
        quoteapi.getQuote(new Callback<APJQuote>() {
            @Override
            public void success(final APJQuote apjQuote, Response response) {
                Log.i("value of response", "is" + response);
                apjQuoteList = apjQuote.getQuotes();
                Log.i("Value of Quote is", "is" + apjQuoteList);
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                // only on success make it as hasRun

                SharedPreferences settings = getSharedPreferences("hasRunBefore", 0);
                SharedPreferences.Editor edit = settings.edit();
                edit.putBoolean("hasRun", true); // set to has run
                edit.commit(); // apply

                // Toast.makeText(MainActivity.this, "Value of quote is" + apjQuoteList, Toast.LENGTH_LONG).show();

                // Add the data to DB
                for (int i = 0; i < apjQuoteList.size(); i++) {
                    apjQuoteOne = apjQuoteList.get(i); // get the first one
                    newQuote.QuoteID = i;
                    Log.i("val in QuoteID", String.valueOf(i));
                    newQuote.QuoteText = apjQuoteOne.getQuote();
                    Log.i("val in QuoteText", apjQuoteOne.getQuote());
                    Uri uri = getApplicationContext().getContentResolver().insert(QuoteTable.CONTENT_URI, QuoteTable.getContentValues(newQuote, false));
                    if (uri != null) {
                        Log.d("DB insert", "added Quote");
                    }
                }


                loadQuote();
                showQuote();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "Please check your data connection", Toast.LENGTH_LONG).show();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });
    }

    public void showQuote() {

        gridView = (GridView) findViewById(R.id.gridView);
        com.natarajan.udadrapjabdulkalam.DO.Adapter adapt = new com.natarajan.udadrapjabdulkalam.DO.Adapter(getApplicationContext(), quoteNum, quote);
        adapt.notifyDataSetChanged();
        gridView.setAdapter(adapt);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                quoteNumber = quoteNum.get(position);
                quoteDisplay = quote.get(position);

                Intent intent = new Intent("android.intent.action.SHOWDRAPJCONTENT");
                intent.putExtra("selection", position);
                intent.putExtra("quoteNumber", quoteNumber);
                intent.putExtra("quoteDisplay", quoteDisplay);
                Log.i("Quote Number sent", " " + quoteNumber);
                Log.i("Quote  sent", " " + quoteDisplay);
                startActivity(intent);


            }
        });
    }

    public void loadQuote() {
        quoteNum.clear();
        quote.clear();

        getAllComments();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_profile:
                selection = 1;
                intent = new Intent("android.intent.action.WEBDRAPJ");
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;
            case R.id.nav_books:
                intent = new Intent("android.intent.action.WEBDRAPJ");
                selection = 2;
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;
            case R.id.fav_quotes:
                intent = new Intent("android.intent.action.SHOWFAVQUOTES");
                selection = 3;
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;
            case R.id.nav_awards:
                intent = new Intent("android.intent.action.WEBDRAPJ");
                selection = 4;
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;
            case R.id.nav_oath:
                selection = 5;
                intent = new Intent("android.intent.action.DISPLAYOATHLIST");
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;
            case R.id.nav_fbpages:
                selection = 6;
                intent = new Intent("android.intent.action.SHOWFBPAGELIST");
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;
            case R.id.nav_share:
                selection = 7;
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri
                        .parse("https://play.google.com/store/apps/developer?id=Natarajan+Raman"));
                startActivity(intent);
                break;

            case R.id.nav_send:
                selection = 8;
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri
                        .parse("market://details?id=com.natarajan.drapjabdulkalam"));
                startActivity(intent);
                break;
            case R.id.ref_quotes:
                callQuote();
                showQuote();
                selection = 9;
                break;

            case R.id.nav_submit:
                selection = 10;
                intent = new Intent("android.intent.action.SENDQUOTES");
                intent.putExtra("selection", selection);
                startActivity(intent);
                break;

            case R.id.nav_invite:
                onInviteClicked();
                break;

            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        quoteNum.add(cursor.getInt(0));
        Log.i("value of QuoteNum DB is", " " + cursor.getInt(0));
        quote.add(cursor.getString(1));
        Log.i("value of Quote DB is", " " + cursor.getString(1));

        comment.setComment(cursor.getString(1));
        return comment;
    }

    public List<Comment> getAllComments() {


        Cursor cursor = getContentResolver().query(QuoteTable.CONTENT_URI, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }


    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                // .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                //  .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode + "intent data=" + data);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }
}
