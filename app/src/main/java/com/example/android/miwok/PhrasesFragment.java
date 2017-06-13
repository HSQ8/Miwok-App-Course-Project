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
public class PhrasesFragment extends Fragment {
    private String activityName = "Phrases";
    public String returnName(){return activityName;}
    AudioManager am;
    MediaPlayer pronounciation;
    MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.sub_activity,container,false);
        final ArrayList<Word> PhrasesList = new ArrayList<Word>();
        PhrasesList.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        PhrasesList.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        PhrasesList.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        PhrasesList.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        PhrasesList.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        PhrasesList.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        PhrasesList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        PhrasesList.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        PhrasesList.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        PhrasesList.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(getActivity(), PhrasesList, R.color.category_phrases);
        ListView numbersView = (ListView) rootview.findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int audioFocusRequestResult = am.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    pronounciation = MediaPlayer.create(getActivity(), PhrasesList.get(i).getAudio());
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
