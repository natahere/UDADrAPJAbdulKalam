package com.natarajan.udadrapjabdulkalam.DO;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.natarajan.udadrapjabdulkalam.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the QuoteAdapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.b1youthoatheng,R.drawable.b2youthoathtam,
            R.drawable.b3youthoathtel,R.drawable.b4youthoathkabtn,R.drawable.b5teacherbtn,
            R.drawable.b6docbtn,R.drawable.b7compeoplebtn,R.drawable.b8iitbtn,R.drawable.b9iasbtn,
            R.drawable.b10farmbtn,R.drawable.b11encitizenbtn,R.drawable.b12jawabtn,
            R.drawable.b13ambedbtn,R.drawable.b14villacitzbtn,R.drawable.b15nusrbtn,
            R.drawable.b16pharambtn
    };
}