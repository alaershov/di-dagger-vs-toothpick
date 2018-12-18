package ru.improvegroup.android.labs.disample.di.toothpick;

import ru.improvegroup.android.labs.disample.comic.presentation.ComicActivity;
import ru.improvegroup.android.labs.disample.di.Di;
import ru.improvegroup.android.labs.disample.di.toothpick.app.AnalyticsModule;
import ru.improvegroup.android.labs.disample.di.toothpick.app.ApiModule;
import ru.improvegroup.android.labs.disample.di.toothpick.app.Scopes;
import toothpick.Scope;
import toothpick.Toothpick;


public final class ToothpickDi implements Di {

    @Override
    public void init() {
        Scope appScope = Toothpick.openScope(Scopes.APP);
        appScope.installModules(
                new AnalyticsModule(),
                new ApiModule());
    }

    @Override
    public void inject(ComicActivity comicActivity) {
        Scope comicScope = Toothpick.openScopes(Scopes.APP, Scopes.COMIC);
        comicScope.installModules();

        Toothpick.inject(comicActivity, comicScope);
    }

    @Override
    public void closeComicActivityScope() {
        Toothpick.closeScope(Scopes.COMIC);
    }
}
