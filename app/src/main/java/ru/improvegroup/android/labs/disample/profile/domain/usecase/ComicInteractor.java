package ru.improvegroup.android.labs.disample.profile.domain.usecase;


import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.improvegroup.android.labs.disample.profile.data.ComicApiModel;
import ru.improvegroup.android.labs.disample.profile.data.XkcdApi;
import ru.improvegroup.android.labs.disample.profile.domain.model.Comic;


public final class ComicInteractor {

    private final XkcdApi api;

    private final Random random = new Random();

    @Inject
    public ComicInteractor(XkcdApi api) {
        this.api = api;
    }

    public Single<Comic> getLatestComic() {
        return api.getLatestComic()
                .map(this::mapComicApiModel);
    }

    public Single<Comic> getRandomComic() {
        return getLatestComic()
                .map(comic -> getRandomComicNumber(comic.getNumber()))
                .flatMap(this::getComicByNumber);
    }

    public Single<Comic> getComicByNumber(int number) {
        return api.getComicByNumber(number)
                .map(this::mapComicApiModel);
    }

    private int getRandomComicNumber(int latestComicNumber) {
        // comics are numerated from 1
        return random.nextInt(latestComicNumber) + 1;
    }

    private Comic mapComicApiModel(ComicApiModel apiModel) {
        return new Comic(
                apiModel.getNum(),
                apiModel.getImg(),
                apiModel.getSafe_title(),
                apiModel.getAlt());
    }
}
