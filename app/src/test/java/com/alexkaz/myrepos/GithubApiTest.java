package com.alexkaz.myrepos;

import com.alexkaz.myrepos.model.api.GithubApi;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class GithubApiTest {

    private GithubApi githubApi;

    @Before
    public void initApi(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "token d18832da8d07c899418f83deddd95db9850110bb")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        }).build();

        githubApi = new Retrofit.Builder()
                .baseUrl(GithubApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build().create(GithubApi.class);
    }


    @Test
    public void testGetUserRepos() {
        githubApi.getUserRepos()
                .subscribe((repoEntities) -> {
                    assertThat(repoEntities.size(),is(10));
                }, Throwable::printStackTrace);

    }

    @Test
    public void testGetReposByName() throws IOException {
        githubApi.getReposByName("tetris")
                .subscribe(searchReposWrapper -> {
                    assertThat(searchReposWrapper,notNullValue());
                }, Throwable::printStackTrace);
    }
}
