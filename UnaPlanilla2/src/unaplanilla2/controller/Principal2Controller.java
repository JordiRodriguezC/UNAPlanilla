/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import unaplanilla2.util.FlowController;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Principal2Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private JFXButton btnEmpleados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ingresarEmpleados(ActionEvent event) {
        FlowController.getInstance().goView("Empleados");
    }
    

    @FXML
    private void TipoPlanilla(ActionEvent event) {
         FlowController.getInstance().goView("TipoPlanillas");
    }
}
