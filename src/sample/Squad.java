package sample;

import java.util.ArrayList;


/**
 * Created by pc on 11.12.2016.
 */
class Squad {


    private String squadName;
    private ArrayList<Warrior> squad = new ArrayList<>();

    void setSquadName(String name) {
        squadName = name;
    }

    // добавление бойцов в отряд
    void addToSquad(String name, int type) {
        switch (type) {
            case 0:
                squad.add(new Scout(name));
                break;
            case 1:
                squad.add(new Fighter(name));
                break;
            case 2:
                squad.add(new Bomber(name));
                break;
        }
    }

    void setSquadNameForWarriors() {
        for (Warrior warrior : squad) {
            warrior.setSquadName(squadName);
        }
    }

    Warrior getRandomWarrior() {
        int randomWarrior = (int) (Math.random() * (squad.size()));
        return squad.get(randomWarrior);
    }

    boolean hasAliveWarriors() {
        return !squad.isEmpty();
    }

    void needHimDeleteFromSquad(Warrior warrior) {
        if (!warrior.isAlive()) squad.remove(warrior);
    }

    @Override
    public String toString() {
        return squadName;
    }
}
