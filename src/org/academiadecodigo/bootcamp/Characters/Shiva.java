package org.academiadecodigo.bootcamp.Characters;

public class Shiva extends Enemy{
    public Shiva(){
        super((int)Math.round(Math.random()*200)+100, CharacterType.SHIVA,5);
    }
}
