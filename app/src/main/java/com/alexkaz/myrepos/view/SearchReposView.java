package com.alexkaz.myrepos.view;

import com.alexkaz.myrepos.model.entities.RepoEntity;

import java.util.List;

public interface SearchReposView {

    String getQueryText();

    void showRepos(List<RepoEntity> userRepos);

    void clearUpList();

    void showLoading();

    void showErrorMessage(String message);

    void hideLoading();

    void hideRepos();

}
