package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.BasicAuthView;

public interface BasicAuthPresenter extends BasePresenter <BasicAuthView> {

    void login(String username, String password);

}
