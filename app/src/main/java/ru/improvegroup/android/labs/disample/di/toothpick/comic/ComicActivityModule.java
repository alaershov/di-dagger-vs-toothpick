package ru.improvegroup.android.labs.disample.di.toothpick.comic;

import ru.improvegroup.android.labs.disample.comic.presentation.ComicPresenter;
import toothpick.config.Module;


public final class ComicActivityModule extends Module {

    public ComicActivityModule() {
        bind(ComicPresenter.class)
                .singletonInScope();
    }
}
