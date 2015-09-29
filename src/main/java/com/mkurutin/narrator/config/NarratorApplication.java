package com.mkurutin.narrator.config;

import android.app.Application;

public class NarratorApplication extends Application {

    private NarratorComponent narratorComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.narratorComponent = DaggerNarratorComponent.builder().narratorModule(new NarratorModule(this)).build();
    }

    public NarratorComponent getNarratorComponent() {
        return narratorComponent;
    }
}
