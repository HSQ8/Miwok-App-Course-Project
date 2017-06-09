package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer pronounciation;

    @Override
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
                pronounciation = MediaPlayer.create(NumbersActivity.this, numbersList.get(i).getAudio());
                pronounciation.start();
            }
        });

    }
}


