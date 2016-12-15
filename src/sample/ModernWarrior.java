package sample;

/**
 * Created by pc on 15.12.2016.
 */
abstract class ModernWarrior implements Warrior {

    private int damage;
    private int health;
    private String name;
    private String squadName;

    ModernWarrior(String warriorName, int warriorDamage, int warriorHealth) {
        name = warriorName;
        damage = warriorDamage;
        health = warriorHealth;
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
    public int getHealth() {
        return health;
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
    public String getSquadName() {
        return squadName;
    }

    @Override
    public String getNameOnly() {
        return name;
    }
}
