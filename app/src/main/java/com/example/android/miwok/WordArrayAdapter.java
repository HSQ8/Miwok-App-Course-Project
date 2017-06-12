package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HQ on 5/31/2017.
 */

public class WordArrayAdapter extends ArrayAdapter<Word> {
    private int colorid;

    public WordArrayAdapter(Context context, ArrayList<Word> words, int _colorid) {
        super(context, 0, words);
        colorid = _colorid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word word = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitemlayout, parent, false);
        }

        ImageView illustration = (ImageView) convertView.findViewById(R.id.wordImage);
        final MediaPlayer pronounciation;
        if (word.isHasImage()) {
            illustration.setImageResource(word.getPic());
        }
        ImageView playbutton = (ImageView) convertView.findViewById(R.id.playericon);
        playbutton.setImageResource(R.drawable.ic_play_arrow_white_24dp);

        TextView english = (TextView) convertView.findViewById(R.id.englishview);
        TextView miwok = (TextView) convertView.findViewById(R.id.miwokview);
        english.setBackgroundColor(ContextCompat.getColor(getContext(), colorid));
        miwok.setBackgroundColor(ContextCompat.getColor(getContext(), colorid));
        LinearLayout imagewrapper = (LinearLayout) convertView.findViewById(R.id.playericonwrap);
        imagewrapper.setBackgroundColor(ContextCompat.getColor(getContext(),colorid));
        english.setText(word.getDefaultTranslation());
        miwok.setText(word.getMiwokTranslation());


        return convertView;

    }


}
