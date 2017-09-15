package com.alexkaz.myrepos.di.modules;

import com.alexkaz.myrepos.model.api.BasicAuthApi;
import com.alexkaz.myrepos.model.api.GitHub0AuthApi;
import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.GitHubService;
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
    UserReposPresenter provideUserReposPresenter(GitHubService gitHubService, ConnInfoHelper connInfoHelper){
        return new UserReposPresenterImpl(gitHubService, connInfoHelper);
    }

    @Singleton
    @Provides
    SearchReposPresenter provideSearchReposPresenter(GitHubService gitHubService, ConnInfoHelper connInfoHelper){
        return new SearchReposPresenterImpl(gitHubService, connInfoHelper);
    }

    @Singleton
    @Provides
    BasicAuthPresenter provideBasicAuthPresenter(BasicAuthApi authApi, ConnInfoHelper connInfoHelper, PrefsHelper prefsHelper){
        return new BasicAuthPresenterImpl(authApi, connInfoHelper, prefsHelper);
    }

    @Singleton
    @Provides
    Login0AuthPresenter provideLogin0AuthPresenterImpl(GitHub0AuthApi authApi, ConnInfoHelper connInfoHelper, PrefsHelper prefsHelper){
        return new Login0AuthPresenterImpl(authApi, connInfoHelper, prefsHelper);
    }

}
