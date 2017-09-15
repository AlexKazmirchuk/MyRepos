package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GitHubService;
import com.alexkaz.myrepos.view.SearchReposView;

import io.reactivex.disposables.Disposable;

public class SearchReposPresenterImpl implements SearchReposPresenter {

    private SearchReposView view;
    private GitHubService gitHubService;
    private ConnInfoHelper helper;

    private String mQuery = "";
    private int page = 1;

    private Disposable disposable;

    public SearchReposPresenterImpl(GitHubService gitHubService, ConnInfoHelper connInfoHelper) {
        this.gitHubService = gitHubService;
        this.helper = connInfoHelper;
    }

    @Override
    public void bindView(SearchReposView view) {
        this.view = view;
    }

    @Override
    public void search() {
        if (!view.getQueryText().isEmpty()){
            page = 1;
            view.clearUpList();
            mQuery = view.getQueryText();
            if (disposable != null ){
                if (!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
            load();
        } else {
            view.showWarningMessage("Please enter repository name!");
        }
    }

    @Override
    public void reset() {
        page = 1;
        if (disposable != null ){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }

    @Override
    public void load() {
        if (helper.isOnline()){
            view.showLoading();
            int perPage = 8;
            disposable = gitHubService.getReposByName(mQuery, page, perPage).subscribe(
                    wrapper ->{
                        view.showRepos(wrapper.getItems());
                        page++;
                        view.hideLoading();
                        if (wrapper.getItems().size() == 0){
                            view.showWarningMessage("We couldn’t find any repositories");
                        }
                    }
                    , throwable -> {
                        view.hideLoading();
                        view.showWarningMessage(throwable.getMessage());
                    });
        } else {
            view.showWarningMessage("No internet connection!");
        }
    }

}
