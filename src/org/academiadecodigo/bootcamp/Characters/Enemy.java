package org.academiadecodigo.bootcamp.Characters;

public class Enemy implements Characters {
    private int hp;
    private int walkspeed;
    private boolean dead = false;
    private State state = State.STANDING;
    private Facing facing = Facing.LEFT;
    private Figure frame;

    public Enemy(int hp, CharacterType enemy, int walkspeed) {
        this.hp = hp;
        this.walkspeed = walkspeed;

        this.frame = new Figure(enemy);
    }

    @Override
    public void move( int gameCounter, Figure sprite) {
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
        frame.animationInit(facing, state);
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
    public void animate(Figure sprite, int gameCounter,AttackMove attackMove) {
        switch (state) {
            case WALKING:
                break;
            case STANDING:
                break;
            case DYING:
                sprite.animationInit(facing, state);
                sprite.animate(gameCounter);
                break;
            case ATTACKING:
                sprite.animationInit(facing, state,attackMove);
                sprite.animate(gameCounter);
                this.frame=sprite;
                break;
        }

    }

    @Override
    public void setStanding(Figure sprite, int gameCounter) {
        state = State.STANDING;
    }

    @Override
    public boolean isColliding(Characters opponent) {
        if (opponent != null) {
            Figure opponentFrame = opponent.getFrame();

            if ((frame.getX() <= opponentFrame.getMaxX() && frame.getX() >= opponentFrame.getX()) || (frame.getMaxX() <= opponentFrame.getMaxX() && frame.getMaxX() >= opponentFrame.getX())) {

                return true;
            }
        }
        return false;
    }

    @Override
    public Figure getFrame() {
        return frame;
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