package com.alexkaz.myrepos.di.components;

import com.alexkaz.myrepos.di.modules.AppModule;
import com.alexkaz.myrepos.di.modules.NetworkModule;
import com.alexkaz.myrepos.di.modules.PreferencesModule;
import com.alexkaz.myrepos.di.modules.PresenterModule;
import com.alexkaz.myrepos.model.services.GithubService;
import com.alexkaz.myrepos.presenter.UserReposPresenter;
import com.alexkaz.myrepos.presenter.UserReposPresenterImpl;
import com.alexkaz.myrepos.view.UserReposActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,
                      PreferencesModule.class,
                      PresenterModule.class,
                      AppModule.class})
public interface MyComponent {
    void inject(UserReposActivity userReposActivity);
}
