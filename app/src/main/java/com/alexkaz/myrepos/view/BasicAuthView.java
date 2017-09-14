package com.alexkaz.myrepos.view;

public interface BasicAuthView {

    void authenticated();

    void showLoading();

    void showWarningMessage(String message);

    void showBadCredentialsWarning(String message);

    void hideLoading();
}
