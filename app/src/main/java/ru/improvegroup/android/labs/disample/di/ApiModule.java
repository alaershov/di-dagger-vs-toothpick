package ru.improvegroup.android.labs.disample.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.improvegroup.android.labs.disample.profile.data.XkcdApi;


@Module
public class ApiModule {

    private static final String BASE_URL = "https://xkcd.com/";

    @Provides
    public XkcdApi provideLoginApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(XkcdApi.class);
    }
}
