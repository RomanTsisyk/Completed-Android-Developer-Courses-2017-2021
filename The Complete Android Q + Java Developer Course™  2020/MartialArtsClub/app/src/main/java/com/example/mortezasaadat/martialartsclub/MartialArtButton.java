package com.example.mortezasaadat.martialartsclub;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.example.mortezasaadat.martialartsclub.Model.MartialArt;

/**
 * Created by mortezasaadat on 5/25/17.
 */

public class MartialArtButton extends AppCompatButton {


    private MartialArt martialArtObject;

    public MartialArtButton(Context context, MartialArt martialArt) {

        super(context);

        martialArtObject = martialArt;

    }

    public String getMartialArtColor() {


        return martialArtObject.getMartialArtColor();

    }

    public double getMartialArtPrice() {


        return martialArtObject.getMartialArtPrice();

    }



}
