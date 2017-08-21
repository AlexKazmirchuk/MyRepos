package com.alexkaz.myrepos.di.modules;

import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
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

}
