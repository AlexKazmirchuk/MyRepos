package com.alexkaz.myrepos.utils;

import android.net.Uri;

public class GitHubConfig {

    public static final String CLIENT_ID = "06c4a6a738efcd9f2305";

    public static final String CLIENT_SECRET = "2210111e2f1ed190a436a724ae879a8581fac88e";

    public static final String OAUTH_URL = "https://github.com/login/oauth/authorize";

    public static final String OAUTH_TOKEN_URL = "https://github.com/login/oauth/access_token";

    public static Uri getOauthUrl(){
        return Uri.parse(OAUTH_URL)
                .buildUpon()
                .appendQueryParameter("client_id", GitHubConfig.CLIENT_ID)
                .build();
    }

}
