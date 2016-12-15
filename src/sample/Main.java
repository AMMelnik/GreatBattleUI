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

    ComboBox warriorClassComboBox;
    Battle battle = new Battle();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception { //очень большой метод. нужно разбить на несколько

        stage.setTitle("Great Battle v.2.0");

        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(350, 400);
        scroll.setPadding(new Insets(25, 25, 25, 25));

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

        TextField squad1TextField = new TextField(); //всем полям нужно дать значения по умолчанию
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
        grid.add(hBoxSquadButton, 0, 5, 2, 1);

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

        warriorClassComboBox = new ComboBox();
        warriorClassComboBox.getItems().addAll("Разведчик", "Борец", "Подрывник"); //задать значение по умолчанию
        grid.add(warriorClassComboBox, 4, 2);

        Button setNewWarriorButton = new Button("Сохранить бойца");
        HBox hBoxWarriorButton = new HBox(10);
        hBoxWarriorButton.setAlignment(Pos.BOTTOM_LEFT);
        hBoxWarriorButton.getChildren().add(setNewWarriorButton);
        grid.add(hBoxWarriorButton, 3, 5, 2, 1);

        ToggleGroup radioSquadsGroup = new ToggleGroup();

        RadioButton setWarriorToSquad1RadioButton = new RadioButton("Отряд 1"); //одна из кнопок должна быть активна по умолчанию
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
        hBoxStartButton.getChildren().add(startBattleButton);
        grid.add(hBoxStartButton, 5, 5, 2, 1);

        Text someText = new Text();
        someText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 12));
        someText.setFill(Color.BROWN);
        grid.add(someText, 0, 4, 5, 1);

        Text showBattleText = new Text();
        showBattleText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 14));
        showBattleText.setFill(Color.BROWN);
        scroll.setContent(showBattleText);

        Scene sceneBattle = new Scene(scroll, 600, 500);

        Scene sceneSettings = new Scene(grid, 700, 200);
        stage.setScene(sceneSettings);
        stage.show();

        setSquadNamesButton.setOnAction(event ->
                battle.createSquads(squad1TextField.getText(), squad2TextField.getText()));

        setNewWarriorButton.setOnAction(event -> {
            someText.setText(" ");
            if (setWarriorToSquad1RadioButton.isSelected()) {
                battle.addWarriorToSquad1(warriorNameTextField.getText(), getWarClassName());
            }
            if (setWarriorToSquad2RadioButton.isSelected()) {
                battle.addWarriorToSquad2(warriorNameTextField.getText(), getWarClassName());
            }
            warriorNameTextField.setText("");
            clearComboboxValue();
        });

        startBattleButton.setOnAction(e -> {
            try {
                battle.startBattle();
                stage.setScene((sceneBattle));
                stage.show();
                showBattleText.setText(battle.showBattleInfo());
            } catch (IndexOutOfBoundsException iOoBe) {
                someText.setText("Сначала нужно задать имена отрядам и добавить в них бойцов!");
            }
        });
    }

    private int getWarClassName() {
        return warriorClassComboBox.getSelectionModel().getSelectedIndex();
    }

    private void clearComboboxValue() {
        warriorClassComboBox.getSelectionModel().clearSelection();
    }
}
