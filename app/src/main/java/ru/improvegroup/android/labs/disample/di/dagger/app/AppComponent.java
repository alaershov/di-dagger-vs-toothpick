package ru.improvegroup.android.labs.disample.di.dagger.app;

import dagger.Component;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.comic.data.XkcdApi;


@Component(
        modules = {
                ApiModule.class,
                AnalyticsModule.class
        }
)
public interface AppComponent {

    XkcdApi getXkcdApi();

    AnalyticsTracker getAnalyticsTracker();
}
