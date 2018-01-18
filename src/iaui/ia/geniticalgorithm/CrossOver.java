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
                if (random <= SEUIL) {
                        Mouse crossedMouses[]=new Mouse[2];
                        crossedMouses[0]=mouses[0].getCopy();
                        crossedMouses[1]=mouses[1].getCopy();
                        applyCross(crossedMouses);
                        if(crossedMouses[0].isBetterThan(mouses[0]) || crossedMouses[1].isBetterThan(mouses[1])){
                                System.out.println("Crossed and keep it");
                                mouses[0].kill();
                                mouses[1].kill();
                                return  crossedMouses;
                        }else{
                                System.out.println("Crossed by not find better");
                                crossedMouses[0].kill();
                                crossedMouses[1].kill();
                        }
                }
                return mouses;
        }

        private void applyCross(Mouse cossedMouses[]) {
                cossedMouses[0].cross(cossedMouses[1]);
        }
}
