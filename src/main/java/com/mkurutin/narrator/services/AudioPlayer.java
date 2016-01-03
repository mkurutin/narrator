package com.mkurutin.narrator.services;

import android.media.MediaPlayer;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class AudioPlayer {

    private static final String TAG = AudioPlayer.class.getSimpleName();
    private ApplicationStateStore applicationStateStore;
    private MediaPlayer mediaPlayer;

    @Inject
    public AudioPlayer(ApplicationStateStore applicationStateStore) {
        this.applicationStateStore = applicationStateStore;
    }

    public synchronized void startFromBeginning() {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = createMediaPlayer();
        }
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
        Log.i(TAG, "Restarted the audio file from beginning");
    }

    public synchronized void startFromLastPosition() {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = createMediaPlayer();
            mediaPlayer.start();
        }
        Log.i(TAG, "Started the audio file from last position");
    }

    public synchronized void pause() {
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
            mediaPlayer.release();
            this.mediaPlayer = null;
            Log.i(TAG, "Killed MediaPlayer");
        }
    }

    private MediaPlayer createMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(applicationStateStore.getSelectedFileName());
            mediaPlayer.prepare();
        } catch (IOException ex) {
            Log.e(TAG, "Failed to prepare MediaPlayer:", ex);
        }
        return mediaPlayer;
    }
}
