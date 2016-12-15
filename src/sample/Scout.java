package sample;


/**
 * Created by pc on 11.12.2016.
 */
class Scout extends ModernWarrior {

    private String someWarriorInfo = ". Класс Разведчик, Отряд ";

    Scout(String warriorName, int warriorDamage, int warriorHealth) {
        super(warriorName, warriorDamage, warriorHealth);
    }

    @Override
    public String toString() {
        return super.getNameOnly() + someWarriorInfo + super.getSquadName() + "\n";
    }

    @Override
    public Scout clone() throws CloneNotSupportedException {
        return (Scout) super.clone();
    }
}
