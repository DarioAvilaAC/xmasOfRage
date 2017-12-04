package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Level {

    public boolean imageRoll(Picture img1, Picture img2){
        if(img1.getX() < 810 && img1.getMaxX() > 0){
            if(img2.getMaxX() <= 0){img2.translate(img1.getWidth()*2 - 10,0);}
            img1.translate(-10,0);
            img2.translate(-10,0);
            return true;
        }else if(img2.getX() < 810 && img2.getMaxX() > 0){
            if(img1.getMaxX() <= 0){img1.translate(img1.getWidth()*2 - 10,0);}
            img2.translate(-10,0);
            img1.translate(-10,0);
            return true;
        }
        return false;
    }


}
