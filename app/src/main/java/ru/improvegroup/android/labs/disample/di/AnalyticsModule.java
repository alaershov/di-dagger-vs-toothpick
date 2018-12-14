package ru.improvegroup.android.labs.disample.di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.analytics.FirebaseAnalyticsTracker;
import ru.improvegroup.android.labs.disample.profile.data.XkcdApi;


@Module
public abstract class AnalyticsModule {

    @Binds
    public abstract AnalyticsTracker provideAnalyticsTracker(
            FirebaseAnalyticsTracker firebaseAnalyticsTracker);
}
