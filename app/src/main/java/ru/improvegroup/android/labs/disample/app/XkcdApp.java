package ru.improvegroup.android.labs.disample.app;

import android.app.Application;

import ru.improvegroup.android.labs.disample.di.AppDi;
import timber.log.Timber;

public final class XkcdApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        AppDi.INSTANCE.init();
    }
}
