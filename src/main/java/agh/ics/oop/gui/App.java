package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 400, 400);

        List<String> args = getParameters().getRaw();
        LinkedList<MoveDirection> directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d lowerLeft = ((AbstractWorldMap) map).findLowerLeft();
        Vector2d upperRight = ((AbstractWorldMap) map).findUpperRight();

        Label yx = new Label("y/x");
        grid.add(yx, 0, 0, 1, 1);
        GridPane.setHalignment(yx, HPos.CENTER);
        for (int i = lowerLeft.x; i <= upperRight.x; ++i) {
            Label labelColumns = new Label(String.valueOf(i));
            grid.add(labelColumns, i - lowerLeft.x + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(20));
            GridPane.setHalignment(labelColumns, HPos.CENTER);
        }

        for (int i = upperRight.y; i >= lowerLeft.y; --i) {
            Label labelRows = new Label(String.valueOf(i));
            grid.add(labelRows, 0, upperRight.y - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(20));
            GridPane.setHalignment(labelRows, HPos.CENTER);
        }

        for (int i = lowerLeft.x; i <= upperRight.x; ++i) {
            for (int j = upperRight.y; j >= lowerLeft.y; --j) {
                Object o = map.objectAt(new Vector2d(i, j));
                if (o != null) {
                    Label label = new Label(o.toString());
                    grid.add(label, i - lowerLeft.x + 1, upperRight.y - j + 1, 1, 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.getRowConstraints().add(new RowConstraints(20));

        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(lowerLeft);
        System.out.println(upperRight);
        System.out.println(map);
    }
}
