package iaui.ia.geniticalgorithm;

import iaui.ia.model.Mouse;

public class CrossOver {

        private static double SEUIL = 0.7;
        Mouse mouses[] = new Mouse[2];

        public CrossOver(Mouse[] selectedMouses) {
                mouses[0] = selectedMouses[0].getCopy();
                mouses[1] = selectedMouses[1].getCopy();
        }

        public Mouse[] crossTwoMouses() {
                double random = Math.random();
                if (random < SEUIL) {
                        applyCross();
                }
                return mouses;
        }

        private void applyCross() {
                mouses[0].cross(mouses[1]);
        }
}
