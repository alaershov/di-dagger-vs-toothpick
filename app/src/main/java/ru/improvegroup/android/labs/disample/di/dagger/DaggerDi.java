package ru.improvegroup.android.labs.disample.di.dagger;

import ru.improvegroup.android.labs.disample.comic.presentation.ComicActivity;
import ru.improvegroup.android.labs.disample.di.Di;
import ru.improvegroup.android.labs.disample.di.dagger.app.AppComponent;
import ru.improvegroup.android.labs.disample.di.dagger.app.DaggerAppComponent;
import ru.improvegroup.android.labs.disample.di.dagger.comic.ComicActivityComponent;
import ru.improvegroup.android.labs.disample.di.dagger.comic.DaggerComicActivityComponent;


public final class DaggerDi implements Di {

    private AppComponent appComponent;
    private ComicActivityComponent comicActivityComponent;

    @Override
    public void init() {
        appComponent = DaggerAppComponent.create();
    }

    @Override
    public void inject(ComicActivity comicActivity) {
        if (comicActivityComponent == null) {
            comicActivityComponent = DaggerComicActivityComponent.builder()
                    .appComponent(appComponent)
                    .build();
        }

        comicActivityComponent.inject(comicActivity);
    }

    public void closeComicActivityScope() {
        comicActivityComponent = null;
    }
}
