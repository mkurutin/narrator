package com.mkurutin.narrator.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.mkurutin.narrator.R;
import com.mkurutin.narrator.config.NarratorApplication;
import com.mkurutin.narrator.services.AudioPlayer;
import com.mkurutin.narrator.services.AudioRecorder;

import javax.inject.Inject;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Inject
    AudioPlayer audioPlayer;
    @Inject
    AudioRecorder audioRecorder;

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
        audioPlayer.init();
        audioRecorder.init();
    }

    public void playAudio(View button) {
//        Intent filePickerIntent = new Intent(this, FilePickerActivity.class);
//        filePickerIntent.putExtra(FilePickerActivity.SCOPE_TYPE, FileScopeType.ALL);
//        filePickerIntent.putExtra(FilePickerActivity.REQUEST_CODE, FilePickerActivity.REQUEST_FILE);
//        startActivityForResult(filePickerIntent, FilePickerActivity.REQUEST_FILE);
        audioPlayer.startFromLastPosition();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        System.out.println("requestCode=" + requestCode);
//        System.out.println("resultCode=" + resultCode);
//        System.out.println("data=" + data.getStringExtra(FilePickerActivity.FILE_EXTRA_DATA_PATH));
//    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }
}