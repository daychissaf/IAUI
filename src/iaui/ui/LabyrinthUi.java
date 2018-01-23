package iaui.ui;

import iaui.ia.exception.AccessRoomException;
import iaui.ia.model.Direction;
import iaui.ia.model.Labyrinth;
import iaui.ia.model.Room;
import javafx.scene.Group;

public class LabyrinthUi {

    private Labyrinth labyrinth;
    private int roomSize = 90;

    public LabyrinthUi(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public Group getShape() {
        Group groupResult = new Group();
        Room initialRoom = labyrinth.getInitialRoom();
        Room targetRoom = labyrinth.getTargetRoom();
        fillGroupRecursively(initialRoom, targetRoom, groupResult, 0, 0);
        return groupResult;
    }

    private void fillGroupRecursively(Room roomA, Room labyrinthTargetRoom, Group groupResult, int x, int y) {
        RoomUi roomUi = new RoomUi(x, y, roomSize, roomSize);
        if (roomA.equals(labyrinthTargetRoom)) {
            roomUi.markAsTarget();
        }
        groupResult.getChildren().add(roomUi.withRoom(roomA).getShape());
        for (Direction direction : Direction.values()) {
            if (direction != Direction.STOP) {
                try {
                    Room currentTargetRoom = roomA.getRoomByDirection(direction);
                    if (!currentTargetRoom.isDrawn()) {
                        Point point = calculateStartPointBasingOnDirection(x, y, direction);
                        fillGroupRecursively(currentTargetRoom, labyrinthTargetRoom, groupResult, point.getX(), point.getY());
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
