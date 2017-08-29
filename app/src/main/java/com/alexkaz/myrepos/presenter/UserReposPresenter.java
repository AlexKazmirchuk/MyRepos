package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.UserReposView;

public interface UserReposPresenter {

    void bindView(UserReposView view);

    void loadNextPage();

    void loadUserInfo();

}
