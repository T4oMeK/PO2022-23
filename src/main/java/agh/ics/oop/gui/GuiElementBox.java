package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;

    private Label label;

    private VBox vbox = new VBox(4);

    public GuiElementBox(IMapElement element) {
        try {
            this.image = new Image(new FileInputStream(element.toImage()));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            this.label = new Label(element.toLabel());
            this.vbox.getChildren().add(imageView);
            this.vbox.getChildren().add(label);
            this.vbox.setAlignment(Pos.CENTER);
        }
        catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getVbox() {
        return this.vbox;
    }
}
