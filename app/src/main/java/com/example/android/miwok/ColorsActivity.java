package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Colors");
        ArrayList<Word> numbersList = new ArrayList<Word>();
        numbersList.add(new Word("one" , "lutti",R.drawable.color_black));
        numbersList.add(new Word("two" , "lutti",R.drawable.color_brown));
        numbersList.add(new Word("three" , "lutti",R.drawable.color_dusty_yellow));
        numbersList.add(new Word("four" , "lutti",R.drawable.color_gray));
        numbersList.add(new Word("five" , "lutti",R.drawable.color_green));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(this,numbersList,R.color.category_colors);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
    }
}
