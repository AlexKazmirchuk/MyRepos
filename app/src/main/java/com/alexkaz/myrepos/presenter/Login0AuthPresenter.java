package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.Login0AuthView;

public interface Login0AuthPresenter {

    void bindView(Login0AuthView view);

    void handleCode(String code);

}
