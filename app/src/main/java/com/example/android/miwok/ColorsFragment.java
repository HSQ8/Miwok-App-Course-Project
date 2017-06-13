package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private String activityName = "Colors";
    public String returnName(){return activityName;}
    AudioManager am;
    MediaPlayer pronounciation;
    MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.sub_activity,container,false);

        final ArrayList<Word> ColorsList = new ArrayList<Word>();
        ColorsList.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        ColorsList.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));
        ColorsList.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        ColorsList.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        ColorsList.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        ColorsList.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        ColorsList.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        ColorsList.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(getActivity(), ColorsList, R.color.category_colors);
        ListView numbersView = (ListView) rootview.findViewById(R.id.list);

        numbersView.setAdapter(numberAdapter);

        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int audioFocusRequestResult = am.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    pronounciation = MediaPlayer.create(getActivity(), ColorsList.get(i).getAudio());
                    pronounciation.start();
                    pronounciation.setOnCompletionListener(mOncompletion);
                } else if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                    // do nothing as of right now
                }

            }
        });

        return rootview;
    }
    private void releaseMediaPlayer() {
        if (pronounciation != null) {
            pronounciation.release();
            pronounciation = null;
            am.abandonAudioFocus(audioFocusChangeListener);}


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
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
