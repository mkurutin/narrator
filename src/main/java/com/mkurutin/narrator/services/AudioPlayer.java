package com.mkurutin.narrator.services;

import android.media.MediaPlayer;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Provider;

public class AudioPlayer {

    private static final String TAG = AudioPlayer.class.getSimpleName();
    private final Provider<MediaPlayer> mediaPlayerProvider;
    private MediaPlayer mediaPlayer;

    @Inject
    public AudioPlayer(Provider<MediaPlayer> mediaPlayerProvider) {
        this.mediaPlayerProvider = mediaPlayerProvider;
    }

    public void init() {
        this.mediaPlayer = mediaPlayerProvider.get();
        Log.i(TAG, "Initialized an instance of MediaPlayer=" + this.mediaPlayer);
    }

    public void startFromBeginning() {
        this.mediaPlayer.pause();
        this.mediaPlayer.seekTo(0);
        this.mediaPlayer.start();
        Log.i(TAG, "Restarted the audio file from beginning");
    }

    public void startFromLastPosition() {
        this.mediaPlayer.start();
        Log.i(TAG, "Started the audio file from last position");
    }

    public void pause() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            Log.i(TAG, "Paused playing the audio file");
        }
    }

    public void shutDown() {
        if (this.mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer = null;
            Log.i(TAG, "Released the instance of MediaPlayer");
        }
    }
}
