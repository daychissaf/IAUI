package iaui.ui;

import iaui.ia.model.Mouse;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.List;

public class MouseUi implements ShapeUi {

    Mouse mouse;

    public MouseUi(Mouse mouse) {
        this.mouse = mouse;
    }

    @Override
    public Group getShape() {
        return null;
    }

    public void drawPath(List<RoomUi> roomsUiPath) {

        if (roomsUiPath.size() != 0) {
            final Group group = roomsUiPath.get(0).getGroup();
            Point centre = roomsUiPath.get(0).getCentre();
            final Circle circle = new Circle(centre.getX(), centre.getY(), 15);
            circle.setFill(mouse.getColor());

            final Path path = buildPathFromRoomsUi(roomsUiPath);

            Platform.runLater(
                    () -> {
                        group.getChildren().add(circle);
                    }
            );
            final PathTransition pathTransition = new PathTransition();

            pathTransition.setDuration(Duration.seconds(1));
            pathTransition.setDelay(Duration.seconds(0));
            pathTransition.setPath(path);
            pathTransition.setNode(circle);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.play();

            pathTransition.setOnFinished(e -> {
                group.getChildren().remove(circle);
            });
        }
    }

    private Path buildPathFromRoomsUi(List<RoomUi> roomsUiPath) {
        final Path path = new Path();
        Point centre = roomsUiPath.get(0).getCentre();
        path.getElements().add(new MoveTo(centre.getX(), centre.getY()));
        for (RoomUi roomUi:roomsUiPath) {
            Point point=roomUi.getCentre();
            path.getElements().add(new LineTo(point.getX(), point.getY()));
        }
        return path;
    }
}
