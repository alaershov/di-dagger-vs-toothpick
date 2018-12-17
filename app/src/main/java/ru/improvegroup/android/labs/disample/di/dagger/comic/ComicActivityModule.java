package ru.improvegroup.android.labs.disample.di.dagger.comic;

import dagger.Module;
import dagger.Provides;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.comic.domain.usecase.ComicInteractor;
import ru.improvegroup.android.labs.disample.comic.presentation.ComicPresenter;
import ru.improvegroup.android.labs.disample.di.dagger.app.ActivityScope;

@Module
public final class ComicActivityModule {

    @Provides
    @ActivityScope
    ComicPresenter provideComicPresenter(
            ComicInteractor comicInteractor,
            AnalyticsTracker analyticsTracker) {
        return new ComicPresenter(comicInteractor, analyticsTracker);
    }
}
