package com.alexkaz.myrepos.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    @Singleton
    @Provides
    @Named("token")
    String provideToken(){
        return "d18832da8d07c899418f83deddd95db9850110bb";
    }

}
