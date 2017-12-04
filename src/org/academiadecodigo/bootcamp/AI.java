package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Characters.*;

public class AI {


    public Characters.State pickAction (Player player, Enemy enemy) {

        Figure playerFrame = player.getFrame();
        Figure enemyFrame = enemy.getFrame();


        //switch ()


        if (Math.abs(enemyFrame.getX() - playerFrame.getMaxX()) <= 10 || Math.abs(enemyFrame.getMaxX() - playerFrame.getX()) <= 10) {
            if (enemy.attack(player)) {
                player.loseHP((int) Math.round(Math.random() * 40) + 20);
            }
            return Characters.State.ATTACKING;
        }
        if (enemyFrame.getX() - playerFrame.getMaxX() > 10) {
            enemy.changeFacing(Characters.Facing.LEFT);
            return Characters.State.WALKING;


        } else {

            //(playerFrame.getX() - enemyFrame.getMaxX() > 10){
            enemy.changeFacing(Characters.Facing.RIGHT);
            return Characters.State.WALKING;
        }

        //return null;

    }

}
