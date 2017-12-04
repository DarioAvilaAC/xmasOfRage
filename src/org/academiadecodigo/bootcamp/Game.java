package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Characters.*;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Music;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements KeyboardHandler {

    //This means that you're at the menu when level =0
    private int level = 0;
    private Level currentLevel;
    private Picture background;
    private Picture background2;
    private Player player;
    private Enemy enemies;
    private int enemiesOnScreen = 0;
    private int distance = 0;
    private Music song;
    private Sound sound;
    private AI ai;
    private Keyboard keyboard = new Keyboard(this);
    private KeyboardEvent controlLeft = new KeyboardEvent();
    private KeyboardEvent controlRight = new KeyboardEvent();
    private KeyboardEvent controlPunch = new KeyboardEvent();
    private KeyboardEvent controlKick = new KeyboardEvent();
    private KeyboardEvent controlLeftRealesed = new KeyboardEvent();
    private KeyboardEvent controlRightRealesed = new KeyboardEvent();
    private KeyboardEvent controlPunchRealesed = new KeyboardEvent();
    private KeyboardEvent controlKickRealesed = new KeyboardEvent();
    private Picture menu;
    private Picture gameOver;
    private Rectangle lifeBar;
    private int frameIndex = 0;
    private Figure sprite;// = new Figure(CharacterType.PLAYER);
    private int gameCounter;


    //Create the menu
    Menu gameMenu = new Menu();

    //Create the upload images
    UploadImages gameImages = new UploadImages();


    public void init() throws InterruptedException{
        gameCounter = 0;

        background = gameImages.loadImages(false);
        background2 = gameImages.loadImages(true);
        player = new Player();
        sprite= new Figure(CharacterType.PLAYER);
        TinySound.init();
        song = TinySound.loadMusic("music/menu.wav");
        sound=TinySound.loadSound("music/titlescreen.wav");
        ai = new AI();

        //Responsible for the screens
        menu = gameMenu.createScreen(true);
        gameMenu.setLevel();
        //gameOver = gameMenu.createScreen(false);





        //Resets the level
        level = 0;
        enemiesOnScreen = 0;
        distance = 0;
        System.out.println(player.isDead());

        keyController();

        start();




    }

    public void start() throws InterruptedException {

        song.play(true);
        while (true) {
            if (gameCounter == 1001) {
                gameCounter = 0;
            }
            // TESTE
            Thread.sleep(80);


            //For the menu
            if (level == 0) {
                gameMenu.keyListener();
                level = gameMenu.getStartLevel();
                Thread.sleep(1000);
                if (level == 1) {
                    song.unload();
                    sound.play();
                    Thread.sleep(4000);
                    background.draw();
                    background2.draw();


                    song = TinySound.loadMusic("music/level.wav");
                    song.play(true);
                    currentLevel = new Level();

                    //player stats
                    sprite.load("characters/player/right/standing/0.png");
                    sprite.draw();
                    player.changeFacing(Characters.Facing.RIGHT);
                    player.setStanding(sprite, gameCounter);


                    //sprite.animationInit(Characters.Facing.RIGHT, Characters.State.STANDING);


                }
            }

            if (level == 1) {


                //To see of the player is standing
                if (player.getState() == Characters.State.STANDING) {
                    if (this.gameCounter == 201) {
                        gameCounter = 0;

                    }

                    player.setStanding(sprite, gameCounter);
                    gameCounter++;


                }

                //Checks if the player is dead and opens the game over screen
                if (player.isDead()) {
                    sound=TinySound.loadSound("music/maledeath.wav");
                    sound.play();
                    song.stop();
                    Thread.sleep(500);
                    gameMenu.createScreen(false);
                    //gameOver.draw();
                    Thread.sleep(5000);
                    //clear();
                    break;

                }

                //Only calls this method when there is an enemy on the field
                if (enemiesOnScreen == 1) {
                    switch(ai.pickAction(player, enemies)){
                        case ATTACKING:
                            //System.out.println("ATTACK");
                            enemies.attack(player);
                            sound=TinySound.loadSound("music/punch.wav");
                            sound.play();
                            enemies.animate(enemies.getFrame(),gameCounter,AttackMove.ATTACK);
                            break;
                        case WALKING:
                            //System.out.println("WALKING");
                            enemies.move(gameCounter,enemies.getFrame());
                            break;
                    }
                    //player.getHp();
                    //enemies.getHp();

                    //Changes the life bar after damage


                    //For when the enemy dies
                    if (enemies.getHp() <= 0) {

                        sound=TinySound.loadSound("music/playerpain2.wav");
                        sound.play();
                        enemies.die();
                        //enemiesOnScreen = 0;

                        if (gameCounter > 1000){
                            enemies.getFrame().loadFrame(1);
                        } else if (gameCounter > 500){
                            enemies.getFrame().loadFrame(0);
                        }

                        enemies.getFrame().delete();
                        distance++;
                        enemiesOnScreen = 0;
                    }

                    //For when the player dies
                    if (player.getHp() <= 0) {
                        player.die();


                    }

                }


                //player.animate();
                //enemies.animate();


                switch (distance) {
                    case 0:
                        battleArea();
                        break;
                    case 25:
                        battleArea();
                        break;
                    case 50:
                        battleArea();
                        break;
                    case 75:
                        battleArea();
                        break;
                    case 100:
                        battleArea();
                        break;
                    case 150:
                        battleArea();
                        break;
                    case 250:
                        bossBattle();
                        break;

                    case 275:
                        level = 2;
                        break;
                }


            }

            if (level == 2) {
                song.stop();
                gameImages.showCredits().draw();
                Thread.sleep(5000);
                break;

            }



            gameCounter++;

        }

        //When the player loses the game
        song.stop();
        song.unload();
        ai=null;
        enemies=null;
        player.getFrame().delete();
        player=null;

        init();

    }

    private void bossBattle() {
        song.stop();
        sound=TinySound.loadSound("music/santalaugh.wav");
        sound.play();
        song.unload();
        song = TinySound.loadMusic("music/boss.wav");
        song.play(true);
        if (sprite.getMaxX() >= 400 && enemiesOnScreen == 0) {
            //Create a new enemy
            Enemy enemy = EnemyFactory.createEnemy(true);
            enemies = enemy;
            enemy.getFrame().draw();

            enemiesOnScreen = 1;
        }
        if (enemies == null || enemies.isDead()) {
            sound=TinySound.loadSound("music/santadeath.wav");
            sound.play();
            enemiesOnScreen = 0;
            distance++;
        }
    }


    private void battleArea() throws InterruptedException {
        if (sprite.getMaxX() >= 400 && enemiesOnScreen == 0) {
            //Create a new enemy
            Enemy enemy = EnemyFactory.createEnemy(false);
            enemies = enemy;
            enemy.getFrame().draw();
            enemiesOnScreen = 1;
        }
        if (enemiesOnScreen == 1) {

            if (enemies.isDead()) {
                enemiesOnScreen = 0;
                distance ++;
                //Method to roll the screen after defeat the enemies

            }
        }

    }

    public void keyController()  {


        controlRight.setKey(KeyboardEvent.KEY_RIGHT);
        controlLeft.setKey(KeyboardEvent.KEY_LEFT);
        controlPunch.setKey(KeyboardEvent.KEY_C);//Punch
        controlKick.setKey(KeyboardEvent.KEY_X);//Kick

        controlRightRealesed.setKey(KeyboardEvent.KEY_RIGHT);
        controlLeftRealesed.setKey(KeyboardEvent.KEY_LEFT);
        controlPunchRealesed.setKey(KeyboardEvent.KEY_C);//Punch
        controlKickRealesed.setKey(KeyboardEvent.KEY_X);//Kick

        controlLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        controlRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        controlPunch.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        controlKick.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        controlLeftRealesed.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        controlRightRealesed.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        controlPunchRealesed.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        controlKickRealesed.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(controlLeft);
        keyboard.addEventListener(controlRight);
        keyboard.addEventListener(controlPunch);
        keyboard.addEventListener(controlKick);
        keyboard.addEventListener(controlLeftRealesed);
        keyboard.addEventListener(controlRightRealesed);
        keyboard.addEventListener(controlPunchRealesed);
        keyboard.addEventListener(controlKickRealesed);




        /*
        control.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        control.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(control);
        */

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.changeFacing(Characters.Facing.RIGHT);
                if (gameCounter == 201) {
                    gameCounter = 0;
                }


                player.move(this.gameCounter, sprite);
                gameCounter++;

                //Scroll background
                if(enemiesOnScreen==0 && sprite.getMaxX()>=500){
                    if (background.getMaxX() > 500 || background2.getMaxX() > 500) {
                        //Method to roll the screen in loop, if it reached the limits

                        if (!currentLevel.imageRoll(background, background2)) {
                            background.translate(-10, 0);
                            background2.translate(-10, 0);

                        } else {
                            player.getFrame().translate(-20, 0);
                        }
                        //Thread.sleep(100);
                    }
                    distance++;
                }

                //Call the method in player
                break;
            case KeyboardEvent.KEY_LEFT:

                player.changeFacing(Characters.Facing.LEFT);
                if (gameCounter == 201) {
                    gameCounter = 0;
                }


                player.move(this.gameCounter, sprite);
                gameCounter++;

                //Call the method in player
                break;
            case KeyboardEvent.KEY_C: //Punch
                player.setState();
                if (gameCounter == 201) {
                    gameCounter = 0;
                }
                player.animate(sprite,gameCounter,AttackMove.PUNCH);
                if(enemiesOnScreen==1){
                    if (player.attack(enemies)) {
                        sound=TinySound.loadSound("music/punch.wav");
                        sound.play();
                        int damage = (int) Math.round(Math.random() * 40) + 20;
                        enemies.loseHP(damage);
                    }


                }

                //Call the method in player
                break;
            case KeyboardEvent.KEY_X: //Kick
                player.setState();
                if (gameCounter == 201) {
                    gameCounter = 0;
                }
                player.animate(sprite,gameCounter,AttackMove.KICK);
                if(enemiesOnScreen==1){
                    if (player.attack(enemies)) {
                        int damage = (int) Math.round(Math.random() * 40) + 20;
                        enemies.loseHP(damage);
                    }


                }
                //Call method in player
                break;


        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.setStanding(sprite, gameCounter);

                break;
            case KeyboardEvent.KEY_LEFT:

                player.setStanding(sprite, gameCounter);
                break;
            case KeyboardEvent.KEY_C:
                player.setStanding(sprite, gameCounter);
                break;
            case KeyboardEvent.KEY_X:
                player.setStanding(sprite, gameCounter);

                break;


        }

    }

    public void clear(){
        level=0;
        enemiesOnScreen=0;
        enemies.getFrame().delete();


    }
}

