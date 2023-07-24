/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.DashboardImpl;
import javax.inject.Named;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

/**
 *
 * @author edgard
 */
@Named(value = "dashboardC")
@SessionScoped
public class DashboardC implements Serializable {
    private PieChartModel dashboardProyecto;
    private List<Number> listaProyecto;
    private DashboardImpl dao;
    
    public DashboardC() {
        dashboardProyecto = new PieChartModel();
        dao = new DashboardImpl();
        
    }
    private void dashboardProyecto() throws Exception {
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = listaProyecto;
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(113, 175, 220)");
        bgColors.add("rgb(227, 99, 91)");
        bgColors.add("rgb(152, 135, 34)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("PROYECCION_SOCIAL");
        labels.add("VOLUNTARIADO");
        labels.add("EXTENSION_UNIVERSITARIA");
        data.setLabels(labels);
        dashboardProyecto.setData(data);
    }
    @PostConstruct
    public void construir() {
        try {
            listaProyecto = dao.dashboardPersonal();
            dashboardProyecto();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en el dashboardCargoD {0}", e.getMessage());
        }
    }
    public PieChartModel getDashboardProyecto() {
        return dashboardProyecto;
    }

    public void setDashboardProyecto(PieChartModel dashboardProyecto) {
        this.dashboardProyecto = dashboardProyecto;
    }

    public List<Number> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(List<Number> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    public DashboardImpl getDao() {
        return dao;
    }

    public void setDao(DashboardImpl dao) {
        this.dao = dao;
    }
    
}
