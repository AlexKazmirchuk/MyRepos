package com.alexkaz.myrepos.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.presenter.Login0AuthPresenter;
import com.alexkaz.myrepos.utils.GithubConfig;

import javax.inject.Inject;

public class LoginTypeChooserActivity extends AppCompatActivity implements Login0AuthView {

    @Inject
    Login0AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        handleIntent(getIntent());
    }

    private void initPresenter() {
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    private void handleIntent(Intent intent) {
        if (intent != null){
            if (intent.getData() != null){
                presenter.handleCode(intent.getData().getQueryParameter("code"));
            }
        }
    }

    public void click(View view){
        if (view.getId() == R.id.oauthBtn){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, GithubConfig.getOauthUrl());
            startActivity(browserIntent);
        }
        if (view.getId() == R.id.BasicBtn){
            startActivity(new Intent(this,BasicAuthActivity.class));
        }
    }

    @Override
    public void authenticated() {
        //todo go to UserReposActivity
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"Loading show",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this,"Loading hide",Toast.LENGTH_LONG).show();
    }
}
