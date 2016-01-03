package com.mkurutin.narrator.services;

import android.os.Environment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationStateStore {

    private String recordingFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/speech_recording.3gp";

    private String selectedFileName;

    @Inject
    public ApplicationStateStore() {
    }

    public String getRecordingFileName() {
        return recordingFileName;
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }

    public void setSelectedFileName(String selectedFileName) {
        this.selectedFileName = selectedFileName;
    }

    public boolean isFileSelected() {
        return this.selectedFileName != null;
    }
}
