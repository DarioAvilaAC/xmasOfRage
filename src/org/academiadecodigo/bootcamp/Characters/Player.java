package org.academiadecodigo.bootcamp.Characters;

import org.academiadecodigo.bootcamp.Game;

public class Player implements Characters {
    int hp = 1000;
    int walkspeed;
    boolean dead;
    State state;
    Facing facing;
    Figure frame;// = new Figure(CharacterType.PLAYER);

    @Override
    public void move ( int gameCounter,Figure sprite) {
        state = State.WALKING;
        sprite.animationInit(facing, state);
        sprite.animate(gameCounter);
        this.frame=sprite;

    }

    @Override
    public boolean attack(Characters opponent) {
        state = State.ATTACKING;
        return isColliding(opponent);
    }

    @Override
    public void die() {
        dead = true;
        state = State.DYING;
    }

    @Override
    public void loseHP(int damage) {
        hp -= damage;
    }

    @Override
    public void changeFacing(Facing direction){
        this.facing=direction;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void animate(Figure sprite, int gameCounter,AttackMove attackMove) {
        switch (state){
            case WALKING:
                break;
            case STANDING:
                break;
            case DYING:
                break;
            case ATTACKING:
                //state = State.WALKING;
                sprite.animationInit(facing, state,attackMove);
                sprite.animate(gameCounter);
                this.frame=sprite;

                break;
        }
    }

    @Override
    public void setStanding(Figure sprite,int gameCounter) {
        state = State.STANDING;
        sprite.animationInit(facing, state);
        sprite.animate(gameCounter);
    }

    public boolean isColliding(Characters opponent) {
            Figure opponentFrame = opponent.getFrame();

            if (((frame.getX() <= opponentFrame.getMaxX() && frame.getX() >= opponentFrame.getX()) || (frame.getMaxX() <= opponentFrame.getMaxX() && frame.getMaxX() >= opponentFrame.getX()))) {

                return true;
            }

        return false;
    }

    @Override
    public Figure getFrame() {
        return frame;
    }

    @Override
    public int getHp(){
        return this.hp;
    }

    @Override
    public State getState(){
        return state;
    }

    public void setDie(){
        this.dead=false;
    }

    public void setState(){
        this.state=State.ATTACKING;

    }
}
