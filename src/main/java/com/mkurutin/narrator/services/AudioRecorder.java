package com.mkurutin.narrator.services;

import android.media.MediaRecorder;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class AudioRecorder {

    private static final String TAG = AudioPlayer.class.getSimpleName();
    private final ApplicationStateStore applicationStateStore;
    private MediaRecorder mediaRecorder;
    private boolean recordingInProgress;

    @Inject
    public AudioRecorder(ApplicationStateStore applicationStateStore) {
        this.applicationStateStore = applicationStateStore;
    }

    public synchronized void init() {
        this.mediaRecorder = new MediaRecorder();
        this.recordingInProgress = false;
        Log.i(TAG, "Initialized MediaRecorder");

    }

    public synchronized void startRecording() {
        if (!this.recordingInProgress) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(applicationStateStore.getRecordingFileName());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                Log.e(TAG, "Failed to prepare MediaRecorder", e);
            }

            mediaRecorder.start();
            this.recordingInProgress = true;
            Log.i(TAG, "Started recording of the audio");
        }
    }

    public synchronized void finishRecording() {
        if (this.recordingInProgress) {
            this.mediaRecorder.stop();
            this.mediaRecorder.reset();
            this.recordingInProgress = false;
            Log.i(TAG, "Finished recording of the audio");
        }
    }

    public synchronized void shutDown() {
        if (this.mediaRecorder != null) {
            this.mediaRecorder.release();
            this.mediaRecorder = null;
        }
        this.recordingInProgress = false;
        Log.i(TAG, "Shut downz MediaRecorder");
    }
}
