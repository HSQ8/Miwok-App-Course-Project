package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/***
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumberFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {link NumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberFragment extends Fragment {
    private String activityName = "Numbers";
    public String returnName(){return activityName;}
    private AudioManager am;
    private MediaPlayer pronounciation;

    private MediaPlayer.OnCompletionListener mOncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
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
    public NumberFragment() {
        // Required empty public constructor
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.sub_activity, container, false);

        getActivity().setTitle("Numbers");
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

        WordArrayAdapter numberAdapter = new WordArrayAdapter(getActivity(), numbersList, R.color.category_numbers);
        ListView numbersView = (ListView) rootView.findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);

        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int audioFocusRequestResult = am.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    pronounciation = MediaPlayer.create(getActivity(), numbersList.get(i).getAudio());
                    pronounciation.start();
                    pronounciation.setOnCompletionListener(mOncompletion);
                } else if (audioFocusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {

                }
                // do nothing as of right now
            }
        });
        return rootView;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void releaseMediaPlayer() {
        if (pronounciation != null) {
            pronounciation.release();
            pronounciation = null;
            am.abandonAudioFocus(audioFocusChangeListener);
        }


    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
