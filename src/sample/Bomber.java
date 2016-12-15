package sample;


/**
 * Created by pc on 11.12.2016.
 */
class Bomber extends ModernWarrior {

    private String someWarriorInfo = ". Класс Подрывник, Отряд ";

    Bomber(String warriorName, int warriorDamage, int warriorHealth) {
        super(warriorName, warriorDamage, warriorHealth);
    }

    @Override
    public String toString() {
        return super.getNameOnly() + someWarriorInfo + super.getSquadName() + "\n";
    }

    @Override
    public Bomber clone() throws CloneNotSupportedException {
        return (Bomber) super.clone();
    }
}
