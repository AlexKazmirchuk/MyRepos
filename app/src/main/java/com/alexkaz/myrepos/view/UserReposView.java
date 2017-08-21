package com.alexkaz.myrepos.view;

import com.alexkaz.myrepos.model.entities.RepoEntity;

import java.util.List;

public interface UserReposView {

    void showRepos(List<RepoEntity> userRepos);

    void showLoading();

    void showErrorMessage();

    void showNoConnectionMessage();

}
