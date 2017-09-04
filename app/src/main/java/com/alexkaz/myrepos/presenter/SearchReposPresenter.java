package com.alexkaz.myrepos.presenter;

import com.alexkaz.myrepos.view.SearchReposView;

public interface SearchReposPresenter {

    void bindView(SearchReposView view);

    void load();

    void search();
}
