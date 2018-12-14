package ru.improvegroup.android.labs.disample.profile.data;

import retrofit2.http.GET;
import retrofit2.http.Query;

interface LoginApi {

    @GET
    void login(@Query("login") String login, @Query("password") String password);
}
