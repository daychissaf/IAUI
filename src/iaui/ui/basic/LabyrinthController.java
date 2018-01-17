package iaui.ui.basic;

import iaui.ia.geniticalgorithm.IAProcessor;
import iaui.ui.LabyrinthUi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LabyrinthController implements Initializable {

    @FXML
    private GridPane apMain;

    private IAProcessor iaProcessor;

    public LabyrinthController() {
        iaProcessor=new IAProcessor();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabyrinthUi labyrinthUi = new LabyrinthUi(iaProcessor.getLabyrinth());
        apMain.getChildren().add(labyrinthUi.getShape());
        iaProcessor.setDaemon(true);
        iaProcessor.start();
    }
}
