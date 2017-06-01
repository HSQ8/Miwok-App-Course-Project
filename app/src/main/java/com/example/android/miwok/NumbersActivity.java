package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        //setTitle("Numbers");
        ArrayList<Word> numbersList = new ArrayList<Word>();
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));
        numbersList.add(new Word("one" , "lutti"));


        WordArrayAdapter numberAdapter = new WordArrayAdapter(this,numbersList);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);






    }
}


