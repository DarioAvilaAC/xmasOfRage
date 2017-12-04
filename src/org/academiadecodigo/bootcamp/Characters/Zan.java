package org.academiadecodigo.bootcamp.Characters;

public class Zan extends Enemy{
    public Zan(){
        super((int)Math.round(Math.random()*200)+100, CharacterType.ZAN,5);
    }
}
