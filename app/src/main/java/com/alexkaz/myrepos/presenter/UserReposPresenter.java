package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.UserReposView;

public interface UserReposPresenter {

    void bindView(UserReposView view);

    void refresh();

    void loadNextPage();

    void loadUserInfo();

}
