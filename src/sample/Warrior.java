package sample;

import javafx.scene.control.TextField;

/**
 * Created by pc on 11.12.2016.
 */
interface Warrior {

    int attack();

    void takeDamage(int damage);

    int getHealth();

    boolean isAlive();

    void setSquadName(String name);

    String getSquadName();

    String getNameOnly();
}
