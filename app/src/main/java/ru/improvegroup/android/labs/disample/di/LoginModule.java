package ru.improvegroup.android.labs.disample.di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.analytics.FirebaseAnalyticsTracker;
import ru.improvegroup.android.labs.disample.LoginApi;


@Module
public abstract class LoginModule {

    @Provides
    public static LoginApi provideLoginApi() {
        return new Retrofit.Builder()
                .baseUrl("http://www.example.com")
                .build()
                .create(LoginApi.class);
    }

    @Binds
    public abstract AnalyticsTracker provideAnalyticsTracker(
            FirebaseAnalyticsTracker firebaseAnalyticsTracker);
}
