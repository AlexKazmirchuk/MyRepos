package com.alexkaz.myrepos.view;

public interface BasicAuthView {

    void authenticated();

    void showLoading();

    void showErrorMessage(String message);

    void showBadCredentialsError();

    void hideLoading();
}