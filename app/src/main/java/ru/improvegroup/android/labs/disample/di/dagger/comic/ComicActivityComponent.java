package ru.improvegroup.android.labs.disample.di.dagger.comic;

import dagger.Component;
import ru.improvegroup.android.labs.disample.comic.presentation.ComicActivity;
import ru.improvegroup.android.labs.disample.di.dagger.app.ActivityScope;
import ru.improvegroup.android.labs.disample.di.dagger.app.AppComponent;


@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                ComicActivityModule.class
        }
)
public interface ComicActivityComponent {

    void inject(ComicActivity loginActivity);
}
