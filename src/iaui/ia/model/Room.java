package iaui.ia.model;

import iaui.ia.exception.AccessRoomException;
import iaui.ui.RoomUi;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private Room visibleRooms[];
    private int importanceRatio;
    private List<Mouse> mouses;
    private RoomUi view;

    private boolean drawn = false;

    public Room() {
        visibleRooms = new Room[4];
        mouses = new ArrayList<Mouse>();
    }

    public Room(String name) {
        this();
        this.name = name;
    }

    public void addMouse(Mouse mouse) {
        mouses.add(mouse);
    }

    public void addRoomAccess(Room room, Direction direction) {
        this.visibleRooms[direction.getOrder() - 1] = room;
    }

    public Room defineImportanceRatio(int importanceRatio) {
        if (this.importanceRatio < importanceRatio) {
            this.importanceRatio = importanceRatio;
        }
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Room getRoomByDirection(Direction direction) {
        if (direction == Direction.STOP) {
            return this;
        }
        Room visibleRoom = visibleRooms[direction.getOrder() - 1];
        if (visibleRoom == null) throw new AccessRoomException();
        return visibleRoom;
    }

    public int getImportanceRatio() {
        return importanceRatio;
    }

    public String getName() {
        return name;
    }

    public void markAsDrawn() {
        this.drawn = true;
    }

    public boolean isDrawn() {
        return drawn;
    }


    public void withView(RoomUi view) {
        this.view = view;
    }

    public void refreshUi(Room initialRoom) {
        Room currentMouseRoom = initialRoom;
        for (Mouse mouse : mouses) {
            Direction directions[] = mouse.getPath();
            for (Direction direction : directions) {
                if (direction != Direction.STOP) {
                    try {
                        currentMouseRoom = currentMouseRoom.getRoomByDirection(direction);
                        currentMouseRoom.drawMouse(mouse);
                    } catch (AccessRoomException e) {
                        break;
                    }
                }
            }
        }
    }

    private void drawMouse(Mouse mouse) {
        this.view.draw(mouse.getColor());
    }

    public void removeMouse(Mouse mouse) {
        this.mouses.remove(mouse);
    }

    public void print() {
        System.out.println(this.name + ":" + this.mouses.size() + " mouses");
    }
}
