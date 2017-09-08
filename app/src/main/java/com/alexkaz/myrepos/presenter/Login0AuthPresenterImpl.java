package com.alexkaz.myrepos.presenter;

import android.util.Log;

import com.alexkaz.myrepos.model.api.Github0AuthApi;
import com.alexkaz.myrepos.model.entities.TokenEntity;
import com.alexkaz.myrepos.model.services.ConnInfoHelper;
import com.alexkaz.myrepos.model.services.PrefsHelper;
import com.alexkaz.myrepos.view.Login0AuthView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login0AuthPresenterImpl implements Login0AuthPresenter {

    private Login0AuthView view;

    private Github0AuthApi authApi;
    private ConnInfoHelper connHelper;
    private PrefsHelper prefsHelper;

    public Login0AuthPresenterImpl(Github0AuthApi authApi, ConnInfoHelper connHelper, PrefsHelper prefsHelper) {
        this.authApi = authApi;
        this.connHelper = connHelper;
        this.prefsHelper = prefsHelper;
    }

    @Override
    public void bindView(Login0AuthView view) {
        this.view = view;
    }

    @Override
    public void handleCode(String code) {
        if (connHelper.isOnline()){
            view.showLoading();
            authApi.getToken(code).enqueue(new Callback<TokenEntity>() {

                @Override
                public void onResponse(Call<TokenEntity> call, Response<TokenEntity> response) {
                    view.hideLoading();
                    if (response.isSuccessful()){
                        String token = response.body().getAccessToken();
                        prefsHelper.saveToken("token " + token);
                        prefsHelper.setAuthenticated(true);
                        view.authenticated();
                        Log.d("myTag", "token - " + token);
                    } else {
                        view.showErrorMessage(response.message());
                    }
                }

                @Override
                public void onFailure(Call<TokenEntity> call, Throwable t) {
                    view.hideLoading();
                    view.showErrorMessage(t.getMessage());
                }
            });
        } else {
            view.showErrorMessage("No internet connection!");
        }
    }
}
