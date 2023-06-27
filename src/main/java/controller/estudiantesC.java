/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.EstudiantesImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.EstudianteModel;

/**
 *
 * @author edgard
 */
@Named(value = "estudiantesC")
@SessionScoped
public class estudiantesC implements Serializable {

    private EstudianteModel est;
    private EstudiantesImpl dao;
    private List<EstudianteModel> listEst;
    private List<EstudianteModel> listEstN;
    private List<EstudianteModel> listCarreras;
    public estudiantesC() {
        est = new EstudianteModel();
        dao = new EstudiantesImpl();
    }
    
    public void registrar() throws Exception {
        try {
            System.out.println("registrado" +est.getApellidos());
            dao.registrar(est);
            System.out.println("registrado" +est.getApellidos());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
            System.out.println("registrado" +est.getApellidos());
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, "error== {0}",e.getErrorCode());
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
            dao.modificar(est);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificar docente C {0}", e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(est);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar docente C {0}", e.getMessage());
        }
    }

    public void limpiar() {
        est = new EstudianteModel();
    }

   
    public void listar() {
        try {
            listEst = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar docente C {0}", e.getMessage());
        }
    }
    

    public EstudiantesImpl getDao() {
        return dao;
    }

    public void setDao(EstudiantesImpl dao) {
        this.dao = dao;
    }

    public List<EstudianteModel> getListEst() {
        return listEst;
    }

    public void setListEst(List<EstudianteModel> listEst) {
        this.listEst = listEst;
    }

    public EstudianteModel getEst() {
        return est;
    }

    public void setEst(EstudianteModel est) {
        this.est = est;
    }

    public List<EstudianteModel> getListEstN() {
         listEstN = new ArrayList<EstudianteModel>();
        if (est.getRol_fk()> 0 && est.getRol_fk()==2 && listEstN.isEmpty()) {
            try {
                listEstN = dao.ListarEstudiantes();
            } catch (Exception e) {
                Logger.getGlobal().log(Level.WARNING, "error en listar estudiantes controlador {0}",e.getMessage());
            }
        }
        return listEstN;
    }

    public void setListEstN(List<EstudianteModel> listEstN) {
        this.listEstN = listEstN;
    }

    public List<EstudianteModel> getListCarreras() {
        listCarreras = new ArrayList<EstudianteModel>();
        try {
            listCarreras = dao.ListarCarreras();
        } catch (SQLException ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(estudiantesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCarreras;
    }

    public void setListCarreras(List<EstudianteModel> listCarreras) {
        this.listCarreras = listCarreras;
    }

}
