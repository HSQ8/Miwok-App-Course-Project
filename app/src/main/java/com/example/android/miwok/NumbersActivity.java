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

public class NumbersActivity extends AppCompatActivity {
    AudioManager am;
    MediaPlayer pronounciation;
    MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Numbers");
        final ArrayList<Word> numbersList = new ArrayList<Word>();
        numbersList.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numbersList.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbersList.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbersList.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbersList.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbersList.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbersList.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbersList.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbersList.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        numbersList.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(this, numbersList, R.color.category_numbers);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);

        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int audioFocusRequestResult = am.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    pronounciation = MediaPlayer.create(NumbersActivity.this, numbersList.get(i).getAudio());
                    pronounciation.start();
                    pronounciation.setOnCompletionListener(mOncompletion);
                } else if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {

                }
                // do nothing as of right now
            }
        });

    }

    private void releaseMediaPlayer() {
        if (pronounciation != null) {
            pronounciation.release();
            pronounciation = null;
            am.abandonAudioFocus(audioFocusChangeListener);
        }


    }

    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


}


