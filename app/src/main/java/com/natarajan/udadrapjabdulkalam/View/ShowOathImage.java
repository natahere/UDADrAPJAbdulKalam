package com.natarajan.udadrapjabdulkalam.View;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.natarajan.udadrapjabdulkalam.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowOathImage extends AppCompatActivity {

    ImageView oathImage;
    Bundle extras;
    int value;
    Bitmap oath;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oath_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5D67054C9D60DACEA12BCE692A4F71B2")
                .build();
        //adView.loadAd(adRequestBuilder.build());

        adView.loadAd(adRequest);

        oathImage = (ImageView) findViewById(R.id.OathImage);

        extras = getIntent().getExtras();
        value = extras.getInt("selection");
        value = value + 1;

        switch (value) {

            case 1:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o1oathenglish);

                break;
            case 2:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o2oathtamil);

                break;
            case 3:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o3oathtelugu);

                break;
            case 4:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o4oathkan);
                break;
            case 5:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o5oathteachers);
                break;
            case 6:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o6oathdoctors);
                break;
            case 7:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o7oathcommon);
                break;
            case 8:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o8oathiits);
                break;
            case 9:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o9oathcivil);
                break;
            case 10:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o10oathfarmer);
                break;
            case 11:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o11oathcitizenship);
                break;
            case 12:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o12oathjawans);
                break;
            case 13:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o13oathambedkar);
                break;
            case 14:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o14oathvillage);
                break;
            case 15:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o15oathnursing);
                break;
            case 16:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o16oathpharma);
                break;
            case 17:
                oath = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.o17oathleaders);
                break;

            default:
                break;
        }

        oathImage.setImageBitmap(oath);

        Toast.makeText(ShowOathImage.this, "ZOOM Or use Landscape mode",
                Toast.LENGTH_SHORT).show();
        mAttacher = new PhotoViewAttacher(oathImage);
    }// onCreate ends


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpTo(this, new Intent(this, DisplayOathList.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
