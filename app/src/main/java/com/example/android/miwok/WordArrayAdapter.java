package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HQ on 5/31/2017.
 */

public class WordArrayAdapter extends ArrayAdapter<Word>{
    public WordArrayAdapter(Context context, ArrayList<Word> words){
        super(context,0,words);
        }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
Word word = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitemlayout,parent,false);
        }
        TextView english = (TextView) convertView.findViewById(R.id.englishview);
        TextView miwok = (TextView) convertView.findViewById(R.id.miwokview);

        english.setText(word.getDefaultTranslation());
        miwok.setText(word.getMiwokTranslation());

        return convertView;

    }


}
