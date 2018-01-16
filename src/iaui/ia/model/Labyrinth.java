package iaui.ia.model;


import iaui.ia.exception.AccessRoomException;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

    List<Room> rooms;
    Room initialRoom;

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

    public void defineImportanceRatioFromRoom(Room roomA) {
        int step = 0;
        recursiveDefinitionForImportanceRatio(roomA, step);
    }

    private void recursiveDefinitionForImportanceRatio(Room roomA, int step) {
        roomA.defineImportanceRatio(rooms.size() - step);
        for (Direction direction : Direction.values()) {
            if (direction != Direction.STOP) {
                try {
                    Room roomTarget = roomA.getRoomByDirection(direction);
                    if(roomTarget.getImportanceRatio() <roomA.getImportanceRatio() + 1){
                        recursiveDefinitionForImportanceRatio(roomTarget, step + 1);
                    }
                } catch (AccessRoomException e) {

                }
            }
        }
    }

    public void refreshUi() {
        for (Room room: rooms) {
            room.refreshUi(initialRoom);
        }
    }

    public void print() {
        for (Room room : rooms) {
            room.print();
        }
        System.out.println("-------------------------------");
    }

    public void printMousePath(Mouse[] mouses) {
        System.out.println("---------------------------------------");
        for (Mouse mouse : mouses) {
            printMousePath(mouse);
            System.out.println("---------------------------------------");
        }
    }

    public void printMousePath(Mouse mouse) {
        mouse.print();
        System.out.println("Fitness ratio: " + mouse.calculateFitnessRatio());
        System.out.println("Position: " + mouse.getRoom());
        Room currentRoom = initialRoom;
        System.out.print("(" + initialRoom + "->");
        Direction directions[] = mouse.getPath();
        for (Direction direction : directions) {
            if (direction != Direction.STOP) {
                try {
                    currentRoom = currentRoom.getRoomByDirection(direction);
                    System.out.print(currentRoom + " ->");
                } catch (AccessRoomException e) {
                    System.out.println(")");
                    break;
                }
            }
        }
        System.out.println(")");
    }
}
