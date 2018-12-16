package ru.improvegroup.android.labs.disample.di.dagger.comic;

import dagger.Component;
import ru.improvegroup.android.labs.disample.di.dagger.app.AppComponent;
import ru.improvegroup.android.labs.disample.comic.presentation.ComicActivity;


@Component(
        dependencies = AppComponent.class
)
public interface ComicActivityComponent {

    void inject(ComicActivity loginActivity);
}
