package ru.improvegroup.android.labs.disample.profile.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.Nullable;
import ru.improvegroup.android.labs.disample.R;
import ru.improvegroup.android.labs.disample.app.GlideApp;
import ru.improvegroup.android.labs.disample.di.AppComponent;
import ru.improvegroup.android.labs.disample.di.DaggerAppComponent;
import ru.improvegroup.android.labs.disample.profile.domain.model.Comic;


public final class ComicActivity extends MvpAppCompatActivity implements ComicView {

    private ProgressBar progressBar;

    private TextView titleTextView;
    private ImageView comicImageView;
    private TextView commentTextView;

    private Button previousButton;
    private Button nextButton;
    private Button randomButton;

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
        initButtons();
    }

    private void inject() {
        AppComponent appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
    }

    private void findViews() {
        progressBar = findViewById(R.id.progressBar);

        titleTextView = findViewById(R.id.text_title);
        comicImageView = findViewById(R.id.image_comic);
        commentTextView = findViewById(R.id.text_comment);

        previousButton = findViewById(R.id.button_previous);
        nextButton = findViewById(R.id.button_next);
        randomButton = findViewById(R.id.button_random);
    }

    private void initButtons() {
        previousButton.setOnClickListener(v -> presenter.onPreviousClicked());
        nextButton.setOnClickListener(v -> presenter.onNextClicked());
        randomButton.setOnClickListener(v -> presenter.onRandomClicked());
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

    @Override
    public void showProgress(boolean inProgress) {
        progressBar.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        previousButton.setEnabled(!inProgress);
        nextButton.setEnabled(!inProgress);
        randomButton.setEnabled(!inProgress);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(progressBar, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void enableButtons(boolean previousEnabled, boolean nextEnabled) {
        previousButton.setEnabled(previousEnabled);
        nextButton.setEnabled(nextEnabled);
    }
}
