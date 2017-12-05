package org.academiadecodigo.bootcamp.Characters;

public class Player implements Characters {
    int hp = 1000;
    int walkspeed;
    boolean dead;
    State state;
    Facing facing;
    Figure figure;// = new Figure(CharacterType.PLAYER);

    //CONSTRUCTOR
    public Player(){
        this.figure = new Figure(CharacterType.PLAYER);
    }


    //METHODS

    @Override
    public void move ( int gameCounter)
    {
        state = State.WALKING;
        figure.animationInit(facing, state);
        figure.animate(gameCounter);
    }

    @Override
    public boolean attack(Characters opponent)
    {
        state = State.ATTACKING;
        return isColliding(opponent);
    }

    @Override
    public void die()
    {
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
    public void animate(int gameCounter,AttackMove attackMove) {
        switch (state){
            case WALKING:
                break;
            case STANDING:
                break;
            case DYING:
                break;
            case ATTACKING:
                //state = State.WALKING;
                figure.animationInit(facing, state,attackMove);
                figure.animate(gameCounter);
                break;
        }
    }

    @Override
    public void setStanding(int gameCounter) {
        state = State.STANDING;
        figure.animationInit(facing, state);
        figure.animate(gameCounter);
    }

    public boolean isColliding(Characters opponent) {
            Figure opponentFrame = opponent.getFigure();

            if (((figure.getX() <= opponentFrame.getMaxX() && figure.getX() >= opponentFrame.getX()) || (figure.getMaxX() <= opponentFrame.getMaxX() && figure.getMaxX() >= opponentFrame.getX()))) {

                return true;
            }

        return false;
    }

    @Override
    public Figure getFigure() {
        return figure;
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
