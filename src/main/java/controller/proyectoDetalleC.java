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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.ProyectoDetalleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.CommonsUploadedFile;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

/**
 *
 * @author edgard
 */
@SessionScoped
@Named(value = "proyectoDetalleC")
public class proyectoDetalleC implements Serializable {

    private UploadedFile archivo;
    private ProyectoDetalleModel prod;
    private ProyectoDetalleImpl dao;
    private StreamedContent archivoTraido;
    private List<ProyectoDetalleModel> listprod;
    private List<ProyectoDetalleModel> listproyectos;
    private List<ProyectoDetalleModel> listEstudiante;

    public proyectoDetalleC() {
        archivo = new CommonsUploadedFile();
        archivoTraido = new DefaultStreamedContent();
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
            dao.modificarArchivo(archivo, prod);
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

    public void decargar(int id) {
        try {
            System.out.println("El idemp es esto " + id);
            archivoTraido = dao.traerImagen(archivoTraido, id);
            System.out.println("Mi archivo traido : " + archivoTraido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descargado", "Descarga completada"));
        } catch (Exception e) {
            System.out.println("Error en Descargar: " + e.getMessage());
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

    public StreamedContent getArchivoTraido() {
        return archivoTraido;
    }

    public void setArchivoTraido(StreamedContent archivoTraido) {
        this.archivoTraido = archivoTraido;
    }

}
