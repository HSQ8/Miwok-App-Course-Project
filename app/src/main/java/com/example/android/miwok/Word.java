package com.example.android.miwok;

/**
 * Created by HQ on 5/31/2017.
 */

public class Word {
    private String eng;
    private String miwok;

    public Word(String _eng, String _miwok){
        eng = _eng;
        miwok= _miwok;
            }
            @Override
    public String toString(){
                return eng + "\n" + miwok;

            }
    public String getDefaultTranslation(){ return eng;  }
    public String getMiwokTranslation(){return miwok;}

}
