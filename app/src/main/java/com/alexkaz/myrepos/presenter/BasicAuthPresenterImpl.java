package com.alexkaz.myrepos.presenter;

import android.util.Log;

import com.alexkaz.myrepos.model.api.BasicAuthApi;
import com.alexkaz.myrepos.model.entities.UserEntity;
import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.PrefsHelper;
import com.alexkaz.myrepos.view.BasicAuthView;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicAuthPresenterImpl implements BasicAuthPresenter {

    private BasicAuthView view;
    private BasicAuthApi authApi;
    private ConnInfoHelper connHelper;
    private PrefsHelper prefsHelper;

    public BasicAuthPresenterImpl(BasicAuthApi authApi, ConnInfoHelper connHelper, PrefsHelper prefsHelper) {
        this.authApi = authApi;
        this.connHelper = connHelper;
        this.prefsHelper = prefsHelper;
    }

    @Override
    public void bindView(BasicAuthView view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        //todo impl later
        if (connHelper.isOnline()){
            view.showLoading();
            authApi.checkCredentials(Credentials.basic(username,password)).enqueue(new Callback<UserEntity>() {
                @Override
                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                    view.hideLoading();
                    if (response.isSuccessful()){
                        if (response.code() == 200){
                            prefsHelper.saveToken(Credentials.basic(username,password));
                            prefsHelper.setAuthenticated(true);
                            view.authenticated();
                        } else {
                            view.showBadCredentialsError();
                        }
                    } else {
                        view.showBadCredentialsError();
                    }
                }

                @Override
                public void onFailure(Call<UserEntity> call, Throwable t) {
                    view.hideLoading();
                    view.showErrorMessage(t.getMessage());
                }
            });
        } else {
            view.showErrorMessage("No internet connection!");
        }

    }
}
