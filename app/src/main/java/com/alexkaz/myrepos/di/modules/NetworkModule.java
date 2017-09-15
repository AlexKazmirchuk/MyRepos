package com.alexkaz.myrepos.di.modules;

import android.content.Context;

import com.alexkaz.myrepos.model.api.BasicAuthApi;
import com.alexkaz.myrepos.model.api.GitHub0AuthApi;
import com.alexkaz.myrepos.model.api.GitHubApi;
import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.ConnInfoHelperImpl;
import com.alexkaz.myrepos.model.services.PrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    GitHubApi provideGithubApi(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(GitHubApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(GitHubApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(PrefsHelper prefsHelper){

        String token = prefsHelper.getToken();

        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Authorization", token)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }).build();
    }

    @Provides
    @Singleton
    ConnInfoHelper provideConnInfoHelper(Context context){
        return new ConnInfoHelperImpl(context);
    }

    @Provides
    @Singleton
    BasicAuthApi provideBasicAuthApi(){
        return new Retrofit.Builder()
                .baseUrl(BasicAuthApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BasicAuthApi.class);
    }

    @Provides
    @Singleton
    GitHub0AuthApi provideGithub0AuthApi(){
        return new Retrofit.Builder()
                .baseUrl(GitHub0AuthApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GitHub0AuthApi.class);
    }
}
