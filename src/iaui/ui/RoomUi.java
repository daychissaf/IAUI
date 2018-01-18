package iaui.ui;

import iaui.ia.exception.AccessRoomException;
import iaui.ia.model.Direction;
import iaui.ia.model.Room;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import static iaui.ui.RoomUi.LINE_TYPE.FULL;
import static iaui.ui.RoomUi.LINE_TYPE.PARTIAL;

public class RoomUi implements ShapeUi {

    private int x;
    private int y;
    private int width;
    private int height;

    private Paint rectangleColor = Color.BLACK;

    private Room room;

    Group group;

    enum LINE_TYPE {
        PARTIAL, FULL;
    }

    public RoomUi(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Group getShape() {
        Group groupResult = new Group();

        Group rightDoor = null;
        Group leftDoor = null;
        Group upDoor = null;
        Group downDoor = null;

        try {
            room.getRoomByDirection(Direction.RIGHT);
            rightDoor = getRightDoor(PARTIAL);
        } catch (AccessRoomException e) {
            rightDoor = getRightDoor(FULL);
        }

        try {
            room.getRoomByDirection(Direction.LEFT);
            leftDoor = getLeftDoor(PARTIAL);
        } catch (AccessRoomException e) {
            leftDoor = getLeftDoor(FULL);
        }

        try {
            room.getRoomByDirection(Direction.UP);
            upDoor = getUpDoor(PARTIAL);
        } catch (AccessRoomException e) {
            upDoor = getUpDoor(FULL);
        }

        try {
            room.getRoomByDirection(Direction.DOWN);
            downDoor = getDownDoor(PARTIAL);
        } catch (AccessRoomException e) {
            downDoor = getDownDoor(FULL);
        }

        groupResult.getChildren().add(rightDoor);
        groupResult.getChildren().add(leftDoor);
        groupResult.getChildren().add(upDoor);
        groupResult.getChildren().add(downDoor);

        this.room.markAsDrawn();
        this.group = groupResult;
        return groupResult;
    }

    private Group getRightDoor(LINE_TYPE lineType) {
        Group group = new Group();
        switch (lineType) {
            case FULL:
                group.getChildren().add(getLine(x + width, y, x + width, y + height));
            case PARTIAL:
                group.getChildren().add(getLine(x + width, y, x + width, y + (height / 3)));
                group.getChildren().add(getLine(x + width, y + (2 * (height / 3)), x + width, y + height));
        }
        return group;
    }

    private Group getLeftDoor(LINE_TYPE lineType) {
        Group group = new Group();
        switch (lineType) {
            case FULL:
                group.getChildren().add(getLine(x, y, x, y + height));
            case PARTIAL:
                group.getChildren().add(getLine(x, y, x, y + (height / 3)));
                group.getChildren().add(getLine(x, y + 2 * (height / 3), x, y + height));
        }
        return group;
    }

    private Group getUpDoor(LINE_TYPE lineType) {
        Group group = new Group();
        switch (lineType) {
            case FULL:
                group.getChildren().add(getLine(x, y, x + width, y));
            case PARTIAL:
                group.getChildren().add(getLine(x, y, x + (width / 3), y));
                group.getChildren().add(getLine(x + 2 * (width / 3), y, x + width, y));
        }
        return group;

    }

    private Group getDownDoor(LINE_TYPE lineType) {
        Group group = new Group();
        switch (lineType) {
            case FULL:
                group.getChildren().add(getLine(x, y + height, x + width, y + height));
            case PARTIAL:
                group.getChildren().add(getLine(x, y + height, x + (width / 3), y + height));
                group.getChildren().add(getLine(x + 2 * (width / 3), y + height, x + width, y + height));
        }
        return group;
    }

    private Line getLine(int x, int y, int width, int height) {
        Line line1 = new Line(x, y, width, height);
        line1.setStroke(rectangleColor);
        line1.setStrokeWidth(3);
        return line1;
    }

    public ShapeUi withRoom(Room room) {
        this.room = room;
        this.room.withView(this);
        return this;
    }

    public void draw(Color color) {
        //Nothing
    }

    public Group getGroup() {
        return group;
    }

    public Point getCentre() {
        return new Point(x + (width / 2), y + (height / 2));
    }
}
