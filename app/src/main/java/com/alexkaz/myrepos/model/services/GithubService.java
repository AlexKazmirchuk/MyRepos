package com.alexkaz.myrepos.model.services;

import com.alexkaz.myrepos.model.api.GithubApi;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.model.entities.SearchReposWrapper;
import com.alexkaz.myrepos.model.entities.UserEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GithubService {

    private GithubApi githubApi;

    @Inject
    public GithubService(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    public Observable<List<RepoEntity>> getUserRepos(){
        return githubApi.getUserRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<RepoEntity>> getUserRepos(int page, int perPage){
        return githubApi.getUserRepos(page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SearchReposWrapper> getReposByName(String name){
        return githubApi.getReposByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SearchReposWrapper> getReposByName(String name, int page, int perPage){
        return githubApi.getReposByName(name, page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserEntity> getUser(){
        return githubApi.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
