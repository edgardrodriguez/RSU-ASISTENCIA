/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AdminImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.AdminModel;

/**
 *
 * @author edgard
 */
@Named(value = "adminC")
@SessionScoped
public class adminC implements Serializable {

    private AdminModel adm;
    private AdminImpl dao;
    private List<AdminModel> listAdm;

    public adminC() {
        adm = new AdminModel();
        dao = new AdminImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(adm);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en registrar admin C {0}", e.getMessage());
        }
    }
    public void modificar() throws Exception {
        try {
            dao.modificar(adm);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificar admin C {0}", e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(adm);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar admin C {0}", e.getMessage());
        }
    }

    public void limpiar() {
        adm = new AdminModel();
    }

   
    public void listar() {
        try {
            listAdm = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar admin C {0}", e.getMessage());
        }
    }
    
    public AdminModel getAdm() {
        return adm;
    }

    public void setAdm(AdminModel adm) {
        this.adm = adm;
    }

    public AdminImpl getDao() {
        return dao;
    }

    public void setDao(AdminImpl dao) {
        this.dao = dao;
    }

    public List<AdminModel> getListAdm() {
        return listAdm;
    }

    public void setListAdm(List<AdminModel> listAdm) {
        this.listAdm = listAdm;
    }

}
