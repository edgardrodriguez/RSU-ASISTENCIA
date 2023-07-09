/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.AsistenciaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.AsistenciaModel;

/**
 *
 * @author edgard
 */
@Named(value = "asistenciaC")
@SessionScoped
public class asistenciaC implements Serializable {

    private AsistenciaModel asis;
    private AsistenciaImpl dao;
    private List<AsistenciaModel> listAsistencia;
    private List<AsistenciaModel> listEstudiante;
    private List<AsistenciaModel> listProyectosDetalle;
    public asistenciaC() {
        asis = new AsistenciaModel();
        dao = new AsistenciaImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(asis);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en registrar asistencia C {0}", e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(asis);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificar asistencia C {0}", e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(asis);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar asistencia C {0}", e.getMessage());
        }
    }

    public void limpiar() {
        asis = new AsistenciaModel();
    }

    public void listar() {
        try {
            listAsistencia = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar asistencia C {0}", e.getMessage());
        }
    }

    public AsistenciaModel getAsis() {
        return asis;
    }

    public void setAsis(AsistenciaModel asis) {
        this.asis = asis;
    }

    public AsistenciaImpl getDao() {
        return dao;
    }

    public void setDao(AsistenciaImpl dao) {
        this.dao = dao;
    }

    public List<AsistenciaModel> getListAsistencia() {
        return listAsistencia;
    }

    public void setListAsistencia(List<AsistenciaModel> listAsistencia) {
        this.listAsistencia = listAsistencia;
    }

    public List<AsistenciaModel> getListEstudiante() {
         listEstudiante = new ArrayList<AsistenciaModel>();
         try {
            listEstudiante=dao.ListarEstudiantes();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEstudiante;
    }

    public void setListEstudiante(List<AsistenciaModel> listEstudiante) {
        this.listEstudiante = listEstudiante;
    }

    public List<AsistenciaModel> getListProyectosDetalle() {
        listProyectosDetalle = new ArrayList<AsistenciaModel>();
        try {
            listProyectosDetalle=dao.ListarProyectoDetalle(asis.getEstudiantes_fk());
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectosDetalle;
    }

    public void setListProyectosDetalle(List<AsistenciaModel> listProyectosDetalle) {
        this.listProyectosDetalle = listProyectosDetalle;
    }

}
