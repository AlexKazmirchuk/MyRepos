package com.alexkaz.myrepos.di.modules;

import com.alexkaz.myrepos.model.api.BasicAuthApi;
import com.alexkaz.myrepos.model.api.Github0AuthApi;
import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.model.services.PrefsHelper;
import com.alexkaz.myrepos.presenter.BasicAuthPresenter;
import com.alexkaz.myrepos.presenter.BasicAuthPresenterImpl;
import com.alexkaz.myrepos.presenter.Login0AuthPresenter;
import com.alexkaz.myrepos.presenter.Login0AuthPresenterImpl;
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

    @Singleton
    @Provides
    BasicAuthPresenter provideBasicAuthPresenter(BasicAuthApi authApi, ConnInfoHelper connInfoHelper, PrefsHelper prefsHelper){
        return new BasicAuthPresenterImpl(authApi, connInfoHelper, prefsHelper);
    }

    @Singleton
    @Provides
    Login0AuthPresenter provideLogin0AuthPresenterImpl(Github0AuthApi authApi, ConnInfoHelper connInfoHelper, PrefsHelper prefsHelper){
        return new Login0AuthPresenterImpl(authApi, connInfoHelper, prefsHelper);
    }

}
