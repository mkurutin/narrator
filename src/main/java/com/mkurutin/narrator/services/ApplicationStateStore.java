package com.mkurutin.narrator.services;

import android.os.Environment;
import com.mkurutin.narrator.R;

import javax.inject.Inject;

public class ApplicationStateStore {

    private String recordingFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/speech_recording.3gp";

    private int audioFileSelection = R.raw.katiusha;

    @Inject
    public ApplicationStateStore() {
    }

    public int getAudioFileSelection() {
        return audioFileSelection;
    }

    public String getRecordingFileName() {
        return recordingFileName;
    }

}
