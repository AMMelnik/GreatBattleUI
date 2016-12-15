package sample;


/**
 * Created by pc on 11.12.2016.
 */
class Fighter extends ModernWarrior {

    private String someWarriorInfo = ". Класс Борец, Отряд ";

    Fighter(String warriorName, int warriorDamage, int warriorHealth) {
        super(warriorName, warriorDamage, warriorHealth);
    }

    @Override
    public String toString() {
        return super.getNameOnly() + someWarriorInfo + super.getSquadName() + "\n";
    }

    @Override
    public Fighter clone() throws CloneNotSupportedException {
        return (Fighter) super.clone();
    }
}
