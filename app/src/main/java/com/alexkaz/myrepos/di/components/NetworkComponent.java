package com.alexkaz.myrepos.di.components;

import com.alexkaz.myrepos.di.modules.NetworkModule;
import com.alexkaz.myrepos.di.modules.PreferencesModule;
import com.alexkaz.myrepos.model.services.GithubService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, PreferencesModule.class})
public interface NetworkComponent {
    GithubService githubService();
}
