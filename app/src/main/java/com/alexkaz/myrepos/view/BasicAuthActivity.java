package com.alexkaz.myrepos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    public void authenticated() {
        // todo start activity with user repos and info
    }

    @Override
    public void showBadCredentialsError() {
        //todo show that username or password is wrong
    }

    @Override
    public void showLoading() {
        progressBarDiv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        progressBarDiv.setVisibility(View.INVISIBLE);
    }
}
