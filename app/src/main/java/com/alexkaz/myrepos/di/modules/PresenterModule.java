package com.alexkaz.myrepos.di.modules;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.presenter.SearchReposPresenter;
import com.alexkaz.myrepos.presenter.SearchReposPresenterImpl;
import com.alexkaz.myrepos.presenter.UserReposPresenter;
import com.alexkaz.myrepos.presenter.UserReposPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Singleton
    @Provides
    UserReposPresenter provideUserReposPresenter(GithubService githubService, ConnInfoHelper connInfoHelper){
        return new UserReposPresenterImpl(githubService, connInfoHelper);
    }

    @Singleton
    @Provides
    SearchReposPresenter provideSearchReposPresenter(GithubService githubService, ConnInfoHelper connInfoHelper){
        return new SearchReposPresenterImpl(githubService, connInfoHelper);
    }

}
