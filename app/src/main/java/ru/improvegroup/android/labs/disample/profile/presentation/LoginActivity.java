package ru.improvegroup.android.labs.disample.profile.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import ru.improvegroup.android.labs.disample.DaggerAppComponent;
import ru.improvegroup.android.labs.disample.di.AppComponent;

import javax.inject.Inject;


public final class LoginActivity extends Activity {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
    }
}
