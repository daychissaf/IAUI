package iaui.ia.geniticalgorithm;

import iaui.ia.model.Mouse;
import iaui.ia.model.Room;

/**
 * Permet de générer la population initial constitué de 6 mouses avec des direction aléatoir
 */
public class PopulationGenarator {

        private static int popolationLength = 100;

        public static Mouse[] generateMouses(int roomsLength, Room room, Room targetRoom) {
                Mouse mouses[] = new Mouse[popolationLength];
                for (int i = 0; i < popolationLength; i++) {
                        mouses[i] = new Mouse(roomsLength).inRoom(room).withTargetRoom(targetRoom).generateRandomPath();
                }
                return mouses;
        }
}
