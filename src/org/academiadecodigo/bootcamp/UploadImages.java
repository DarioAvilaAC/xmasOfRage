package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Characters.Characters;
import org.academiadecodigo.bootcamp.Characters.Player;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


import java.io.File;

public class UploadImages {

    private Text playerName;
    private Text playHp;

    //Load the background images
    public Picture loadImages(boolean isSecondBG){
        if(!isSecondBG) {
            return new Picture(0, 0, "background/background.gif");
        }else{
            return new Picture(1078,0,"background/background.gif");
        }
    }





    public Picture showCredits() {
        return new Picture(0, 0, "background/credits.png");
    }


}