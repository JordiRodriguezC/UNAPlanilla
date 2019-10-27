/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unaplanilla2;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import unaplanilla2.util.FlowController;

/**
 *
 * @author Carlos
 */
public class UnaPlanilla2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FlowController.getInstance().InitializeFlow(stage,null);
        stage.getIcons().add(new Image("unaplanilla2/resources/Agregar-48.png"));
        stage.setTitle("UNA PLANILLA");
        FlowController.getInstance().goViewInWindow("LogIng2");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
