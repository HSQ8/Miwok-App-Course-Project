package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMemberActivity extends AppCompatActivity {
    AudioManager am;
    MediaPlayer pronounciation;
    MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Family");
        final ArrayList<Word> FamilyList = new ArrayList<Word>();
        FamilyList.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        FamilyList.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        FamilyList.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        FamilyList.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        FamilyList.add(new Word("older brother", "taachi", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        FamilyList.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        FamilyList.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        FamilyList.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        FamilyList.add(new Word("grandmother ", "ama", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        FamilyList.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,
                R.raw.family_grandfather));


        WordArrayAdapter numberAdapter = new WordArrayAdapter(this, FamilyList, R.color.category_family);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int audioFocusRequestResult = am.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    pronounciation = MediaPlayer.create(FamilyMemberActivity.this, FamilyList.get(i).getAudio());
                    pronounciation.start();
                    pronounciation.setOnCompletionListener(mOncompletion);
                } else if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                    // do nothing as of right now
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        if (pronounciation != null) {
            pronounciation.release();
            pronounciation = null;
            am.abandonAudioFocus(audioFocusChangeListener);}


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {

            switch (i) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    Log.v("media player", "audio focus lost");
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    pronounciation.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    pronounciation.pause();
                    pronounciation.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    pronounciation.pause();
                    pronounciation.seekTo(0);
                    break;

            }
        }
    };
}
