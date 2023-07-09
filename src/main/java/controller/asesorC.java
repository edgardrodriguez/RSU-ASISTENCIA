/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AsesorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.AsesorModel;
import service.Reporte;

/**
 *
 * @author edgard
 */
@Named(value = "asesorC")
@SessionScoped
public class asesorC implements Serializable {

    private AsesorModel ase;
    private AsesorImpl dao;
    private List<AsesorModel> listAse;

    public asesorC() {
        ase = new AsesorModel();
        dao = new AsesorImpl();
    }

    public void registrar() throws Exception {
        try {
            System.out.println("registrado" +ase.getApellidos());
            dao.registrar(ase);
            System.out.println("registrado" +ase.getApellidos());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
            System.out.println("registrado" +ase.getApellidos());
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, "error== {0}",e.getErrorCode());
            if (e.getErrorCode() == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "el DNI ingresado coincide con otro usuario existente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error fatal"));
            }
            Logger.getGlobal().log(Level.INFO, "Error en registrar docente C {0}", e.getMessage());
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en registrar docente C {0}", e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(ase);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificar docente C {0}", e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(ase);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar docente C {0}", e.getMessage());
        }
    }

    public void limpiar() {
        ase = new AsesorModel();
    }

   
    public void listar() {
        try {
            listAse = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar docente C {0}", e.getMessage());
        }
    }
    public void reporteAsesor() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Asesor.jasper", fechSystem + " asesor.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public AsesorModel getDoc() {
        return ase;
    }
  
    public void setListDoc(List<AsesorModel> listDoc) {
        this.listAse = listDoc;
    }

    public AsesorModel getAse() {
        return ase;
    }

    public void setAse(AsesorModel ase) {
        this.ase = ase;
    }

    public AsesorImpl getDao() {
        return dao;
    }

    public void setDao(AsesorImpl dao) {
        this.dao = dao;
    }

    public List<AsesorModel> getListAse() {
        try {
            this.listAse = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(asesorC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(asesorC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAse;
    }

    public void setListAse(List<AsesorModel> listAse) {
        this.listAse = listAse;
    }

   

}
