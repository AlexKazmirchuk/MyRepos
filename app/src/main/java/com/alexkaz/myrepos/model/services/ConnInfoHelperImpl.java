package com.alexkaz.myrepos.model.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnInfoHelperImpl implements ConnInfoHelper {

    private Context context;

    public ConnInfoHelperImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
