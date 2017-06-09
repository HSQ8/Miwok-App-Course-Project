package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer pronounciation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Colors");
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

        WordArrayAdapter numberAdapter = new WordArrayAdapter(this, ColorsList, R.color.category_colors);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pronounciation = MediaPlayer.create(ColorsActivity.this, ColorsList.get(i).getAudio());
                pronounciation.start();
            }
        });
    }
}
