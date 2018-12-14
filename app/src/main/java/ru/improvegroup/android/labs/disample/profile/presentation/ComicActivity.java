package ru.improvegroup.android.labs.disample.profile.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.improvegroup.android.labs.disample.GlideApp;
import ru.improvegroup.android.labs.disample.R;
import ru.improvegroup.android.labs.disample.di.AppComponent;
import ru.improvegroup.android.labs.disample.di.DaggerAppComponent;
import ru.improvegroup.android.labs.disample.profile.domain.model.Comic;


public final class ComicActivity extends MvpAppCompatActivity implements ComicView {

    private TextView titleTextView;
    private ImageView comicImageView;
    private TextView commentTextView;

    @InjectPresenter
    ComicPresenter presenter;
    @Inject
    Provider<ComicPresenter> presenterProvider;

    @ProvidePresenter
    ComicPresenter comicPresenter() {
        return presenterProvider.get();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        findViews();
    }

    private void inject() {
        AppComponent appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
    }

    private void findViews() {
        titleTextView = findViewById(R.id.text_title);
        comicImageView = findViewById(R.id.image_comic);
        commentTextView = findViewById(R.id.text_comment);
    }

    @Override
    public void showComic(Comic comic) {
        titleTextView.setText(comic.getTitle());

        GlideApp.with(this)
                .load(comic.getImageUrl())
                .fitCenter()
                .into(comicImageView);

        commentTextView.setText(comic.getComment());
    }
}
