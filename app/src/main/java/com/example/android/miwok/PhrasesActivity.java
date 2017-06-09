package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer pronounciation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);
        setTitle("Phrases");
        final ArrayList<Word> PhrasesList = new ArrayList<Word>();
        PhrasesList.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        PhrasesList.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        PhrasesList.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        PhrasesList.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        PhrasesList.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        PhrasesList.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        PhrasesList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        PhrasesList.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        PhrasesList.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        PhrasesList.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordArrayAdapter numberAdapter = new WordArrayAdapter(this, PhrasesList, R.color.category_phrases);
        ListView numbersView = (ListView) findViewById(R.id.list);
        numbersView.setAdapter(numberAdapter);
        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pronounciation = MediaPlayer.create(PhrasesActivity.this, PhrasesList.get(i).getAudio());
                pronounciation.start();
            }
        });
    }
}
