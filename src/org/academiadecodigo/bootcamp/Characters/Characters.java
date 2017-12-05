package org.academiadecodigo.bootcamp.Characters;

public interface Characters {

    void move( int gameCounter);
    boolean attack(Characters opponent);
    void die();
    void loseHP(int damage);
    boolean isDead();
    void animate(int gameCounter,AttackMove attackMove);
    void setStanding(int gameCounter);
    boolean isColliding(Characters opponent);
    Figure getFigure();
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


