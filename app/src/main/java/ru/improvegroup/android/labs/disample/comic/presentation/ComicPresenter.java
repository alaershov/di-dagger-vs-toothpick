package ru.improvegroup.android.labs.disample.comic.presentation;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import ru.improvegroup.android.labs.disample.analytics.AnalyticsTracker;
import ru.improvegroup.android.labs.disample.comic.domain.model.Comic;
import ru.improvegroup.android.labs.disample.comic.domain.usecase.ComicInteractor;
import timber.log.Timber;


@InjectViewState
public final class ComicPresenter extends MvpPresenter<ComicView> {

    private final ComicInteractor comicInteractor;
    private final AnalyticsTracker analyticsTracker;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    private Comic latestComic;
    @Nullable
    private Comic comic;

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

    public void onPreviousClicked() {
        if (comic == null) {
            return;
        }

        loadComicByNumber(comic.getNumber() - 1);
    }

    public void onNextClicked() {
        if (comic == null) {
            return;
        }

        loadComicByNumber(comic.getNumber() + 1);
    }

    public void onFirstClicked() {
        if (comic == null) {
            return;
        }

        loadComicByNumber(1);
    }

    public void onLatestClicked() {
        if (comic == null) {
            return;
        }

        loadLatestComic();
    }

    @SuppressLint("CheckResult")
    public void onRandomClicked() {
        comicInteractor.getRandomComic()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(compositeDisposable::add)
                .doOnSubscribe(disposable -> showProgress(true))
                .doAfterTerminate(() -> showProgress(false))
                .subscribe(
                        comic -> {
                            showComic(comic);
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
                .doOnSubscribe(disposable -> showProgress(true))
                .doAfterTerminate(() -> showProgress(false))
                .subscribe(
                        comic -> {
                            latestComic = comic;
                            showComic(comic);
                            analyticsTracker.track("latest comic success");
                        },
                        throwable -> {
                            analyticsTracker.track("latest comic error");
                            handleError(throwable);
                        });
    }

    @SuppressLint("CheckResult")
    private void loadComicByNumber(int number) {
        comicInteractor.getComicByNumber(number)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(compositeDisposable::add)
                .doOnSubscribe(disposable -> showProgress(true))
                .doAfterTerminate(() -> showProgress(false))
                .subscribe(
                        comic -> {
                            showComic(comic);
                            analyticsTracker.track("comic by number success");
                        },
                        throwable -> {
                            analyticsTracker.track("comic by number success");
                            handleError(throwable);
                        });
    }

    private void showProgress(boolean inProgress) {
        getViewState().showProgress(inProgress);
        refreshButtons(inProgress);
    }

    private void showComic(Comic comic) {
        this.comic = comic;
        getViewState().showComic(comic);
    }

    private void refreshButtons(boolean inProgress) {
        boolean previousEnabled = false;
        boolean nextEnabled = false;
        boolean firstEnabled = false;
        boolean latestEnabled = false;
        boolean randomEnabled = false;

        if (comic != null && latestComic != null) {
            previousEnabled = !inProgress
                    && comic.getNumber() > 1;
            nextEnabled = !inProgress
                    && comic.getNumber() < latestComic.getNumber();

            firstEnabled = !inProgress;
            latestEnabled = !inProgress;

            randomEnabled = !inProgress;
        }

        getViewState().enableButtons(previousEnabled, nextEnabled, firstEnabled, latestEnabled, randomEnabled);
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
