package org.academiadecodigo.bootcamp.Characters;

public interface Characters {

    void move( int gameCounter, Figure sprite);
    boolean attack(Characters opponent);
    void die();
    void loseHP(int damage);
    boolean isDead();
    void animate(Figure sprite, int gameCounter,AttackMove attackMove);
    void setStanding(Figure sprite, int gameCounter);
    boolean isColliding(Characters opponent);
    Figure getFrame();
    void changeFacing(Facing direction);
    int getHp();
    State getState();


    enum Facing {
        RIGHT,
        LEFT;
    }

    enum State{
        STANDING,
        WALKING,
        ATTACKING,
        DYING;
    }
}


