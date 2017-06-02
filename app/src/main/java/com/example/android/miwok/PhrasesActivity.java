package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Phrases");
        ArrayList<Word> numbersList = new ArrayList<Word>();
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("two" , "lutti"));
        numbersList.add(new Word("three" , "lutti"));
        numbersList.add(new Word("four" , "lutti"));
        numbersList.add(new Word("five" , "lutti"));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(this,numbersList,R.color.category_phrases);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
    }
}
