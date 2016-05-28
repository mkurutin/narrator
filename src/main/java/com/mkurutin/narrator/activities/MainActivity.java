package com.mkurutin.narrator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.github.developerpaul123.filepickerlibrary.FilePickerActivity;
import com.github.developerpaul123.filepickerlibrary.enums.Request;
import com.github.developerpaul123.filepickerlibrary.enums.Scope;
import com.mkurutin.narrator.R;
import com.mkurutin.narrator.config.NarratorApplication;
import com.mkurutin.narrator.services.ApplicationStateStore;
import com.mkurutin.narrator.services.AudioPlayer;
import com.mkurutin.narrator.services.AudioRecorder;

import javax.inject.Inject;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Inject
    AudioPlayer audioPlayer;
    @Inject
    AudioRecorder audioRecorder;
    @Inject
    ApplicationStateStore applicationStateStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(myToolbar);
        ((NarratorApplication) this.getApplicationContext()).getNarratorComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        audioRecorder.init();
    }

    public void playAudio(View button) {
        audioPlayer.startFromLastPosition();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FilePickerActivity.REQUEST_FILE && resultCode == RESULT_OK) {
            String selectedFileName = data.getStringExtra(FilePickerActivity.FILE_EXTRA_DATA_PATH);
            applicationStateStore.setSelectedFileName(selectedFileName);
        }
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
        audioRecorder.shutDown();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_select_file) {
            Intent filePickerIntent = new Intent(this, FilePickerActivity.class);
            filePickerIntent.putExtra(FilePickerActivity.SCOPE, Scope.ALL);
            filePickerIntent.putExtra(FilePickerActivity.REQUEST, Request.FILE);
            startActivityForResult(filePickerIntent, FilePickerActivity.REQUEST_FILE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}