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
import javax.el.PropertyNotFoundException;
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
    private UploadedFile archivo2;
    private StreamedContent archivoTraido2;
    private ProyectosModel pro;
    private ProyectosImpl dao;
    private List<ProyectosModel> listProyecto;
    private List<ProyectosModel> listEstudiantes;
    private List<ProyectosModel> listAsesor;
    private List<ProyectosModel> listProyectFecha;
    private boolean enabled = true;

    public proyectoC() {
        pro = new ProyectosModel();
        dao = new ProyectosImpl();
    }

    public void registrar() throws Exception,SQLException {
        try {
            dao.registrarProyectos(archivo2, pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (PropertyNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
            
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
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
        }
    }

    public void modificarArchivo2() throws Exception {
        try {
            dao.modificarArchivo2(archivo2, pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Actualizado con éxito"));
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
        }
    }

    public void decargar(int id) throws SQLException {
        try {
            archivoTraido = dao.traerImagen(archivoTraido, id);
            System.out.println("Mi archivo traido : " + archivoTraido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descargado", "Descarga completada"));

        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
        }
    }

    public void decarga2(int id) throws SQLException {
        try {
            archivoTraido2 = dao.traerImagen2(archivoTraido2, id);
            System.out.println("Mi archivo traido : " + archivoTraido2);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descargado", "Descarga completada"));
            System.out.println("Mi archivo traido : " + archivoTraido2);

        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "NO HAY ARCHIVO"));
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
            if (pro.getReportTipo() == null) {
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
            if (pro.getReportEstado() == null) {
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
            if (pro.getReportRevisado() == null) {
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

    public void reporteFecha() throws Exception {

        try {
            if (pro.getFechaNew() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getFechaNew() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getFechaNew();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectoFecha.jasper", fechSystem + " proyectoFecha.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteOds() throws Exception {

        try {
            if (pro.getOds() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getOds() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getOds();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectoOds.jasper", fechSystem + " proyectoOds.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteFacultad() throws Exception {

        try {
            if (pro.getFacultad() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getFacultad() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getFacultad();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectoFacultad.jasper", fechSystem + " proyectoFacultad.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteEscuelaProfesional() throws Exception {

        try {
            if (pro.getEscuelaProfesional() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (pro.getEscuelaProfesional() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = pro.getEscuelaProfesional();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parametro1", sts);
                report.exportarPDFGlobal(parameters, "proyectoEscuelaProfesional.jasper", fechSystem + " proyectoEscuelaProfesional.pdf");
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

    public UploadedFile getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(UploadedFile archivo2) {
        this.archivo2 = archivo2;
    }

    public StreamedContent getArchivoTraido2() {
        return archivoTraido2;
    }

    public void setArchivoTraido2(StreamedContent archivoTraido2) {
        this.archivoTraido2 = archivoTraido2;
    }

    public List<ProyectosModel> getListProyectFecha() {
        listProyectFecha = new ArrayList<ProyectosModel>();
        try {
            listProyectFecha = dao.listarFecha();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectFecha;
    }

    public void setListProyectFecha(List<ProyectosModel> listProyectFecha) {
        this.listProyectFecha = listProyectFecha;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
