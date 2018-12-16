package ru.improvegroup.android.labs.disample.profile.presentation;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.profile.domain.usecase.ComicInteractor;
import timber.log.Timber;


@InjectViewState
public final class ComicPresenter extends MvpPresenter<ComicView> {

    private final ComicInteractor comicInteractor;
    private final AnalyticsTracker analyticsTracker;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ComicPresenter(
            ComicInteractor comicInteractor,
            AnalyticsTracker analyticsTracker) {
        this.comicInteractor = comicInteractor;
        this.analyticsTracker = analyticsTracker;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadLatestComic();
    }

    @SuppressLint("CheckResult")
    public void onRandomClicked() {
        comicInteractor.getRandomComic()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(compositeDisposable::add)
                .doOnSubscribe(disposable -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(
                        comic -> {
                            getViewState().showComic(comic);
                            analyticsTracker.track("random comic success");
                        },
                        throwable -> {
                            analyticsTracker.track("random comic success");
                            handleError(throwable);
                        });
    }

    @SuppressLint("CheckResult")
    private void loadLatestComic() {
        comicInteractor.getLatestComic()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(compositeDisposable::add)
                .doOnSubscribe(disposable -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(
                        comic -> {
                            getViewState().showComic(comic);
                            analyticsTracker.track("latest comic success");
                        },
                        throwable -> {
                            analyticsTracker.track("latest comic error");
                            handleError(throwable);
                        });
    }

    private void handleError(Throwable throwable) {
        Timber.w(throwable);
        String localizedMessage = throwable.getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = "Unknown error";
        }
        getViewState().showError(localizedMessage);
    }
}
