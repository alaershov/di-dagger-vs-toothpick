package ru.improvegroup.android.labs.disample.profile.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface XkcdApi {

    @GET("info.0.json")
    Single<ComicApiModel> getLatestComic();

    @GET("{number}/info.0.json")
    Single<ComicApiModel> getComicByNumber(@Query("number") int number);
}
