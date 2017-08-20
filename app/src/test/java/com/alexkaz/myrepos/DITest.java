package com.alexkaz.myrepos;


import com.alexkaz.myrepos.di.components.DaggerNetworkComponent;
import com.alexkaz.myrepos.di.components.NetworkComponent;
import com.alexkaz.myrepos.model.services.GithubService;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DITest {

    private GithubService githubService;

    @Before
    public void init(){
        NetworkComponent component = DaggerNetworkComponent.builder().build();
        githubService = component.githubService();
    }

    @Test
    public void testGithubService(){
        githubService.getUserRepos().subscribe(userRepos -> {
            assertThat(userRepos.size(), is(10));
        });
    }

}
