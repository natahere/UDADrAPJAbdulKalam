package com.natarajan.udadrapjabdulkalam.DO;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.natarajan.udadrapjabdulkalam.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Natarajan on 12/23/2015.
 */



public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<String>  quote;
    private ArrayList<Integer> quoteNum;
    int imageInt;
    int next;
    public int[] images = new int[] {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5, R.drawable.bg6, R.drawable.bg7,
            R.drawable.bg8};
    List<Integer> generated = new ArrayList<Integer>();
    Random random = new Random(System.currentTimeMillis());
//    ViewHolder holder = new ViewHolder();


    public Adapter(Context context, ArrayList<Integer> quoteNum, ArrayList<String> quote) {
        this.context = context;
        this.quoteNum = quoteNum;
        this.quote = quote;
    }

    @Override
    public int getCount() {
        return quoteNum.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //declare an inner class for ViewHolder

    static class ViewHolder {
        TextView quoteView;
        ImageView quote_image;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Changed to ViewHolder and Picasso error load as per feedback received
        ViewHolder holder = new ViewHolder();


        //Inflate view if it is null
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_file, null);
            holder.quoteView = (TextView) convertView.findViewById(R.id.quoteText);
            holder.quote_image = (ImageView) convertView.findViewById(R.id.quote_image);

            convertView.setTag(holder);
//            imageInt = randomImage();
//            holder.quote_image.setImageResource(images[imageInt]);
            holder.quoteView.setVisibility(View.VISIBLE);
            // holder.quoteView.setText(quoteNum.get(position) + quote.get(position));
            holder.quoteView.setText(quote.get(position));

        } else {
            holder.quoteView = (TextView) convertView.findViewById(R.id.quoteText);
            holder.quote_image = (ImageView) convertView.findViewById(R.id.quote_image);
            convertView.setTag(holder);
//            randomImage();
//            imageInt = randomImage();
//            holder.quote_image.setImageResource(images[imageInt]);
            holder.quoteView.setVisibility(View.VISIBLE);
//            holder.quoteView.setText(quoteNum.get(position) + quote.get(position));
            holder.quoteView.setText(quote.get(position));
        }

        if (position % 2 == 0){
            holder.quote_image.setBackgroundResource(R.drawable.bg1);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.colorAccent));
        }
        else if(position % 3 == 0){
            holder.quote_image.setBackgroundResource(R.drawable.bg2);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.blue));
        }
        else if (position % 4 == 0)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg3);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
        }else if (position % 5 == 0)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg4);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.darkgreen));
        }else if (position % 6 == 0)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg5);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.darkpurple));
        }
        else if (position % 7 == 0)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg6);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
        }
        else if (position % 8 == 0)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg7);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.darkorange));
        }

        else if (position % 9 == 0)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg8);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.darkred));
        }
        else if (position == 0 || position == 1)
        {
            holder.quote_image.setBackgroundResource(R.drawable.bg8);
            holder.quoteView.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
        }

        return convertView;
    }

    public int randomImage() {
        for (int i = 0; i < images.length; i++) {
            next = random.nextInt(7);
            if (!generated.contains(next)) {
                generated.add(next);
                //  holder.quote_image.setImageResource( images[next] );
            } else {
                i--;
            }
        }
        return next;
    }

}

