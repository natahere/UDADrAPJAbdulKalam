package com.natarajan.udadrapjabdulkalam.Model;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

/**
 * Created by Natarajan on 10/8/2016.
 */

@SimpleSQLTable(table = "Quote", provider = App.QUOTE_PROVIDER)
public class Quote {

    @SimpleSQLColumn(value = "col_int", primary = true)
    public int QuoteID;

    @SimpleSQLColumn("col_str")
    public String QuoteText;

    public Quote()
    {}

    public Quote(int QuoteID,String QuoteText)
    {
        this.QuoteID = QuoteID;
        this.QuoteText = QuoteText;
    }

}
