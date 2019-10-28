/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unaplanilla2.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
//import unaplanilla2.model.Empleado;
import unaplanilla2.model.EmpleadoDto;
import unaplanilla2.model.TipoPlanillaDto;
import unaplanilla2.util.Request;
//import unaplanilla2.util.EntityManagerHelper;
import unaplanilla2.util.Respuesta;

/**
 *
 * @author Carlos
 */
public class TipoPlanillaService {
    //EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getTipoPlanilla(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("TipoPlanillaController/planilla", "/{id}", parametros);
            request.get();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            EmpleadoDto empleado = (EmpleadoDto) request.readEntity(EmpleadoDto.class);
            return new Respuesta(true, "", "", "Planilla", empleado);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo la planilla [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el empleado.", "getPlanilla " + ex.getMessage());
        }
    }

    
        public Respuesta guardarTipoPlanilla(TipoPlanillaDto tipoPlanillaDto) {
        try {
            Request request = new Request("TipoPlanillaController/guardar");
            request.post(tipoPlanillaDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            EmpleadoDto empleadoDto = (EmpleadoDto) request.readEntity(EmpleadoDto.class);
            return new Respuesta(true, "", "", "TipoPlanilla", empleadoDto);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error guardando la planilla.", ex);
            return new Respuesta(false, "Error guardando el Planilla.", "guardarPlanilla " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoPlanilla(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("TipoPlanillaController/planilla","/{id}",parametros);
            request.delete();

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error eliminando el tipo planilla.", ex);
            return new Respuesta(false, "Error eliminando el tipo Planilla.", "eliminarTipoPlanilla " + ex.getMessage());
        }
    }
    
}
