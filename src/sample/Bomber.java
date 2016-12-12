package sample;


/**
 * Created by pc on 11.12.2016.
 */
class Bomber implements Warrior {


    private int damage = 100;
    private int health = 50;
    private String name;
    private String squadName;

    Bomber(String name) {
        this.name = name;
    }

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public boolean isAlive() {
        return (health > 0);
    }

    @Override
    public void setSquadName(String name) {
        squadName = name;
    }

    @Override
    public String getHealthStatus() {
        if (health > 0) {
            return "Его здоровье равно " + health + "\n";
        } else return "Боец пал смертью храбрых!\n";
    }

    @Override
    public String getNameOnly() {
        return name;
    }

    @Override
    public String toString() {
        return name + ". Класс Подрывник, Отряд " + squadName + "\n";
    }

    @Override
    public Bomber clone() throws CloneNotSupportedException {
        return (Bomber) super.clone();
    }
}
