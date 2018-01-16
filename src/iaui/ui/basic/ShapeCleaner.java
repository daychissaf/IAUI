package iaui.ui.basic;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ShapeCleaner extends Thread {

    Group group;
    Circle circle;

    public ShapeCleaner(Group group, Circle circle) {
        this.group = group;
        this.circle = circle;
    }

    @Override
    public void run() {
        circle.setFill(Color.WHITE);
        Platform.runLater(
                () -> {
                    group.getChildren().add(circle);
                }
        );
    }
}
