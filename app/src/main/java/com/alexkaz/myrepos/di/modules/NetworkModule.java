package com.alexkaz.myrepos.di.modules;

import android.content.Context;

import com.alexkaz.myrepos.model.api.GithubApi;
import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.ConnInfoHelperImpl;

import javax.inject.Named;
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
    GithubApi provideGithubApi(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(GithubApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(GithubApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(@Named("token")String token){
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Authorization", "token " + token)
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

}
