package org.academiadecodigo.bootcamp.Characters;

public class EnemyFactory {

    public static Enemy createEnemy(boolean isBoss) {
        Enemy enemy = null;
        if(!isBoss){
        CharacterType random = CharacterType.values()[(int) (Math.random() * (CharacterType.values().length-2) )];

        switch (random) {
            case SHIVA:
                enemy = new Shiva();
                break;
            case ZAN:
                enemy = new Zan();
                break;
        }
        }else{
            enemy = new Santa();
        }

        return enemy;
    }

    /*enum EnemyType{
        SHIVA,
        ZAN,
        SANTA
    }
    */
}

