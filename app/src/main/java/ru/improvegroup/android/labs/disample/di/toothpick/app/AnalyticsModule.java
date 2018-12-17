package ru.improvegroup.android.labs.disample.di.toothpick.app;

import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.analytics.FirebaseAnalyticsTracker;
import toothpick.config.Module;


public final class AnalyticsModule extends Module {

    public AnalyticsModule() {
        bind(AnalyticsTracker.class)
                .to(FirebaseAnalyticsTracker.class)
                .singletonInScope();
    }
}
