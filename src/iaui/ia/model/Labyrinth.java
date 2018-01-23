package iaui.ia.model;


import iaui.ia.exception.AccessRoomException;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

    List<Room> rooms;
    Room initialRoom;
    Room targetRoom;

    public Labyrinth() {
        rooms = new ArrayList<Room>();
    }

    public Labyrinth addRoom(Room room) {
        this.rooms.add(room);
        return this;
    }

    public void roomAcces(Room roomSource, Room roomTarget, Direction direction) {
        roomSource.addRoomAccess(roomTarget, direction);
        roomTarget.addRoomAccess(roomSource, direction.getOpposite());
    }

    public int getRoomsLength() {
        return rooms.size();
    }

    public void defineInitialRoom(Room initialRoom) {
        this.initialRoom = initialRoom;
    }

    public Room getInitialRoom() {
        return this.initialRoom;
    }

    public void defineImportanceRatioFromRoom(Room targetRoom) {
        int step = 0;
        this.targetRoom = targetRoom;
        recursiveDefinitionForImportanceRatio(targetRoom, step);
    }

    private void recursiveDefinitionForImportanceRatio(Room roomA, int step) {
        roomA.defineImportanceRatio(rooms.size() - step);
        for (Direction direction : Direction.values()) {
            if (direction != Direction.STOP) {
                try {
                    Room roomTarget = roomA.getRoomByDirection(direction);
                    if (roomTarget.getImportanceRatio() < roomA.getImportanceRatio() + 1) {
                        recursiveDefinitionForImportanceRatio(roomTarget, step + 1);
                    }
                } catch (AccessRoomException e) {

                }
            }
        }
    }

    public void refreshUi() {
        for (Room room : rooms) {
            room.refreshUi(initialRoom);
        }
    }

    public Room getTargetRoom() {
        return targetRoom;
    }
}
