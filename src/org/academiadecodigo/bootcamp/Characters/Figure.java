package org.academiadecodigo.bootcamp.Characters;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Figure extends Picture {

    private CharacterType character;
    private Characters.Facing currentFacing;
    private Characters.State currentState;
    private AttackMove attackMove;
    private int currentFrame;


    //CONSTRUCTOR
    public Figure(CharacterType character){
        super(character.getXinitialPosition(), character.getYinitialPosition(), character.frameFileName(0, character.getInitialState(), character.getInitialFacing(), character.getInitialAttackMove()));
        this.character = character;
        this.currentState = character.getInitialState();
        this.currentFacing = character.getInitialFacing();
        this.attackMove = AttackMove.NONE;
        this.currentFrame = 0;
    }


    //METHODS


    public void animationInit(Characters.Facing direction, Characters.State state)
    {

        this.currentState = state;
        this.currentFacing = direction;
    }

    public void animationInit(Characters.Facing direction, Characters.State state, AttackMove attackMove)
    {

        this.currentState = state;
        this.currentFacing = direction;
        if (character != CharacterType.PLAYER){
            this.attackMove = AttackMove.ATTACK;
        } else {
            this.attackMove = attackMove;
        }
    }

    public void animate(int gameCounter){
        //this.move(currentFacing);
        //loadFrame(currentFrame);
        if (gameCounter < 200){
            if (currentFrame >= this.maxFrameIndex()){
                this.currentFrame = -1;
            }
            if (this.currentState == Characters.State.WALKING) {
                this.move(this.currentFacing);
            }
            this.currentFrame++;
            loadFrame(this.currentFrame);
        }
    }

    public void loadFrame(int i)
    {
        this.load(character.frameFileName(i, this.currentState, this.currentFacing, this.attackMove));
        //this.draw();
    }

    public int maxFrameIndex()
    {
        return character.maxFrameIndex(this.currentState, this.currentFacing, this.attackMove, this.character);
    }

    private void move(Characters.Facing direction)
    {
        switch (direction){
            case RIGHT:
                if (this.getMaxX() < 795){
                    this.translate(character.getMoveSpan(), 0);
                }
                break;
            case LEFT:
                if (this.getX() > 5){
                    this.translate(-character.getMoveSpan(), 0);
                }
                break;
        }
    }

    //GETTERS
    public int getCurrentFrame(){
        return this.currentFrame;
    }

}


