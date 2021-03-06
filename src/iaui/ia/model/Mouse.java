package iaui.ia.model;

import iaui.ia.exception.AccessRoomException;
import iaui.ui.MouseUi;
import iaui.ui.RoomUi;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

import static iaui.ia.model.Direction.STOP;

public class Mouse {

    Direction path[];
    int stopIndex = 0;
    Room initialRoom;
    Room targetRoom;
    Room currentRoom;

    Color color;

    MouseUi mouseView;

    public Mouse(int pathMax) {
        path = new Direction[pathMax];
        color = getRandomColor();
        initPath();
        mouseView = new MouseUi(this);
    }

    private Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b, 1);
    }

    private void initPath() {
        for (int i = 0; i < path.length; i++) {
            path[i] = STOP;
        }
    }

    private void moveRandom() {
        if (stopIndex == 0) {
            move(STOP.getRandomDirection());
        } else {
            move(path[stopIndex - 1].getRandomDirection());
        }
    }

    private void move(Direction direction) {
        path[stopIndex] = direction;
        try {
            Room roomByDirection = currentRoom.getRoomByDirection(path[stopIndex]);
            updateCurrentRoomRelation(roomByDirection);
            if (roomByDirection.equals(targetRoom)) {
                stopMoving(++stopIndex);
            } else {
                stopIndex++;
            }
        } catch (AccessRoomException e) {
            stopMoving(stopIndex++);
        }
    }

    private void stopMoving(int currentIndex) {
        for (int i = currentIndex; i < path.length; i++) {
            path[i] = STOP;
        }
        throw new AccessRoomException();
    }

    public Mouse generateRandomPath() {
        for (int i = 0; i < path.length; i++) {
            try {
                this.moveRandom();
            } catch (AccessRoomException e) {
                break;
            }
        }
        return this;
    }

    public double calculateFitnessRatio() {
        return currentRoom.getImportanceRatio() - (stopIndex * 0.1);
    }

    public Mouse inRoom(Room room) {
        this.initialRoom = room;
        updateCurrentRoomRelation(room);
        return this;
    }

    private void updateCurrentRoomRelation(Room room) {
        Room oldRoom = this.currentRoom;
        this.currentRoom = room;
        this.currentRoom.addMouse(this);
        if (oldRoom != null) {
            oldRoom.removeMouse(this);
        }
    }

    public Direction[] getPath() {
        return path;
    }

    public Room getRoom() {
        return currentRoom;
    }

    public Mouse getCopy() {
        Mouse mouseCopy = new Mouse(this.path.length);
        int i = 0;
        for (Direction direction : this.path) {
            mouseCopy.path[i++] = direction;
        }
        mouseCopy.stopIndex = this.stopIndex;
        mouseCopy.updateCurrentRoomRelation(this.currentRoom);
        mouseCopy.initialRoom = this.initialRoom;
        mouseCopy.color = getRandomColor();
        mouseCopy.targetRoom = this.targetRoom;
        return mouseCopy;
    }

    public void cross(Mouse mouse) {
        int maxStopIndex = this.stopIndex > mouse.stopIndex ? this.stopIndex : mouse.stopIndex;
        if(maxStopIndex>1){
            int random1 = (int) Math.floor((Math.random() * 100)) % (maxStopIndex / 2);
            int random2 = (int) Math.floor((Math.random() * 100)) % (maxStopIndex / 2) + (maxStopIndex / 2);
            for (int i = random1; i <= random2; i++) {
                    for (int j = i; j <= random2; j++) {
                        Direction direction = this.path[j];
                        this.path[j] = mouse.path[j];
                        mouse.path[j] = direction;
                    }
                    break;
            }

            this.refreshPathLogic();
            mouse.refreshPathLogic();
        }
    }

    public void mutate() {
        if (stopIndex > 0) {
            int random = (int) Math.floor((Math.random() * 100)) % stopIndex;
            path[random] = path[random].getRandomDirection();
            this.refreshPathLogic();
        }else{
            path[0] = path[0].getRandomDirection();
            this.refreshPathLogic();
        }

    }

    private void refreshPathLogic() {
        updateCurrentRoomRelation(this.initialRoom);
        stopIndex = 0;
        for (Direction direction : path) {
            try {
                this.move(direction);
            } catch (AccessRoomException e) {
                break;
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public void kill() {
        this.currentRoom.removeMouse(this);
    }

    public void drawPath(List<RoomUi> roomsUiPath) {
        mouseView.drawPath(roomsUiPath);
    }

    public Mouse withTargetRoom(Room targetRoom) {
        this.targetRoom = targetRoom;
        return this;
    }

    public boolean isBetterThan(Mouse mouse) {
        return this.calculateFitnessRatio() >= mouse.calculateFitnessRatio();
    }
}
