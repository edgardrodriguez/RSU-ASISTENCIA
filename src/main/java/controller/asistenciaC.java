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
import model.AsistenciaModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import service.Reporte;

/**
 *
 * @author edgard
 */
@Named(value = "asistenciaC")
@SessionScoped
public class asistenciaC implements Serializable {

    private UploadedFile archivo;
    private StreamedContent archivoTraido;
    private AsistenciaModel asis;
    private AsistenciaImpl dao;
    private List<AsistenciaModel> listAsistencia;
    private List<AsistenciaModel> listProyecto;
    private List<AsistenciaModel> listProyectFecha;
    public asistenciaC() {
        asis = new AsistenciaModel();
        dao = new AsistenciaImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.registrarAsistencia(archivo, asis);
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

    public void modificarArchivo() throws Exception {
        try {
            dao.modificarArchivo(archivo, asis);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Actualizado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en actualizar archivo asistencia C {0}", e.getMessage());
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

    public void decargar(int id) throws SQLException {
        try {
            System.out.println("El idemp es esto " + id);
            archivoTraido = dao.traerImagen(archivoTraido, id);
            System.out.println("Mi archivo traido : " + archivoTraido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descargado", "Descarga completada"));
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
        }
    }

    public void reporteAsistencia() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "RegistroAsistencia.jasper", fechSystem + " RegistroAsistencia.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteEstado() throws Exception {

        try {
            if (asis.getEstado()== null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (asis.getEstado() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = asis.getEstado();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "RegistroAsistenciaEstado.jasper", fechSystem + " RegistroAsistenciaEstado.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }
    public void reporteFecha() throws Exception {

        try {
            if (asis.getFechaNew() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (asis.getFechaNew() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = asis.getFechaNew();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "RegistroAsistenciaFecha.jasper", fechSystem + " RegistroAsistenciaFecha.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
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

    public List<AsistenciaModel> getListProyecto() {
        listProyecto = new ArrayList<AsistenciaModel>();
        try {
            listProyecto = dao.ListarProyectos();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyecto;
    }

    public void setListProyecto(List<AsistenciaModel> listProyecto) {
        this.listProyecto = listProyecto;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public StreamedContent getArchivoTraido() {
        return archivoTraido;
    }

    public void setArchivoTraido(StreamedContent archivoTraido) {
        this.archivoTraido = archivoTraido;
    }

    public List<AsistenciaModel> getListProyectFecha() {
        listProyectFecha = new ArrayList<AsistenciaModel>();
        try {
            listProyectFecha = dao.listarFecha();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectFecha;
    }

    public void setListProyectFecha(List<AsistenciaModel> listProyectFecha) {
        this.listProyectFecha = listProyectFecha;
    }

}
