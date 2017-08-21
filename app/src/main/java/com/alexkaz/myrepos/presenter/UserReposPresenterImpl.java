package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.view.UserReposView;

public class UserReposPresenterImpl implements UserReposPresenter {

    private UserReposView view;
    private GithubService githubService;
    private ConnInfoHelper helper;

    public UserReposPresenterImpl(GithubService githubService, ConnInfoHelper connInfoHelper) {
        this.githubService = githubService;
        this.helper = connInfoHelper;
    }

    @Override
    public void bindView(UserReposView view) {
        this.view = view;
    }

    @Override
    public void loadRepos() {
        if (helper.isOnline()){
            view.showLoading();
            githubService.getUserRepos().subscribe(repos -> {
                view.hideLoading();
                view.showRepos(repos);
            }, throwable -> {
                view.hideLoading();
                view.showErrorMessage(throwable.getMessage());
            });
        } else {
            view.hideRepos();
            view.showNoConnectionMessage();
        }
    }

    @Override
    public void loadNextPage(int page, int perPage) {
        // todo impl later
    }
}
