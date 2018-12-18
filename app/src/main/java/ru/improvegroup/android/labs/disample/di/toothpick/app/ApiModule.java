package ru.improvegroup.android.labs.disample.di.toothpick.app;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.improvegroup.android.labs.disample.comic.data.XkcdApi;
import toothpick.config.Module;


public final class ApiModule extends Module {

    public ApiModule() {
        bind(OkHttpClient.class)
                .toProvider(OkHttpClientProvider.class)
                .providesSingletonInScope();

        bind(XkcdApi.class)
                .toProvider(XkcdApiProvider.class)
                .providesSingletonInScope();
    }

    public static final class XkcdApiProvider implements Provider<XkcdApi> {

        private static final String BASE_URL = "https://xkcd.com/";

        private final OkHttpClient okHttpClient;

        @Inject
        public XkcdApiProvider(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
        }

        @Override
        public XkcdApi get() {
            return new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()
                    .create(XkcdApi.class);
        }
    }

    public static final class OkHttpClientProvider implements Provider<OkHttpClient> {

        @Inject
        public OkHttpClientProvider() {
        }

        @Override
        public OkHttpClient get() {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
    }
}
