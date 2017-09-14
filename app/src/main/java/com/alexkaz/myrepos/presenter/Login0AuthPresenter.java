package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.Login0AuthView;

public interface Login0AuthPresenter extends BasePresenter <Login0AuthView> {

    void handleCode(String code);

}
