package ru.improvegroup.android.labs.disample.di.dagger.app;

import dagger.Binds;
import dagger.Module;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.analytics.FirebaseAnalyticsTracker;


@Module
public abstract class AnalyticsModule {

    @Binds
    public abstract AnalyticsTracker provideAnalyticsTracker(
            FirebaseAnalyticsTracker firebaseAnalyticsTracker);
}
