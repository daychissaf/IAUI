package iaui.ia.geniticalgorithm;

import iaui.ia.model.Mouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Selector {

        private Mouse mouses[];
        List<Interval> intervals = new ArrayList<>();

        public Selector(Mouse mouses[]) {
                this.mouses = mouses;
        }

        public Mouse[] getTwoRandomMouses() {
                Mouse mousesResult[] = new Mouse[2];
                double lastInterval = 0;
                for (Mouse mouse : mouses) {
                        double fitnessRatio = mouse.calculateFitnessRatio() * 100;
                        intervals.add(new Interval(lastInterval, fitnessRatio + lastInterval, mouse));
                        lastInterval = fitnessRatio + lastInterval;
                }
                int random1 = (int) Math.floor(Math.random() * lastInterval);
                mousesResult[0] = getMouseFromIntervals(random1);
                int random2 = (int) Math.floor(Math.random() * lastInterval);
                mousesResult[1] = getMouseFromIntervals(random2);

                System.out.println("Last Interval: "+ lastInterval+ " --- "+random1+" -- "+random2);
                displayIntervals();
                return mousesResult;
        }

        private void displayIntervals() {
                for (Interval interval:intervals) {
                        interval.display();
                }
        }

        private Mouse getMouseFromIntervals(int random1) {
                for (Interval interval : intervals) {
                        if (interval.contains(random1)) {
                                return interval.getMouse();
                        }
                }
                throw new RuntimeException();
        }

        private class Interval {
                private double initial;
                private double finale;
                private Mouse mouse;

                public Interval(double initial, double finale, Mouse mouse) {
                        this.initial = initial;
                        this.finale = finale;
                        this.mouse = mouse;
                }

                boolean contains(double i) {
                        return i <= finale && initial <= i;
                }

                public Mouse getMouse() {
                        return mouse;
                }

                public void display() {
                        System.out.println("["+initial+","+finale+"], "+mouse.calculateFitnessRatio());
                }
        }
}

