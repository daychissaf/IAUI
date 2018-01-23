package iaui.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class MouseShape {

    List<Shape> shapes = new ArrayList<>();
    Group group;

    public MouseShape(int x, int y, int radius, Color color) {
        buildShapes(x, y, radius, color);
        this.group = groupAllShapes();
    }

    private void buildShapes(int x, int y, int radius, Color color) {
        buildCircle(x, y, radius, color);
    }

    private void buildCircle(int x, int y, int radius, Color color) {
        final Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        shapes.add(circle);

        final Circle or1 = new Circle(x + radius, y - radius, radius / 3 + 2);
        or1.setFill(color);
        shapes.add(or1);

        final Circle or2 = new Circle(x - radius, y - radius, radius / 3 + 2);
        or2.setFill(color);
        shapes.add(or2);

        final Circle or3 = new Circle(x, y + (radius/2), radius / 3);
        or3.setFill(Color.GRAY);
        or3.setStroke(Color.GRAY);
        shapes.add(or3);
    }

    private Group groupAllShapes() {
        Group group = new Group();
        for (Shape shape : shapes) {
            group.getChildren().add(shape);
        }
        return group;
    }

    public Group getShape() {
        return this.group;
    }
}
