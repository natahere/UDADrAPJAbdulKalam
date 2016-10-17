package com.natarajan.udadrapjabdulkalam.Model;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

/**
 * Created by Natarajan on 10/8/2016.
 */
@SimpleSQLConfig(
        name = "QuoteProvider",
        authority = "com.natarajan.udadrapjabdulkalam.authority",
        database = "quotedata.db",
        version = 1)
public class QuoteProviderConfig implements ProviderConfig {
    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}