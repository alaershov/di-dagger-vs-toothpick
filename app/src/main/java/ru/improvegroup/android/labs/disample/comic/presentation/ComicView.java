package ru.improvegroup.android.labs.disample.comic.presentation;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.improvegroup.android.labs.disample.comic.domain.model.Comic;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface ComicView extends MvpView {

    void showComic(Comic comic);

    void showProgress(boolean inProgress);

    void enableButtons(
            boolean previousEnabled,
            boolean nextEnabled,
            boolean firstEnabled,
            boolean latestEnabled,
            boolean randomEnabled);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String message);
}
