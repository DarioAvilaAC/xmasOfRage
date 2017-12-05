package org.academiadecodigo.bootcamp.Characters;

public class Enemy implements Characters {
    private int hp;
    private int walkspeed;
    private boolean dead = false;
    private State state = State.STANDING;
    private Facing facing = Facing.LEFT;
    private Figure figure;

    public Enemy(int hp, CharacterType enemy, int walkspeed) {
        this.hp = hp;
        this.walkspeed = walkspeed;
        this.figure = new Figure(enemy);
    }

    @Override
    public void move( int gameCounter) {
        state = State.WALKING;
        figure.animationInit(facing, state);
        figure.animate(gameCounter);
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
        figure.animationInit(facing, state);
    }

    @Override
    public void loseHP(int damage) {
        hp -= damage;
    }

    @Override
    public void changeFacing(Facing direction){
        //Brandao noticed this. It was very helpful and smart
        this.facing=direction;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void animate(int gameCounter,AttackMove attackMove) {
        switch (state) {
            case WALKING:
                figure.animationInit(facing, state);
                figure.animate(gameCounter);
                break;
            case STANDING:
                figure.animationInit(facing, state);
                figure.animate(gameCounter);
                break;
            case DYING:
                figure.animationInit(facing, state);
                figure.animate(gameCounter);
                break;
            case ATTACKING:
                figure.animationInit(facing, state,attackMove);
                figure.animate(gameCounter);
                break;
        }

    }

    @Override
    public void setStanding(int gameCounter) {
        state = State.STANDING;
    }

    @Override
    public boolean isColliding(Characters opponent) {
        if (opponent != null) {
            Figure opponentFrame = opponent.getFigure();

            if ((figure.getX() <= opponentFrame.getMaxX() && figure.getX() >= opponentFrame.getX()) || (figure.getMaxX() <= opponentFrame.getMaxX() && figure.getMaxX() >= opponentFrame.getX())) {

                return true;
            }
        }
        return false;
    }

    @Override
    public Figure getFigure() {
        return figure;
    }

    @Override
    public int getHp(){
        return hp;
    }

    @Override
    public State getState(){
        return state;
    }

}