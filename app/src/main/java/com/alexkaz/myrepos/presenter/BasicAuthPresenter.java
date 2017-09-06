package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.BasicAuthView;

public interface BasicAuthPresenter {

    void bindView(BasicAuthView view);

    void login(String username, String password);
}
