package com.alexkaz.myrepos.view;

import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.model.entities.UserEntity;

import java.util.List;

public interface UserReposView {

    void showUserInfo(UserEntity user);

    void showRepos(List<RepoEntity> userRepos);

    void showLoading();

    void showErrorMessage(String message);

    void showNoConnectionMessage();

    void hideLoading();

    void hideNoConnectionMessage();

    void hideRepos();

}
