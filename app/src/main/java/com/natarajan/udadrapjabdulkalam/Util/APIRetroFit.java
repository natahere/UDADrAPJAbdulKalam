package com.natarajan.udadrapjabdulkalam.Util;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Natarajan on 2/19/2016.
 */
public interface APIRetroFit {


    @GET("/drkalamjson")
    public void getQuote(Callback<APJQuote> response);
}
