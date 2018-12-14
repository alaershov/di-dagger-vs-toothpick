package ru.improvegroup.android.labs.disample.profile.presentation;

import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.profile.domain.LoginInteractor;

import javax.inject.Inject;


public final class LoginPresenter {

    private final LoginInteractor loginInteractor;
    private final AnalyticsTracker analyticsTracker;

    @Inject
    public LoginPresenter(
            LoginInteractor loginInteractor,
            AnalyticsTracker analyticsTracker) {
        this.loginInteractor = loginInteractor;
        this.analyticsTracker = analyticsTracker;
    }

    public void login(String login, String password) {
        loginInteractor.login(login, password);
        analyticsTracker.track("login");
    }
}
