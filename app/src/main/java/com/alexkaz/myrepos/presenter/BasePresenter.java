package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.BaseView;

public interface BasePresenter <T extends BaseView> {

    void bindView(T view);

}
