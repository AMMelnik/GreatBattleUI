package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    Battle battle = new Battle();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int warriorType = 0;
        stage.setTitle("Great Battle v.2.0");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text setSquadNameTitle = new Text("Задайте названия отрядам");
        setSquadNameTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 16));
        grid.add(setSquadNameTitle, 0, 0, 2, 1);

        Label squad1Name = new Label("Отряд 1:");
        squad1Name.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        grid.add(squad1Name, 0, 1);

        TextField squad1TextField = new TextField();
        grid.add(squad1TextField, 1, 1);

        Label squad2Name = new Label("Отряд 2:");
        squad2Name.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        grid.add(squad2Name, 0, 2);

        TextField squad2TextField = new TextField();
        grid.add(squad2TextField, 1, 2);

        Button setSquadNamesButton = new Button("Сохранить отряды");
        HBox hBoxSquadButton = new HBox(10);
        hBoxSquadButton.setAlignment(Pos.BOTTOM_LEFT);
        hBoxSquadButton.getChildren().add(setSquadNamesButton);
        grid.add(hBoxSquadButton, 0, 4);

        final Text actionTargetSquadButton = new Text();
        grid.add(actionTargetSquadButton, 0, 6, 2, 1);

        Text setWarriorTitle = new Text("  Новый боец");
        setWarriorTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 16));
        grid.add(setWarriorTitle, 2, 0, 2, 1);

        Label warriorNameLabel = new Label("Имя бойца");
        warriorNameLabel.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        grid.add(warriorNameLabel, 3, 1);

        TextField warriorNameTextField = new TextField();
        grid.add(warriorNameTextField, 4, 1);

        Label warriorClassLabel = new Label("Класс бойца");
        warriorClassLabel.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        grid.add(warriorClassLabel, 3, 2);

        ComboBox warriorClassComboBox = new ComboBox();
        warriorClassComboBox.getItems().addAll("Разведчик", "Борец", "Подрывник");
        grid.add(warriorClassComboBox, 4, 2);
        if (warriorClassComboBox.getId().equals(0)) {
            warriorType = 1;
        }
        if (warriorClassComboBox.getId().equals(1)) {
            warriorType = 2;
        }
        if (warriorClassComboBox.getId().equals(2)) {
            warriorType = 3;
        }

        Button setNewWarriorButton = new Button("Сохранить бойца");
        HBox hBoxWarriorButton = new HBox(10);
        hBoxWarriorButton.setAlignment(Pos.BOTTOM_LEFT);
        hBoxWarriorButton.getChildren().add(setNewWarriorButton);
        grid.add(hBoxWarriorButton, 3, 4);

        final Text actionTargetWarriorButton = new Text();
        grid.add(actionTargetWarriorButton, 3, 6);

        ToggleGroup radioSquadsGroup = new ToggleGroup();

        RadioButton setWarriorToSquad1RadioButton = new RadioButton("Отряд 1");
        setWarriorToSquad1RadioButton.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        grid.add(setWarriorToSquad1RadioButton, 5, 1);
        setWarriorToSquad1RadioButton.setToggleGroup(radioSquadsGroup);

        RadioButton setWarriorToSquad2RadioButton = new RadioButton("Отряд 2");
        setWarriorToSquad2RadioButton.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        grid.add(setWarriorToSquad2RadioButton, 5, 2);
        setWarriorToSquad2RadioButton.setToggleGroup(radioSquadsGroup);

        Button startBattleButton = new Button("Начать сражение!");
        HBox hBoxStartButton = new HBox(10);
        hBoxStartButton.setAlignment(Pos.BOTTOM_LEFT);
        hBoxStartButton.getChildren().

                add(startBattleButton);
        grid.add(hBoxStartButton, 0, 7);

        Text showBattleText = new Text();
        showBattleText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        showBattleText.setFill(Color.BROWN);
        grid.add(showBattleText, 0, 8, 6, 10);

        Scene scene = new Scene(grid, 750, 500);
        stage.setScene(scene);
        stage.show();

        setSquadNamesButton.setOnAction(event ->
        {
            battle.createSquads(squad1TextField.getText(), squad2TextField.getText());
            actionTargetSquadButton.setFill(Color.FIREBRICK);
            actionTargetSquadButton.setText("Названия отрядов установлены");
            showBattleText.setText(battle.showSquadsName());
        });

        int finalWarriorType = warriorType;
        setNewWarriorButton.setOnAction(event ->
        {
            if (setWarriorToSquad1RadioButton.isSelected()) {
                battle.addWarriorToSquad1(warriorNameTextField.getText(), finalWarriorType);
            }
            if (setWarriorToSquad2RadioButton.isSelected()) {
                battle.addWarriorToSquad2(warriorNameTextField.getText(), finalWarriorType);
            }
            actionTargetWarriorButton.setFill(Color.FIREBRICK);
            actionTargetWarriorButton.setText("Боец сохранен");
        });

        startBattleButton.setOnAction(e ->

        {
            actionTargetSquadButton.setText("");
            actionTargetWarriorButton.setText("");
            //  battle.startBattle();
            //     showBattleText.setText(battle.showBattleInfo());
        });
    }
}
