package com.natarajan.udadrapjabdulkalam.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.natarajan.udadrapjabdulkalam.Model.Comment;
import com.natarajan.udadrapjabdulkalam.R;
import com.natarajan.udadrapjabdulkalam.Model.QuoteProvider;
import com.natarajan.udadrapjabdulkalam.Model.QuoteTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Natarajan on 4/16/2016.
 */

// We need a service class to ensure that it is running in background to Observe the changes if any

// and provide update

public class QuoteWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewFactory(this.getApplicationContext(), intent);
    }
}


class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private Cursor mCursor;
    private int mAppWidgetId;


    List<Comment> comments = new ArrayList<Comment>();
    public static ArrayList<Integer> widquoteNum = new ArrayList<>();
    public static ArrayList<String> widquote = new ArrayList<>();
    // RemoteViews rv;


    public ListViewFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {
        // logic in onDataSetChanged() follows this call
    }

    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    public int getCount() {
        return mCursor.getCount();
    }


    // will be called automatically

    public RemoteViews getViewAt(int position) {


        // Get the data for this position from the content provider
        String quotetext = "";


        if (mCursor.moveToPosition(position)) {

            quotetext = widquote.get(position);


        }

        // Fill data in UI
        final int itemId = R.layout.widget_collection_item;
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), itemId);
        rv.setTextViewText(R.id.quote_text, quotetext);


        // Set the click intent
        final Intent fillInIntent = new Intent();
        final Bundle extras = new Bundle();

        extras.putString("quoteDisplay", quotetext);

        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
        return rv;
    }

    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }

    public boolean hasStableIds() {
        return true;
    }

    public void onDataSetChanged() {
        // on Change of data, we need to refresh the data
        // cursor
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = mContext.getContentResolver().query(QuoteTable.CONTENT_URI, null, null, null, null);
        getAllComments();

    }


    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        widquoteNum.add(cursor.getInt(0));

        Log.i("value of QuoteNum DB is", " " + cursor.getInt(0));
        widquote.add(cursor.getString(1));
        Log.i("value of Quote DB is", " " + cursor.getString(1));
        // comment.setId(cursor.getString(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }

    public List<Comment> getAllComments() {

        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            Comment comment = cursorToComment(mCursor);
            comments.add(comment);
            mCursor.moveToNext();
        }
        // make sure to close the cursor
        // mCursor.close();
        return comments;
    }
}