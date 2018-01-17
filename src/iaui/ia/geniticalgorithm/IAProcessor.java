package iaui.ia.geniticalgorithm;

import iaui.ia.model.Labyrinth;
import iaui.ia.model.LabyrinthBuilder;
import iaui.ia.model.Mouse;

public class IAProcessor extends Thread {

    private Labyrinth labyrinth;

    public IAProcessor() {
        this.labyrinth = LabyrinthBuilder.build();
    }


    @Override
    public void run() {

        //build population
        Mouse oldGeneration[] = PopulationGenarator.generateMouses(labyrinth.getRoomsLength(), labyrinth.getInitialRoom(), labyrinth.getTargetRoom());
        Mouse newGenerationMouses[] = new Mouse[oldGeneration.length];

        int I=0;
        double maxRatio = rerieveMaxRatioFrom(labyrinth);
        do {

            //display
            if(I%1000==0){
                labyrinth.refreshUi();
                display(labyrinth, oldGeneration);
                System.out.println("maxRatio :"+maxRatio);

            }

            int newGenerationIndex = 0;

            for (int i = 0; i < oldGeneration.length / 2; i++) {
                //Selection
                Mouse selectedMouses[] = new Selector(oldGeneration).getTwoRandomMouses();
                // CrossOver
                Mouse crossedMouses[] = new CrossOver(selectedMouses).crossTwoMouses();

                newGenerationMouses[newGenerationIndex++] = crossedMouses[0];
                newGenerationMouses[newGenerationIndex++] = crossedMouses[1];
            }

            //Mutation
            for (Mouse mouse : newGenerationMouses) {
                new Mutateur().mutate(mouse);
            }
            tearDownGeneration(oldGeneration);
            oldGeneration = newGenerationMouses;
            newGenerationMouses = new Mouse[oldGeneration.length];
            if(I%1000==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            I++;
        } while (currentGenerationCanBeImproved(oldGeneration, maxRatio));
        System.out.println("Finished");
        while(true){
            labyrinth.refreshUi();
            display(labyrinth, oldGeneration);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void tearDownGeneration(Mouse[] mouses) {
        for (Mouse mouse : mouses) {
            mouse.kill();
        }
    }

    private static double rerieveMaxRatioFrom(Labyrinth labyrinth) {
        return labyrinth.getRoomsLength() - (labyrinth.getRoomsLength() * 0.1);
    }

    private static boolean currentGenerationCanBeImproved(Mouse[] currentGeneration, double maxRatio) {
        for (Mouse mouse : currentGeneration) {
            if (mouse.calculateFitnessRatio() < maxRatio) {
                return true;
            }
        }
        return false;
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    private static void display(Labyrinth labyrinth, Mouse[] mouses) {
        labyrinth.print();
        labyrinth.printMousePath(mouses);
    }
}
