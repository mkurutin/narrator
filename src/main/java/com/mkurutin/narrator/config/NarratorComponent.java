package com.mkurutin.narrator.config;

import com.mkurutin.narrator.activities.MainActivity;
import dagger.Component;

@Component(modules = NarratorModule.class)
public interface NarratorComponent {
    void inject(MainActivity mainActivity);
}
