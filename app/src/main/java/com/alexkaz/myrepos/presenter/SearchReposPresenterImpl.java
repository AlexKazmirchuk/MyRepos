package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.view.SearchReposView;

public class SearchReposPresenterImpl implements SearchReposPresenter {

    private SearchReposView view;
    private GithubService githubService;
    private ConnInfoHelper helper;

    private String mQuery = "";
    private int page = 1;
    private int perPage = 8;

    public SearchReposPresenterImpl(GithubService githubService, ConnInfoHelper connInfoHelper) {
        this.githubService = githubService;
        this.helper = connInfoHelper;
    }

    @Override
    public void bindView(SearchReposView view) {
        this.view = view;
        page = 1;
        mQuery = "";
    }

    @Override
    public void search() {
        page = 1;
        view.clearUpList();
        mQuery = view.getQueryText();
        load();
    }

    @Override
    public void load() {
        if (helper.isOnline()){
            view.showLoading();
            githubService.getReposByName(mQuery, page,perPage).subscribe(
                    wrapper ->{
                        view.showRepos(wrapper.getItems());
                        page++;
                        view.hideLoading();
                    }
                    , throwable -> {
                        view.hideLoading();
                        view.showErrorMessage(throwable.getMessage());
                    });
        } else {
            view.showErrorMessage("No internet connection!");
        }
    }

}
