package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.view.UserReposView;

public class UserReposPresenterImpl implements UserReposPresenter {

    private UserReposView view;
    private GithubService githubService;
    private ConnInfoHelper connInfoHelper;

    public UserReposPresenterImpl(GithubService githubService, ConnInfoHelper connInfoHelper) {
        this.githubService = githubService;
        this.connInfoHelper = connInfoHelper;
    }

    @Override
    public void bindView(UserReposView view) {
        this.view = view;
    }

    @Override
    public void loadRepos() {
        view.showLoading();
        githubService.getUserRepos().subscribe(repos -> {
            view.hideLoading();
            view.showRepos(repos);
        }, throwable -> {
            view.hideLoading();
            view.showErrorMessage(throwable.getMessage());
        });
    }

    @Override
    public void loadNextPage(int page, int perPage) {
        // todo impl later
    }
}
