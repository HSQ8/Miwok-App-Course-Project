package com.example.android.miwok;

/**
 * Created by HQ on 5/31/2017.
 */

public class Word {
    private String eng;
    private String miwok;
    private int pic;
    private boolean hasImage = false;
        public Word(String _eng, String _miwok){
        eng = _eng;
        miwok= _miwok;

        }

    public Word(String _eng, String _miwok, int _pic){
        eng = _eng;
        miwok= _miwok;
        pic = _pic;
        hasImage = true;
    }


    public String getDefaultTranslation(){ return eng;}
    public String getMiwokTranslation(){return miwok;}
    public int getPic(){ return pic;}
    public boolean isHasImage(){return hasImage;}


}
