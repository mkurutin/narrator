package com.mkurutin.narrator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.mkurutin.narrator.R;
import com.mkurutin.narrator.config.NarratorApplication;
import com.mkurutin.narrator.services.AudioPlayer;
import com.mkurutin.narrator.services.AudioRecorder;

import javax.inject.Inject;
import java.io.IOException;

public class MainActivity extends Activity {

    @Inject
    AudioPlayer audioPlayer;
    @Inject
    AudioRecorder audioRecorder;

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
        audioRecorder.init();
    }

    public void playAudio(View button) {
        audioPlayer.startFromLastPosition();
    }

    public void pauseAudio(View button) {
        audioPlayer.pause();
    }

    public void replayAudio(View view) throws IOException {
        audioPlayer.startFromBeginning();
    }

    public void startRecording(View view) {
        audioRecorder.startRecording();
    }

    public void playBackRecording(View view) {
        audioRecorder.finishRecording();
    }

    @Override
    protected void onStop() {
        audioPlayer.shutDown();
        audioRecorder.shutDown();
        super.onStop();
    }
}