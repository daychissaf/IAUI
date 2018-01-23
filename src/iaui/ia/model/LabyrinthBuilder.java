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
        Room roomQ = new Room("Q");

        Room roomA1 = new Room("A1");
        Room roomB1 = new Room("B1");
        Room roomC1 = new Room("C1");
        Room roomD1 = new Room("D1");
        Room roomE1 = new Room("E1");
        Room roomF1 = new Room("F1");
        Room roomG1 = new Room("G1");
        Room roomH1 = new Room("H1");
        Room roomI1 = new Room("I1");
        Room roomJ1 = new Room("J1");
        Room roomK1 = new Room("K1");
        Room roomL1 = new Room("L1");
        Room roomM1 = new Room("M1");
        Room roomN1 = new Room("N1");
        Room roomO1 = new Room("O1");
        Room roomP1 = new Room("P1");
        Room roomR1 = new Room("R1");
        Room roomQ1 = new Room("Q1");

        labyrinth.addRoom(roomA).addRoom(roomB).addRoom(roomC).addRoom(roomD).addRoom(roomE).addRoom(roomF).addRoom(roomG).addRoom(roomH).addRoom(roomI).addRoom(roomJ).addRoom(roomK).addRoom(roomL)
                .addRoom(roomM).addRoom(roomN).addRoom(roomO).addRoom(roomP).addRoom(roomQ);

        labyrinth.addRoom(roomA1).addRoom(roomB1).addRoom(roomC1).addRoom(roomD1).addRoom(roomE1).addRoom(roomF1).addRoom(roomG1).addRoom(roomH1).addRoom(roomI1).addRoom(roomJ1).addRoom(roomK1).addRoom(roomL1)
                .addRoom(roomM1).addRoom(roomN1).addRoom(roomO1).addRoom(roomP1).addRoom(roomQ1);

        labyrinth.roomAcces(roomA, roomD, Direction.DOWN);
        labyrinth.roomAcces(roomA, roomB, Direction.RIGHT);
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
        labyrinth.roomAcces(roomP, roomI, Direction.UP);
        labyrinth.roomAcces(roomI, roomH, Direction.LEFT);


        labyrinth.roomAcces(roomP, roomA1, Direction.DOWN);
        labyrinth.roomAcces(roomO, roomB1, Direction.DOWN);

        labyrinth.roomAcces(roomA1, roomD1, Direction.DOWN);
        labyrinth.roomAcces(roomA1, roomB1, Direction.RIGHT);
        labyrinth.roomAcces(roomD1, roomG1, Direction.DOWN);
        labyrinth.roomAcces(roomG1, roomH1, Direction.RIGHT);
        labyrinth.roomAcces(roomH1, roomE1, Direction.UP);
        labyrinth.roomAcces(roomE1, roomF1, Direction.RIGHT);
        labyrinth.roomAcces(roomE1, roomB1, Direction.UP);
        labyrinth.roomAcces(roomE1, roomD1, Direction.LEFT);
        labyrinth.roomAcces(roomB1, roomC1, Direction.RIGHT);
        labyrinth.roomAcces(roomC1, roomF1, Direction.DOWN);
        labyrinth.roomAcces(roomF1, roomI1, Direction.DOWN);
        labyrinth.roomAcces(roomF1, roomK1, Direction.RIGHT);
        labyrinth.roomAcces(roomK1, roomL1, Direction.RIGHT);
        labyrinth.roomAcces(roomL1, roomM1, Direction.DOWN);
        labyrinth.roomAcces(roomM1, roomN1, Direction.DOWN);
        labyrinth.roomAcces(roomN1, roomO1, Direction.LEFT);
        labyrinth.roomAcces(roomO1, roomP1, Direction.LEFT);
        labyrinth.roomAcces(roomP1, roomR1, Direction.DOWN);
        labyrinth.roomAcces(roomR1, roomQ1, Direction.LEFT);
        labyrinth.roomAcces(roomP1, roomI1, Direction.UP);
        labyrinth.roomAcces(roomI1, roomH1, Direction.LEFT);

        labyrinth.defineInitialRoom(roomQ1);
        labyrinth.defineImportanceRatioFromRoom(roomA);

        return labyrinth;
    }
}
