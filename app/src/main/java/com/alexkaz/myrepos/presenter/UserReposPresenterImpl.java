package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.view.UserReposView;

import io.reactivex.disposables.Disposable;

public class UserReposPresenterImpl implements UserReposPresenter {

    private UserReposView view;
    private GithubService githubService;
    private ConnInfoHelper helper;

    private int page = 1;
    private int perPage = 8;

    private boolean userInfoLoaded;
    private boolean repoListLoaded;

    private Disposable disposable;

    public UserReposPresenterImpl(GithubService githubService, ConnInfoHelper connInfoHelper) {
        this.githubService = githubService;
        this.helper = connInfoHelper;
    }

    @Override
    public void bindView(UserReposView view) {
        this.view = view;
    }

    @Override
    public void refresh() {
        if (helper.isOnline()){
            if (disposable != null ){
                if (!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
            if (!userInfoLoaded){
                loadUserInfo();
            }
            page = 1;
            view.clearUpList();
            view.hideRepos();
            loadNextPage();
        } else {
            view.showErrorMessage("No internet connection!");
        }
    }

    @Override
    public void loadNextPage() {
        if (helper.isOnline()){
            view.hideNoConnectionMessage();
            view.showLoading();
            disposable = githubService.getUserRepos(page, perPage).subscribe(repos -> {
                repoListLoaded = true;
                view.showRepos(repos);
                page++;
                if (userInfoLoaded){
                    view.hideLoading();
                } else {
                    view.hideRepos();
                }
            }, throwable -> {
                view.hideLoading();
                view.showErrorMessage(throwable.getMessage());
            });
        } else {
            if (!userInfoLoaded && !repoListLoaded){
                view.showNoConnectionMessage();
            } else {
                view.showErrorMessage("No internet connection!");
            }
        }
    }

    @Override
    public void loadUserInfo() {
        if (helper.isOnline()){
            view.hideNoConnectionMessage();
            view.showLoading();
            githubService.getUser().subscribe(user -> {
                view.showUserInfo(user);
                userInfoLoaded = true;
                if (repoListLoaded){
                    view.hideLoading();
                    view.showRepos(null);
                }
            }, throwable -> {
                view.hideLoading();
                view.showErrorMessage(throwable.getMessage());
            });
        } else {
            if (!userInfoLoaded && !repoListLoaded){
                view.showNoConnectionMessage();
            } else {
                view.showErrorMessage("No internet connection!");
            }
        }
    }
}
