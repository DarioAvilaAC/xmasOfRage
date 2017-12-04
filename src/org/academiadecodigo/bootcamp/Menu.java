package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements  KeyboardHandler{

    private Picture menu;
    private int startLevel=0;



    //Calls the menu and create it in the constructor
    public Picture createScreen (boolean isMenu){
        if (isMenu){
            this.menu=new Picture(0,0,"background/menugame.png");
            menu.draw();

        }else{
            this.menu=new Picture(0,0,"background/gameover.png");
            menu.draw();

        }
        return null;

    }




    //The key listner for the menu
    //Method to listen the keyboard
    public void keyListener() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent menu = new KeyboardEvent();

        menu.setKey(KeyboardEvent.KEY_SPACE);
        menu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(menu);




    }


    //GETTER
    //To get the end of the menu
    public int getStartLevel(){
        return this.startLevel;
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        if(startLevel==0){
            switch(keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_SPACE:
                    Rectangle fade=new Rectangle(0,0,800,600);
                    fade.draw();

                    fade.setColor(Color.DARK_GRAY);
                    fade.fill();
                    this.startLevel=1;
                    break;
            }
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent){

    }


    public void setLevel() {
        this.startLevel = 0;
    }
}

