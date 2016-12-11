package sample;

import javafx.scene.control.TextField;

/**
 * Created by pc on 11.12.2016.
 */
class Battle {
    private Squad squad1 = new Squad();
    private Squad squad2 = new Squad();
    StringBuilder battleInfo = new StringBuilder();
    boolean isBattleEnd = false;

    void createSquads(String name1, String name2) {
        squad1.setSquadName(name1);
        squad2.setSquadName(name2);
    }

    void addWarriorToSquad1(String name, int type) {
        squad1.addToSquad(name, type);
    }

    void addWarriorToSquad2(String name, int type) {
        squad2.addToSquad(name, type);
    }

    String showSquadsName() {
        return " Первый отряд: " + squad1.toString() + "\n Второй отряд: " + squad2.toString() + "\n";
    }

    void startBattle() {
        squad1.setSquadNameForWarriors();
        squad2.setSquadNameForWarriors();
        DateHelper dateHelper = new DateHelper();
        // выводим текущую дату-время -1500 лет
        prepareToShowBattleInfo(dateHelper.getFormattedStartDate() + "\n");
        Warrior warrior1, warrior2;
        // пока есть живые в обоих отрядах
        while (!isBattleEnd) {
            //выбор бойцов для первого раунда
            warrior1 = squad1.getRandomWarrior();
            warrior2 = squad2.getRandomWarrior();
            // описание раунда
            String round1 = "\u001b[30;m     На бой вызываются: \n" + warrior1.toString() + "и " + warrior2.toString() +
                    "\n\u001b[30;mАтакует боец " + warrior1.getNameOnly() + "\n\u001b[30;mБоец " +
                    warrior2.getNameOnly() + "\u001b[30;m  принимает удар!\n";
            prepareToShowBattleInfo(round1);
            // удар и потеря здоровья
            warrior2.takeDamage(warrior1.attack());
            prepareToShowBattleInfo(warrior2.getHealthStatus());
            squad2.needHimDeleteFromSquad(warrior2);
            // пропускаем время раунда 20мин
            dateHelper.skipTime();
            // если есть живые в первом и нет живых во втором
            if (showResults()) {
                continue;
            }
            //выбор бойцов для второго раунда
            warrior1 = squad1.getRandomWarrior();
            warrior2 = squad2.getRandomWarrior();
            String round2 = "\u001b[30;m     На бой вызываются: \n" + warrior1.toString() + "и " + warrior2.toString() +
                    "\n\u001b[30;mАтакует боец " + warrior2.getNameOnly() + "\n\u001b[30;mБоец " +
                    warrior1.getNameOnly() + "\u001b[30;m  принимает удар!";
            prepareToShowBattleInfo(round2);
            warrior1.takeDamage(warrior2.attack());
            prepareToShowBattleInfo(warrior1.getHealthStatus());
            squad1.needHimDeleteFromSquad(warrior1);
            dateHelper.skipTime();
            if (showResults()) {
                continue;
            }
        }
        // вывести вермя сражения
        prepareToShowBattleInfo(dateHelper.getFormattedDiff());

    }

    boolean showResults() {
        if (squad1.hasAliveWarriors() & !squad2.hasAliveWarriors()) {
            String squad2down = "\u001b[31;m Отряд " + squad2.toString() +
                    " полностью разбит!!!\n\u001b[36;m Победу одержал отряд " + squad1.toString() + " ! УРА!!!\n";
            prepareToShowBattleInfo(squad2down);
            return isBattleEnd = true;

        }
        // если есть живые во втором и нет живых во первом
        if (!squad1.hasAliveWarriors() & squad2.hasAliveWarriors()) {
            String squad1down = "\u001b[31;m Отряд " + squad1.toString() +
                    " полностью разбит!!!\n\u001b[36;m Победу одержал отряд " + squad2.toString() + " ! УРА!!!\n";
            prepareToShowBattleInfo(squad1down);
            return isBattleEnd = true;
        }
        return false;
    }

    void prepareToShowBattleInfo(String newInfo) {
        battleInfo.append(newInfo);
    }

    String showBattleInfo() {
        return battleInfo.toString();
    }
}
