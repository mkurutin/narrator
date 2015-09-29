package com.mkurutin.narrator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.mkurutin.narrator.R;
import com.mkurutin.narrator.config.NarratorApplication;
import com.mkurutin.narrator.services.AudioPlayer;

import javax.inject.Inject;
import java.io.IOException;

public class MainActivity extends Activity {

    @Inject
    AudioPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((NarratorApplication) this.getApplicationContext()).getNarratorComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        audioPlayer.init();
    }

    public void playAudio(View button) {
        audioPlayer.startFromLastPosition();
    }

    public void stopAudio(View button) {
        audioPlayer.pause();
    }

    public void resetAudio(View view) throws IOException {
        audioPlayer.startFromBeginning();
    }

    @Override
    protected void onStop() {
        audioPlayer.shutDown();
        super.onStop();
    }
}