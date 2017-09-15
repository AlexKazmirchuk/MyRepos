package com.alexkaz.myrepos.di.modules;

import android.content.Context;

import com.alexkaz.myrepos.model.services.PrefsHelper;
import com.alexkaz.myrepos.model.services.PrefsHelperImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    @Singleton
    @Provides
    PrefsHelper providePrefsHelper(Context context){
        return new PrefsHelperImpl(context);
    }

}
