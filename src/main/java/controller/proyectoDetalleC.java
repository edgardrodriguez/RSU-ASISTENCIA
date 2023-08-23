/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.ProyectoDetalleImpl;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.ProyectoDetalleModel;
import service.Reporte;

/**
 *
 * @author edgard
 */
@SessionScoped
@Named(value = "proyectoDetalleC")
public class proyectoDetalleC implements Serializable {

    private ProyectoDetalleModel prod;
    private ProyectoDetalleImpl dao;
    private List<ProyectoDetalleModel> listprod;
    private List<ProyectoDetalleModel> listproyectos;
    private List<ProyectoDetalleModel> listEstudiante;
    private List<ProyectoDetalleModel> listDetalle;
    private List<ProyectoDetalleModel> listProyectFilter;
    private List<ProyectoDetalleModel> listProyectFecha;

    public proyectoDetalleC() {
        prod = new ProyectoDetalleModel();
        dao = new ProyectoDetalleImpl();
        listDetalle = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            dao.registrarProyectos(prod);
            limpiar();
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

    public void reporteProyectoDetalle() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "proyectoDetalle.jasper", fechSystem + " proyectosDetalle.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteProyecto() throws Exception {

        try {
            if (prod.getProyectos_fk() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (prod.getProyectos_fk() >= 0) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                int sts = prod.getProyectos_fk();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "proyectoDetalleFiltroPro.jasper", fechSystem + " proyectoDetalleFiltroPro.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteEstudiante() throws Exception {

        try {
            if (prod.getEstudiantes_fk() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (prod.getEstudiantes_fk() >= 0) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                int sts = prod.getEstudiantes_fk();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "proyectoDetalleFiltroEst.jasper", fechSystem + " proyectoDetalleFiltroEst.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteEstado() throws Exception {

        try {
            if (prod.getEstado() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (prod.getEstado() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = prod.getEstado();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "proyectoDetalleFiltroEstado.jasper", fechSystem + " proyectoDetalleFiltroEstado.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteFecha() throws Exception {

        try {
            if (prod.getFechaNew() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (prod.getFechaNew() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = prod.getFechaNew();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "proyectoDetalleFiltroFecha.jasper", fechSystem + " proyectoDetalleFiltroFecha.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
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
            listproyectos = dao.ListarPro(prod.getEstudiantes_fk());
            System.out.println("ID " + prod.toString());

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

    public List<ProyectoDetalleModel> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<ProyectoDetalleModel> listDetalle) {
        this.listDetalle = listDetalle;
    }

    public List<ProyectoDetalleModel> getListProyectFilter() {
        listProyectFilter = new ArrayList<ProyectoDetalleModel>();
        try {
            listProyectFilter = dao.ListarProyectos();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectFilter;
    }

    public void setListProyectFilter(List<ProyectoDetalleModel> listProyectFilter) {
        this.listProyectFilter = listProyectFilter;
    }

    public List<ProyectoDetalleModel> getListProyectFecha() {
        listProyectFecha = new ArrayList<ProyectoDetalleModel>();
        try {
            listProyectFecha = dao.listarFecha();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectFecha;
    }

    public void setListProyectFecha(List<ProyectoDetalleModel> listProyectFecha) {
        this.listProyectFecha = listProyectFecha;
    }

}
