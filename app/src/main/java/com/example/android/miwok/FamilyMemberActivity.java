package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class FamilyMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Family");
        ArrayList<Word> numbersList = new ArrayList<Word>();
        numbersList.add(new Word("one" , "lutti",R.drawable.family_daughter,R.raw.family_daughter));
        numbersList.add(new Word("two" , "lutti",R.drawable.family_father));
        numbersList.add(new Word("three" , "lutti",R.drawable.family_grandfather));
        numbersList.add(new Word("four" , "lutti",R.drawable.family_mother));
        numbersList.add(new Word("five" , "lutti",R.drawable.family_older_sister));


        WordArrayAdapter numberAdapter = new WordArrayAdapter(this,numbersList,R.color.category_family);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
    }
}
