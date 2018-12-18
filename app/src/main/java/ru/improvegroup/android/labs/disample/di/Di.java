package ru.improvegroup.android.labs.disample.di;

import ru.improvegroup.android.labs.disample.comic.presentation.ComicActivity;

public interface Di {

    void init();

    void inject(ComicActivity comicActivity);

    void closeComicActivityScope();
}
