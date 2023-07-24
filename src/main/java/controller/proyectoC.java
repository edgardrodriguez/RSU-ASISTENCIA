/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.ProyectosImpl;
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
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ProyectosModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import service.Reporte;

/**
 *
 * @author edgard
 */
@Named(value = "proyectoC")
@SessionScoped
public class proyectoC implements Serializable {

    private UploadedFile archivo;
    private StreamedContent archivoTraido;
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
            dao.registrarProyectos(archivo, pro);
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

    public void modificarArchivo() throws Exception {
        try {
            dao.modificarArchivo(archivo, pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Actualizado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en actualizar archivo asistencia C {0}", e.getMessage());
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

    public void reporteProyecto() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "proyectos.jasper", fechSystem + " proyectos.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteTipo() throws Exception {

        try {
            if (pro.getReportTipo()== null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getReportTipo() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getReportTipo();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectosEstado.jasper", fechSystem + " proyectosTipo.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }
    public void reporteEstado() throws Exception {

        try {
            if (pro.getReportEstado()== null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getReportEstado() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getReportEstado();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectosEstadoPorceso.jasper", fechSystem + " proyectosEstado.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }
    public void reporteRevisado() throws Exception {

        try {
            if (pro.getReportRevisado()== null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getReportRevisado() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getReportRevisado();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectosRevisado.jasper", fechSystem + " proyectosRevisado.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
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

}
