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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import unaplanilla2.model.EmpleadoDto;
import unaplanilla2.model.TipoPlanillaDto;
import unaplanilla2.service.EmpleadoService;
import unaplanilla2.service.TipoPlanillaService;
import unaplanilla2.util.AppContext;
import unaplanilla2.util.FlowController;
import unaplanilla2.util.Mensaje;
import unaplanilla2.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jose Pablo Bermudez
 */
public class BuscarPlanillasController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXTextField txtPlanilla_Mes;
    @FXML
    private TableView<TipoPlanillaDto> Table_Buscar;
    @FXML
    private TableColumn<TipoPlanillaDto, String> Col_Codigo;
    @FXML
    private TableColumn<TipoPlanillaDto, String> Col_ID;
    @FXML
    private TableColumn<TipoPlanillaDto, String> Col_Planilla;
    private String id, planilla, codigo;
    private ObservableList items;
    private ArrayList<TipoPlanillaDto> planillas;
    private Respuesta resp;
    private TipoPlanillaService planillaService;
    private Mensaje ms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Col_Codigo.setCellValueFactory(value -> value.getValue().tplaCodigo);
        Col_Planilla.setCellValueFactory(value -> value.getValue().tplaPlaxmes);
        Col_ID.setCellValueFactory(value -> value.getValue().tplaId);

    }

    @FXML
    private void MostrarDatos(MouseEvent event) {

        if (Table_Buscar.getSelectionModel() != null && Table_Buscar.getSelectionModel().getSelectedItem() != null) {
            AppContext.getInstance().set("Planilla", Table_Buscar.getSelectionModel().getSelectedItem());
            FlowController.getInstance().initialize();

            FlowController.getInstance().goView("TipoPlanillas");

        }
    }

    @FXML
    private void Filtrar(ActionEvent event
    ) {
        id = (!txtID.getText().isEmpty()) ? "%" + txtID.getText().toUpperCase() + "%" : "%";
        planilla = (!txtPlanilla_Mes.getText().isEmpty()) ? "%" + txtPlanilla_Mes.getText().toUpperCase() + "%" : "%";
        codigo = (!txtCodigo.getText().isEmpty()) ? "%" + txtCodigo.getText().toUpperCase() + "%" : "%";

        planillaService = new TipoPlanillaService();

        resp = planillaService.getPlanillas(id, planilla, codigo);
        if (resp.getEstado()) {
            planillas = (ArrayList<TipoPlanillaDto>) resp.getResultado("Planillas");
            items = FXCollections.observableArrayList(planillas);
            Table_Buscar.setItems(items);
        } else {
            System.out.println(resp.getMensaje());
        }
    }

    @Override
    public void initialize() {
    }

}
