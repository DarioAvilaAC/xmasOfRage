package org.academiadecodigo.bootcamp.Characters;
//import org.academiadecodigo.bootcamp.Characters.Characters;


import java.io.File;

public enum CharacterType {

    SHIVA(810, 430, "shiva/", Characters.State.STANDING, Characters.Facing.LEFT, 15, 2, 4, 4, 1),
    ZAN(810, 450, "zan/", Characters.State.STANDING, Characters.Facing.LEFT, 20, 2, 4, 2, 1),
    SANTA(810, 450, "santa/", Characters.State.STANDING, Characters.Facing.LEFT, 12, 3, 1, 2, 1),
    PLAYER(10, 430, "player/", Characters.State.STANDING, Characters.Facing.RIGHT, 15, 2, 5, 6, 2);


    private String generalCharactersDir = "characters/";
    private int xInitialPosition;
    private int yInitialPosition;
    private String characterDir;
    private Characters.State initialState;
    private Characters.Facing initialFacing;
    private AttackMove initialAttackMove;
    private int moveSpan;
    private int standingFrames;
    private int walkingFrames;
    private int attackingFrames;
    private int dyingFrames;

    //CONSTRUCTOR
    CharacterType(int xInitialPosition, int yInitialPosition, String characterDir, Characters.State initialState, Characters.Facing initialFacing, int moveSpan, int s, int w, int a, int d) {
        this.xInitialPosition = xInitialPosition;
        this.yInitialPosition = yInitialPosition;
        this.characterDir = characterDir;
        this.initialState = initialState;
        this.initialFacing = initialFacing;
        this.initialAttackMove = AttackMove.NONE;
        this.moveSpan = moveSpan;
        this.standingFrames = s;
        this.walkingFrames = w;
        this.attackingFrames = a;
        this.dyingFrames = d;
    }


    //METHODS

    public String frameFileName(int frame, Characters.State state, Characters.Facing facing, AttackMove attackMove)
    {
        String attackMoveDir = "";
        if (state == Characters.State.ATTACKING){
            switch (attackMove){
                case PUNCH:
                    attackMoveDir += "p";
                    break;
                case KICK:
                    attackMoveDir += "k";
                    break;
                default:
                    break;
            }
        }
        return this.generalCharactersDir + this.characterDir + this.facingDir(facing) + this.stateDir(state) + attackMoveDir + frame + ".png";
    }

    public int maxFrameIndex(Characters.State state, Characters.Facing facing, AttackMove attackMove, CharacterType characterType) {

        switch (state){
            case STANDING:
                        return this.standingFrames;
            case WALKING:
                        return this.walkingFrames;
            case ATTACKING:
                        if(characterType == CharacterType.PLAYER){
                            switch(attackMove){
                                case PUNCH:
                                    return 2;
                                case KICK:
                                    return 3;
                            }
                        } else {
                            return this.attackingFrames;
                        }
            case DYING:
                        return this.dyingFrames;
        }
        return 0;
    }

    private String stateDir(Characters.State state)
    {
        switch (state){
            case STANDING:
                return "standing/";
            case WALKING:
                return "walking/";
            case ATTACKING:
                return "attacking/";
            case DYING:
                return "dying/";
        }
        return null;
    }

    private String facingDir(Characters.Facing facing)
    {
        switch (facing){
            case RIGHT:
                return "right/";
            case LEFT:
                return "left/";
        }
        return null;
    }




    //GETTERS

    public int getXinitialPosition(){
        return this.xInitialPosition;
    }

    public int getYinitialPosition(){
        return this.yInitialPosition;
    }

    public Characters.State getInitialState() {
        return this.initialState;
    }

    public Characters.Facing getInitialFacing() {
        return this.initialFacing;
    }

    public AttackMove getInitialAttackMove(){
        return this.initialAttackMove;
    }

    public int getMoveSpan(){ return this.moveSpan; }

}
