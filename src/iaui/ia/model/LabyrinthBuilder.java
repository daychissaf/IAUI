package iaui.ia.model;

public class LabyrinthBuilder {

    public static Labyrinth build() {
        Labyrinth labyrinth = new Labyrinth();
        Room roomA = new Room("A");
        Room roomB = new Room("B");
        Room roomC = new Room("C");
        Room roomD = new Room("D");
        Room roomE = new Room("E");
        Room roomF = new Room("F");
        Room roomG = new Room("G");
        Room roomH = new Room("H");
        Room roomI = new Room("I");
        Room roomJ = new Room("J");
        Room roomK = new Room("K");
        Room roomL = new Room("L");
        Room roomM = new Room("M");
        Room roomN = new Room("N");
        Room roomO = new Room("O");
        Room roomP = new Room("P");
        Room roomR = new Room("R");

        labyrinth.addRoom(roomA).addRoom(roomB).addRoom(roomC).addRoom(roomD).addRoom(roomE).addRoom(roomF).addRoom(roomG).addRoom(roomH).addRoom(roomI).addRoom(roomJ).addRoom(roomK).addRoom(roomL)
                .addRoom(roomM).addRoom(roomN).addRoom(roomO).addRoom(roomP).addRoom(roomR);

        labyrinth.roomAcces(roomA, roomD, Direction.DOWN);
        labyrinth.roomAcces(roomD, roomG, Direction.DOWN);
        labyrinth.roomAcces(roomG, roomH, Direction.RIGHT);
        labyrinth.roomAcces(roomH, roomE, Direction.UP);
        labyrinth.roomAcces(roomE, roomF, Direction.RIGHT);
        labyrinth.roomAcces(roomE, roomB, Direction.UP);
        labyrinth.roomAcces(roomE, roomD, Direction.LEFT);
        labyrinth.roomAcces(roomB, roomC, Direction.RIGHT);
        labyrinth.roomAcces(roomC, roomF, Direction.DOWN);
        labyrinth.roomAcces(roomF, roomI, Direction.DOWN);
        labyrinth.roomAcces(roomF, roomK, Direction.RIGHT);
        labyrinth.roomAcces(roomK, roomL, Direction.RIGHT);
        labyrinth.roomAcces(roomL, roomM, Direction.DOWN);
        labyrinth.roomAcces(roomM, roomN, Direction.DOWN);
        labyrinth.roomAcces(roomN, roomO, Direction.LEFT);
        labyrinth.roomAcces(roomO, roomP, Direction.LEFT);

        labyrinth.defineInitialRoom(roomE);
        labyrinth.defineImportanceRatioFromRoom(roomA);

        return labyrinth;
    }
}
