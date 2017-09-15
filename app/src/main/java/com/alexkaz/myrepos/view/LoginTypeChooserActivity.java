package com.alexkaz.myrepos.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.presenter.Login0AuthPresenter;
import com.alexkaz.myrepos.ui.CustomToast;
import com.alexkaz.myrepos.utils.GithubConfig;

import javax.inject.Inject;

public class LoginTypeChooserActivity extends AppCompatActivity implements Login0AuthView {

    private static final int BASIC_AUTH_ACTIVITY = 200;

    @Inject
    Login0AuthPresenter presenter;

    private View progressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type_chooser);
        progressBarView = findViewById(R.id.progressBarDiv);
        initPresenter();
    }

    private void initPresenter() {
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    public void click(View view){
        if (view.getId() == R.id.oauthBtn){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, GithubConfig.getOauthUrl());
            startActivity(browserIntent);
        }
        if (view.getId() == R.id.BasicBtn){
            startActivityForResult(new Intent(this,BasicAuthActivity.class), BASIC_AUTH_ACTIVITY);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null){
            if (intent.getData() != null){
                presenter.handleCode(intent.getData().getQueryParameter("code"));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BASIC_AUTH_ACTIVITY){
            if (resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            } else if (resultCode == RESULT_CANCELED){
                showWarningMessage("Canceled!");
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("progressBar_showed", progressBarView.isShown());
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (state.getBoolean("progressBar_showed", false)){
            showLoading();
        } else {
            hideLoading();
        }
    }

    @Override
    public void authenticated() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showLoading() {
        progressBarView.setVisibility(View.VISIBLE);
        findViewById(R.id.oauthBtn).setEnabled(false);
        findViewById(R.id.BasicBtn).setEnabled(false);
    }

    @Override
    public void showWarningMessage(String message) {
        new CustomToast(this).showMessage(message);
    }

    @Override
    public void hideLoading() {
        progressBarView.setVisibility(View.INVISIBLE);
        findViewById(R.id.oauthBtn).setEnabled(true);
        findViewById(R.id.BasicBtn).setEnabled(true);
    }
}
