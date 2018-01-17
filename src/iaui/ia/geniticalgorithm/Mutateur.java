package iaui.ia.geniticalgorithm;

import iaui.ia.model.Mouse;

public class Mutateur {

    private double LOWER_THRESHOLD = 0.001;
    private double UPPER_THRESHOLD = 0.01;


    public void mutate(Mouse mouse) {
        double random = Math.random();
        if (LOWER_THRESHOLD < random && random < UPPER_THRESHOLD) {
            mouse.mutate();
        }
    }
}
