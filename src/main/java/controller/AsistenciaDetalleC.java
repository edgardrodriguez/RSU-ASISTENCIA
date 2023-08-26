/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AsistenciaDetalleImpl;
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
import model.AsistenciaDetalleModel;
import service.Reporte;

/**
 *
 * @author edgard
 */
@Named(value = "asistenciaDetalleC")
@SessionScoped
public class AsistenciaDetalleC implements Serializable {

    private AsistenciaDetalleModel asis;
    private AsistenciaDetalleImpl dao;
    private List<AsistenciaDetalleModel> listProyecto;

    public AsistenciaDetalleC() {
        asis = new AsistenciaDetalleModel();
        dao = new AsistenciaDetalleImpl();
    }

    public void obtenerCantHoras() throws Exception {

        try {
            if (asis.getProyecto_fk() > 0) {
                asis.setCantHorasSum(dao.obtenerHoraAsistencia(asis.getProyecto_fk()));
            }

        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en obtener asistencia C {0}", e.getMessage());
        }

    }

    public void reporteAsistencia() throws Exception {

        try {
            if (asis.getProyecto_fk() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar"));
            }
            if (asis.getProyecto_fk() >= 0) {
                if (asis.getCantHorasSum() > 60) {
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date fechaActual = new Date(System.currentTimeMillis());
                    String fechSystem = dateFormat2.format(fechaActual);
                    int sts = asis.getProyecto_fk();
                    Reporte report = new Reporte();

                    Map<String, Object> parameters = new HashMap();
                    parameters.put("Parameter1", sts);
                    report.exportarPDFGlobal(parameters, "asistenciaReporte.jasper", fechSystem + " asistenciaReporte.pdf");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FALTAN HORAS", null));
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public AsistenciaDetalleImpl getDao() {
        return dao;
    }

    public void setDao(AsistenciaDetalleImpl dao) {
        this.dao = dao;
    }

    public AsistenciaDetalleModel getAsis() {
        return asis;
    }

    public void setAsis(AsistenciaDetalleModel asis) {
        this.asis = asis;
    }

    public List<AsistenciaDetalleModel> getListProyecto() {
        listProyecto = new ArrayList<AsistenciaDetalleModel>();
        try {
            listProyecto = dao.ListarProyectos();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyecto;
    }

    public void setListProyecto(List<AsistenciaDetalleModel> listProyecto) {
        this.listProyecto = listProyecto;
    }

}
