package iaui.ui;

import iaui.ia.exception.AccessRoomException;
import iaui.ia.model.Direction;
import iaui.ia.model.Labyrinth;
import iaui.ia.model.Room;
import javafx.scene.Group;

public class LabyrinthUi implements ShapeUi {

    private Labyrinth labyrinth;
    private int roomSize = 90;

    public LabyrinthUi(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public Group getShape() {
        Group groupResult = new Group();
        Room roomA = labyrinth.getInitialRoom();
        fillGroupRecursively(roomA, groupResult, 0, 0);
        return groupResult;
    }

    private void fillGroupRecursively(Room roomA, Group groupResult, int x, int y) {
        groupResult.getChildren().add(new RoomUi(x, y, roomSize, roomSize).withRoom(roomA).getShape());
        for (Direction direction : Direction.values()) {
            if (direction != Direction.STOP) {
                try {
                    Room roomTarget = roomA.getRoomByDirection(direction);
                    if (!roomTarget.isDrawn()) {
                        Point point = calculateStartPointBasingOnDirection(x, y, direction);
                        fillGroupRecursively(roomTarget, groupResult, point.getX(), point.getY());
                    }
                } catch (AccessRoomException e) {

                }
            }
        }
    }

    private Point calculateStartPointBasingOnDirection(int x, int y, Direction direction) {
        switch (direction) {
            case RIGHT:
                return new Point(x + roomSize, y);
            case LEFT:
                return new Point(x - roomSize, y);
            case UP:
                return new Point(x, y - roomSize);
            case DOWN:
                return new Point(x, y + roomSize);
            case STOP:
                throw new IllegalArgumentException();
        }
        return null;
    }
}
