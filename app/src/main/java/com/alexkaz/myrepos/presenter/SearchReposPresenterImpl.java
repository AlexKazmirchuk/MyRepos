package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.view.SearchReposView;

import io.reactivex.disposables.Disposable;

public class SearchReposPresenterImpl implements SearchReposPresenter {

    private SearchReposView view;
    private GithubService githubService;
    private ConnInfoHelper helper;

    private String mQuery = "";
    private int page = 1;

    private Disposable disposable;

    public SearchReposPresenterImpl(GithubService githubService, ConnInfoHelper connInfoHelper) {
        this.githubService = githubService;
        this.helper = connInfoHelper;
    }

    @Override
    public void bindView(SearchReposView view) {
        this.view = view;
    }

    @Override
    public void search() {
        page = 1;
        view.clearUpList();
        mQuery = view.getQueryText();
        if (disposable != null ){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
        load();
    }

    @Override
    public void load() {
        if (helper.isOnline()){
            view.showLoading();
            int perPage = 8;
            disposable = githubService.getReposByName(mQuery, page, perPage).subscribe(
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
