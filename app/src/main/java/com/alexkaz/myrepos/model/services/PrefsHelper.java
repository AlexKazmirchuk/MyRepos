package com.alexkaz.myrepos.model.services;

public interface PrefsHelper {

    void saveToken(String token);

    String getToken();

    void setAuthenticated(boolean authenticated);

    boolean isAuthenticated();
}
