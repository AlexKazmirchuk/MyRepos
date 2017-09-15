package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.SearchReposView;

public interface SearchReposPresenter extends BasePresenter <SearchReposView> {

    void load();

    void search();

    void reset();
}
