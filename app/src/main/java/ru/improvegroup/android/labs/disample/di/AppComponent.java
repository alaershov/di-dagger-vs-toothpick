package ru.improvegroup.android.labs.disample.di;

import dagger.Component;
import ru.improvegroup.android.labs.disample.profile.presentation.ComicActivity;


@Component(
        modules = {
                ApiModule.class,
                AnalyticsModule.class
        }
)
public interface AppComponent {

    void inject(ComicActivity loginActivity);
}
