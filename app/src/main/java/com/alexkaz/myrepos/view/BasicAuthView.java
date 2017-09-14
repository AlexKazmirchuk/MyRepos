package com.alexkaz.myrepos.view;

public interface BasicAuthView extends BaseView {

    void authenticated();

    void showBadCredentialsWarning(String message);

}
