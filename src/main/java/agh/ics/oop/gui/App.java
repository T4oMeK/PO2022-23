package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) {
        Label label = new Label("Zwierzak");
        Scene scene = new Scene(label, 400, 400);
        GridPane grid = new GridPane();
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                GridPane.setConstraints(label, i, j);
            }
        }
        grid.getChildren().addAll(label);
        grid.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
