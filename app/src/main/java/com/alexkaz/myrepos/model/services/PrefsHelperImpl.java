package com.alexkaz.myrepos.model.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PrefsHelperImpl implements PrefsHelper {

    private SharedPreferences prefs;

    public PrefsHelperImpl(Context context) {
        prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
    }

    @Override
    public void saveToken(String token) {
        prefs.edit().putString("token", token).apply();
    }

    @Override
    public String getToken() {
        return prefs.getString("token","");
    }
}
