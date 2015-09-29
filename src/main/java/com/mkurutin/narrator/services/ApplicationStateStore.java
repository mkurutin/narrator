package com.mkurutin.narrator.services;

import com.mkurutin.narrator.R;

import javax.inject.Inject;

public class ApplicationStateStore {

    @Inject
    public ApplicationStateStore() {
    }

    private int audioFileSelection = R.raw.katiusha;

    public int getAudioFileSelection() {
        return audioFileSelection;
    }
}
