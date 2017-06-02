package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Numbers");
        ArrayList<Word> numbersList = new ArrayList<Word>();
        numbersList.add(new Word("one" , "lutti",R.drawable.number_one));
        numbersList.add(new Word("two" , "lutti",R.drawable.number_two));
        numbersList.add(new Word("three" , "lutti",R.drawable.number_three));
        numbersList.add(new Word("four" , "lutti",R.drawable.number_four));
        numbersList.add(new Word("five" , "lutti",R.drawable.number_five));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(this,numbersList,R.color.category_numbers);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);

    }
}


