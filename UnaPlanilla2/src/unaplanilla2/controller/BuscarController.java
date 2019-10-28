/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unaplanilla2.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import unaplanilla2.model.EmpleadoDto;
import unaplanilla2.service.EmpleadoService;
import unaplanilla2.util.AppContext;
import unaplanilla2.util.FlowController;
import unaplanilla2.util.Mensaje;
import unaplanilla2.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author JORDI RODRIGUEZ
 */
public class BuscarController extends Controller {

    @FXML
    private JFXTextField txtCedula;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private TableView<EmpleadoDto> Table_Buscar;
    @FXML
    private TableColumn<EmpleadoDto, String> Col_Cedula;
    @FXML
    private TableColumn<EmpleadoDto, String> Col_Nombre;
    @FXML
    private TableColumn<EmpleadoDto, String> Col_Apellido;
    private ObservableList items;
    private ArrayList<EmpleadoDto> empleados;
    private Respuesta resp;
    private EmpleadoService empService;
    private Mensaje ms;
    private String ced, nom, apellido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize() {
        /*  empService = new EmpleadoService();
        resp = empService.getEmpleados();
        empleados = ((ArrayList<EmpleadoDto>) resp.getResultado("Empleados"));
         Col_Nombre.setCellValueFactory(value ->new SimpleStringProperty(value.getValue().getEmpNombre()));
        Col_Apellido.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getEmpPapellido()));
        Col_Cedula.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getEmpCedula()));
        items = FXCollections.observableArrayList(empleados);
        Table_Buscar.setItems(items);*/
        Col_Nombre.setCellValueFactory(value -> value.getValue().empNombre);
        Col_Apellido.setCellValueFactory(value -> value.getValue().empPapellido);
        Col_Cedula.setCellValueFactory(value -> value.getValue().empCedula);
    }

    @FXML
    private void Filtrar(ActionEvent event) {
        ced = (!txtCedula.getText().isEmpty()) ? "%" + txtCedula.getText().toUpperCase() + "%" : "%";
        nom = (!txtNombre.getText().isEmpty()) ? "%" + txtNombre.getText().toUpperCase() + "%" : "%";
        apellido = (!txtApellido.getText().isEmpty()) ? "%" + txtApellido.getText().toUpperCase() + "%" : "%";

        empService = new EmpleadoService();

        resp = empService.getEmpleados(ced, nom, apellido);
        if (resp.getEstado()) {
            empleados = (ArrayList<EmpleadoDto>) resp.getResultado("Empleados");
            items = FXCollections.observableArrayList(empleados);
            Table_Buscar.setItems(items);
        } else {

            System.out.println(resp.getMensaje());
        }
    }

    private void MostrarDatos(KeyEvent event) {

    }

    @FXML
    private void MostrarDatos(MouseEvent event) {
        if (Table_Buscar.getSelectionModel() != null && Table_Buscar.getSelectionModel().getSelectedItem() != null) {
            AppContext.getInstance().set("Empleado", Table_Buscar.getSelectionModel().getSelectedItem());
            FlowController.getInstance().initialize();

            FlowController.getInstance().goView("Empleados");
            this.getStage().close();
        }
    }

}
