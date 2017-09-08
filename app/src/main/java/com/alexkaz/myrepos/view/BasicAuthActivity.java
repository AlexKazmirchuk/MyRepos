package com.alexkaz.myrepos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.presenter.BasicAuthPresenter;

import javax.inject.Inject;

public class BasicAuthActivity extends AppCompatActivity implements BasicAuthView{

    @Inject
    BasicAuthPresenter presenter;

    private EditText userNameET;
    private EditText passwordET;
    private Button loginBtn;
    private View progressBarDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_auth);

        initComponents();
    }

    private void initComponents(){
        userNameET = findViewById(R.id.userNameET);
        passwordET = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        progressBarDiv = findViewById(R.id.loginPBDiv);

        loginBtn.setOnClickListener(event -> {
            String userName = userNameET.getText().toString();
            String password = passwordET.getText().toString();
            presenter.login(userName,password);
        });

        initPresenter();
    }

    private void initPresenter(){
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("progressBar_showed", progressBarDiv.isShown());
        outState.putString("username",userNameET.getText().toString());
        outState.putString("password",passwordET.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        userNameET.setText(state.getString("username",""));
        passwordET.setText(state.getString("password",""));
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
    public void showBadCredentialsError(String message) {
        // TODO: create custom toast message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBarDiv.setVisibility(View.VISIBLE);
        loginBtn.setEnabled(false);
        userNameET.setEnabled(false);
        passwordET.setEnabled(false);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        progressBarDiv.setVisibility(View.INVISIBLE);
        loginBtn.setEnabled(true);
        userNameET.setEnabled(true);
        passwordET.setEnabled(true);
    }
}
