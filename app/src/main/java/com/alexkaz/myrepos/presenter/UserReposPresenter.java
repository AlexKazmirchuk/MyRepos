package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.UserReposView;

public interface UserReposPresenter extends BasePresenter <UserReposView> {

    void refresh();

    void loadNextPage();

    void loadUserInfo();

    void reset();

}
