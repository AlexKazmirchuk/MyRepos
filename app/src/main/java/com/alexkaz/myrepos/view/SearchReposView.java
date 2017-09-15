package com.alexkaz.myrepos.view;

import com.alexkaz.myrepos.model.entities.RepoEntity;

import java.util.List;

public interface SearchReposView extends BaseView {

    String getQueryText();

    void showRepos(List<RepoEntity> userRepos);

    void clearUpList();

    void hideRepos();

}
