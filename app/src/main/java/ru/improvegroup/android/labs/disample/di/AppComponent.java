package ru.improvegroup.android.labs.disample.di;

import dagger.Component;
import ru.improvegroup.android.labs.disample.profile.presentation.LoginActivity;


@Component(
        modules = {
                LoginModule.class
        }
)
public interface AppComponent {

    void inject(LoginActivity loginActivity);
}
