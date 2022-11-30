package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class App extends Application implements IRenderObserver {
    private IWorldMap map = new GrassField(10);
    private GridPane grid = new GridPane();
    private SimulationEngineDraw engine;
    private Thread thread;
    private int moveDelay = 500;
    private int size = 50;
    @Override
    public void init() throws Exception {
        super.init();
        Parameters parameters = getParameters();
        String[] args = parameters.getRaw().toArray(new String[0]);

        try {
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            engine = new SimulationEngineDraw(map, positions, moveDelay);
            engine.addObserver(this);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void draw(){
        Vector2d lowerLeft = ((AbstractWorldMap) map).findLowerLeft();
        Vector2d upperRight = ((AbstractWorldMap) map).findUpperRight();

        Label yx = new Label("y/x");
        grid.add(yx, 0, 0, 1, 1);
        GridPane.setHalignment(yx, HPos.CENTER);
        for (int i = lowerLeft.x; i <= upperRight.x; ++i) {
            Label labelColumns = new Label(String.valueOf(i));
            grid.add(labelColumns, i - lowerLeft.x + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(size));
            GridPane.setHalignment(labelColumns, HPos.CENTER);
        }

        for (int i = upperRight.y; i >= lowerLeft.y; --i) {
            Label labelRows = new Label(String.valueOf(i));
            grid.add(labelRows, 0, upperRight.y - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(size));
            GridPane.setHalignment(labelRows, HPos.CENTER);
        }

        for (int i = lowerLeft.x; i <= upperRight.x; ++i) {
            for (int j = upperRight.y; j >= lowerLeft.y; --j) {
                Object o = map.objectAt(new Vector2d(i, j));
                if (o != null) {
                    GuiElementBox image = new GuiElementBox((IMapElement) o);
                    VBox element = image.getVbox();
                    grid.add(element, i - lowerLeft.x + 1, upperRight.y - j + 1, 1, 1);
                    GridPane.setHalignment(element, HPos.CENTER);
                }
            }
        }
        grid.getColumnConstraints().add(new ColumnConstraints(size));
        grid.getRowConstraints().add(new RowConstraints(size));

        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
    }
    public void render() {
        Platform.runLater(() -> {
            grid.getChildren().clear();
            grid.getRowConstraints().clear();
            grid.getColumnConstraints().clear();
            grid.setGridLinesVisible(false);
            draw();
        });
    }
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();
        TextField textField = new TextField();
        Button button = new Button("Move");
        Scene scene = new Scene(vBox, 600, 600);

        button.setOnAction((event) -> {
            String[] moves = textField.getCharacters().toString().split(" ");
            LinkedList<MoveDirection> directions = OptionsParser.parse(List.of(moves));
            this.engine.setDirections(directions);
            this.thread = new Thread(engine);
            thread.start();
        });

        vBox.getChildren().addAll(textField, button);
        vBox.getChildren().add(grid);

        draw();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
