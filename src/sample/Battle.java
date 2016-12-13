package sample;


/**
 * Created by pc on 11.12.2016.
 */
class Battle {
    private Squad squad1 = new Squad();
    private Squad squad2 = new Squad();
    private StringBuilder battleInfo = new StringBuilder();
    private boolean isBattleEnd = false;
    private Warrior warrior1 = null;
    private Warrior warrior2 = null;

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

    void startBattle() {
        squad1.setSquadNameForWarriors();
        squad2.setSquadNameForWarriors();
        DateHelper dateHelper = new DateHelper();
        // пока есть живые в обоих отрядах
        while (!isBattleEnd) {
            //выбор бойцов для первого раунда
            warrior1 = squad1.getRandomWarrior();
            warrior2 = squad2.getRandomWarrior();
            // описание раунда
            prepareToShowBattleInfo(showRound1Ad());
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
            prepareToShowBattleInfo(showRound2Ad());
            warrior1.takeDamage(warrior2.attack());
            prepareToShowBattleInfo(warrior1.getHealthStatus());
            squad1.needHimDeleteFromSquad(warrior1);
            dateHelper.skipTime();
            if (showResults()) {
                continue;
            }
        }
        // выводим текущую дату-время -1500 лет
        prepareToShowBattleInfo("\n" + dateHelper.getFormattedStartDate() + "\n");
        // вывести время сражения
        prepareToShowBattleInfo(dateHelper.getFormattedDiff());
    }

    private boolean showResults() {
        if (squad1.hasAliveWarriors() & !squad2.hasAliveWarriors()) {
            String squad2down = "Отряд " + squad2.toString() +
                    " полностью разбит!!!\nПобеду одержал отряд " + squad1.toString() + " ! УРА!!!\n";
            prepareToShowBattleInfo(squad2down);
            return isBattleEnd = true;
        }
        // если есть живые во втором и нет живых во первом
        if (!squad1.hasAliveWarriors() & squad2.hasAliveWarriors()) {
            String squad1down = "Отряд " + squad1.toString() +
                    " полностью разбит!!!\nПобеду одержал отряд " + squad2.toString() + " ! УРА!!!\n";
            prepareToShowBattleInfo(squad1down);
            return isBattleEnd = true;
        }
        return false;
    }

    private void prepareToShowBattleInfo(String newInfo) {
        battleInfo.append(newInfo);
    }

    String showBattleInfo() {
        return battleInfo.toString();
    }

    private String showRound1Ad() {
        return "\n       На бой вызываются: \n" + warrior1.toString() + "и " + warrior2.toString() +
                "\nАтакует боец " + warrior1.getNameOnly() + "\nБоец " + warrior2.getNameOnly() + "  принимает удар!\n";
    }

    private String showRound2Ad() {
        return "\n       На бой вызываются: \n" + warrior1.toString() + "и " + warrior2.toString() +
                "\nАтакует боец " + warrior2.getNameOnly() + "\nБоец " + warrior1.getNameOnly() + "  принимает удар!\n";
    }
}
