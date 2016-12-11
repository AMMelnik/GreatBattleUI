package sample;

import javafx.scene.control.TextField;

/**
 * Created by pc on 11.12.2016.
 */
interface Warrior {

    int attack();

    void takeDamage(int damage);

    boolean isAlive();

    void setSquadName(String name);

    String getHealthStatus();

    String getNameOnly();

    Warrior clone() throws CloneNotSupportedException;
}
