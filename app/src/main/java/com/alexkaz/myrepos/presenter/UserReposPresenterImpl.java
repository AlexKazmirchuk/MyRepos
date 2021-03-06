package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GitHubService;
import com.alexkaz.myrepos.view.UserReposView;

import io.reactivex.disposables.Disposable;

public class UserReposPresenterImpl implements UserReposPresenter {

    private UserReposView view;
    private GitHubService gitHubService;
    private ConnInfoHelper helper;

    private int page = 1;

    private boolean userInfoLoaded;
    private boolean repoListLoaded;

    private Disposable disposable;

    public UserReposPresenterImpl(GitHubService gitHubService, ConnInfoHelper connInfoHelper) {
        this.gitHubService = gitHubService;
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
            view.showWarningMessage("No internet connection!");
        }
    }

    @Override
    public void loadNextPage() {
        if (helper.isOnline()){
            view.hideNoConnectionMessage();
            view.showLoading();
            int perPage = 8;
            disposable = gitHubService.getUserRepos(page, perPage).subscribe(repos -> {
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
                view.showWarningMessage(throwable.getMessage());
            });
        } else {
            if (!userInfoLoaded && !repoListLoaded){
                view.showNoConnectionMessage();
            } else {
                view.showWarningMessage("No internet connection!");
            }
        }
    }

    @Override
    public void loadUserInfo() {
        if (helper.isOnline()){
            view.hideNoConnectionMessage();
            view.showLoading();
            gitHubService.getUser().subscribe(user -> {
                view.showUserInfo(user);
                userInfoLoaded = true;
                if (repoListLoaded){
                    view.hideLoading();
                    view.showRepos(null);
                }
            }, throwable -> {
                view.hideLoading();
                view.showWarningMessage(throwable.getMessage());
            });
        } else {
            if (!userInfoLoaded && !repoListLoaded){
                view.showNoConnectionMessage();
            } else {
                view.showWarningMessage("No internet connection!");
            }
        }
    }

    @Override
    public void reset() {
        page = 1;
        userInfoLoaded = false;
        repoListLoaded = false;
        if (disposable != null ){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
