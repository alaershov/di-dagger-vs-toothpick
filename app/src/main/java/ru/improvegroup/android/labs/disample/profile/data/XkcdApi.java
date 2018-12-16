package ru.improvegroup.android.labs.disample.profile.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface XkcdApi {

    @GET("info.0.json")
    Single<ComicApiModel> getLatestComic();

    @GET("{number}/info.0.json")
    Single<ComicApiModel> getComicByNumber(@Path("number") int number);
}
