package ru.improvegroup.android.labs.disample.profile.domain;

import ru.improvegroup.android.labs.disample.LoginApi;

import javax.inject.Inject;


public final class LoginInteractor {

    private final LoginApi loginApi;

    @Inject
    public LoginInteractor(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    public void login(String login, String password) {
        loginApi.login(login, password);
    }
}
