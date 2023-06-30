/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.ProyectoDetalleImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ProyectoDetalleModel;
import org.primefaces.model.file.CommonsUploadedFile;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author edgard
 */
@Named(value = "proyectoDetalleC")
@SessionScoped
public class proyectoDetalleC implements Serializable {

    private UploadedFile archivo;
    private ProyectoDetalleModel prod;
    private ProyectoDetalleImpl dao;
    private List<ProyectoDetalleModel> listprod;
    private List<ProyectoDetalleModel> listproyectos;
    private List<ProyectoDetalleModel> listEstudiante;

    public proyectoDetalleC() {
        archivo = new CommonsUploadedFile();
        prod = new ProyectoDetalleModel();
        dao = new ProyectoDetalleImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(archivo, prod);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en registrar proyecto Detalle C {0}", e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(prod);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Actualizado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en actualizar proyecto Detalle C {0}", e.getMessage());
        }
    }
    public void modificarArchivo() throws Exception {
        try {
            dao.modificarArchivo(archivo,prod);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Actualizado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en actualizar proyecto Detalle C {0}", e.getMessage());
        }
    }
    public void eliminar() throws Exception {
        try {
            dao.eliminar(prod);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar proyecto detalle C {0}", e.getMessage());
        }
    }

    public void limpiar() {
        prod = new ProyectoDetalleModel();
    }

    public void listar() {
        try {
            listprod = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar proyecto detalle C {0}", e.getMessage());
        }
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public ProyectoDetalleModel getProd() {
        return prod;
    }

    public void setProd(ProyectoDetalleModel prod) {
        this.prod = prod;
    }

    public ProyectoDetalleImpl getDao() {
        return dao;
    }

    public void setDao(ProyectoDetalleImpl dao) {
        this.dao = dao;
    }

    public List<ProyectoDetalleModel> getListprod() {
        return listprod;
    }

    public void setListprod(List<ProyectoDetalleModel> listprod) {
        this.listprod = listprod;
    }

    public List<ProyectoDetalleModel> getListproyectos() {
        listproyectos = new ArrayList<ProyectoDetalleModel>();
        try {
            listproyectos = dao.ListarProyectos();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listproyectos;
    }

    public void setListproyectos(List<ProyectoDetalleModel> listproyectos) {
        this.listproyectos = listproyectos;
    }

    public List<ProyectoDetalleModel> getListEstudiante() {
        listEstudiante = new ArrayList<ProyectoDetalleModel>();
        try {
            listEstudiante = dao.ListarEstudiantes();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEstudiante;
    }

    public void setListEstudiante(List<ProyectoDetalleModel> listEstudiante) {
        this.listEstudiante = listEstudiante;
    }

}
