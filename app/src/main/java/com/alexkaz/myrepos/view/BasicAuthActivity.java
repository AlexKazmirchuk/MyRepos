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
            if (validate(userName,password)){
                presenter.login(userName,password);
            }
        });

        initPresenter();
    }

    private void initPresenter(){
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    private boolean validate(String userName, String password){
        if (userName.isEmpty() && password.isEmpty()){
            showBadCredentialsError("Please enter the required fields!");
            return false;
        }

        if (userName.isEmpty()){
            showBadCredentialsError("Please enter username!");
            return false;
        }
        if (password.isEmpty()){
            showBadCredentialsError("Please enter password!");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
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
