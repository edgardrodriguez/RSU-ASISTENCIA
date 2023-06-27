/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.ProyectosImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ProyectosModel;

/**
 *
 * @author edgard
 */
@Named(value = "proyectoC")
@SessionScoped
public class proyectoC implements Serializable {

    private ProyectosModel pro;
    private ProyectosImpl dao;
    private List<ProyectosModel> listProyecto;
    private List<ProyectosModel> listEstudiantes;
    private List<ProyectosModel> listAsesor;

    public proyectoC() {
        pro = new ProyectosModel();
        dao = new ProyectosImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, "error== {0}", e.getErrorCode());
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
            dao.modificar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificar docente C {0}", e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar docente C {0}", e.getMessage());
        }
    }

    public void limpiar() {
        pro = new ProyectosModel();
    }

    public void listar() {
        try {
            listProyecto = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar docente C {0}", e.getMessage());
        }
    }

    public ProyectosModel getPro() {
        return pro;
    }

    public void setPro(ProyectosModel pro) {
        this.pro = pro;
    }

    public ProyectosImpl getDao() {
        return dao;
    }

    public void setDao(ProyectosImpl dao) {
        this.dao = dao;
    }

    public List<ProyectosModel> getListProyecto() {
        return listProyecto;
    }

    public void setListProyecto(List<ProyectosModel> listProyecto) {
        this.listProyecto = listProyecto;
    }

    public List<ProyectosModel> getListEstudiantes() {
        listEstudiantes = new ArrayList<ProyectosModel>();
        try {
            listEstudiantes = dao.ListarEstudiantes();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEstudiantes;
    }

    public void setListEstudiantes(List<ProyectosModel> listEstudiantes) {
        this.listEstudiantes = listEstudiantes;
    }

    public List<ProyectosModel> getListAsesor() {
        listAsesor = new ArrayList<ProyectosModel>();
        try {
            listAsesor = dao.ListarAsesor();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAsesor;
    }

    public void setListAsesor(List<ProyectosModel> listAsesor) {
        this.listAsesor = listAsesor;
    }

}
