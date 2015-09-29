package com.mkurutin.narrator.config;

import android.app.Application;
import android.media.MediaPlayer;
import com.mkurutin.narrator.services.ApplicationStateStore;
import dagger.Module;
import dagger.Provides;

@Module
public class NarratorModule {

    private final Application application;

    public NarratorModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    MediaPlayer provideMediaPlayer(ApplicationStateStore applicationStateStore) {
        return MediaPlayer.create(application, applicationStateStore.getAudioFileSelection());
    }
}
