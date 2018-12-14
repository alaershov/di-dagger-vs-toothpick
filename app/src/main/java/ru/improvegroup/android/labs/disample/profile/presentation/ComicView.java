package ru.improvegroup.android.labs.disample.profile.presentation;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.improvegroup.android.labs.disample.profile.domain.model.Comic;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface ComicView extends MvpView {

    void showComic(Comic comic);
}
