package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.view.UserReposView;

public class UserReposPresenterImpl implements UserReposPresenter {

    private UserReposView view;

    private GithubService githubService;

    public UserReposPresenterImpl(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    public void bindView(UserReposView view) {
        this.view = view;
    }

    @Override
    public void loadRepos() {
        //todo impl later
    }

    @Override
    public void loadNextPage(int page, int perPage) {
        // todo impl later
    }
}
