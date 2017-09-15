package com.alexkaz.myrepos.model.api;

import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.model.entities.SearchReposWrapper;
import com.alexkaz.myrepos.model.entities.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    String END_POINT = "https://api.github.com/";

    @GET("user/repos")
    Observable<List<RepoEntity>> getUserRepos(@Query("page") int page, @Query("per_page") int perPage);

    @GET("search/repositories")
    Observable<SearchReposWrapper> getReposByName(@Query("q") String name);

    @GET("search/repositories")
    Observable<SearchReposWrapper> getReposByName(@Query("q") String name,@Query("page") int page, @Query("per_page") int perPage);

    @GET("user")
    Observable<UserEntity> getUser();
}
