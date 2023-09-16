/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.UsuarioImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.UsuarioModel;

/**
 *
 * @author edgard
 */
@Named(value = "usuarioC")
@SessionScoped
public class usuarioC implements Serializable {

    public static int getGlobal() {
        return global;
    }

    public static void setGlobal(int aGlobal) {
        global = aGlobal;
    }

    private UsuarioImpl dao;
    private UsuarioModel usuarrio;
    private static int global;

    public usuarioC() {
        usuarrio = new UsuarioModel();
        dao = new UsuarioImpl();
        global = usuarrio.getId();
    }

    public void ingres() throws Exception {
        try {
            usuarrio = dao.ingresoLogin(usuarrio.getEmail(), usuarrio.getPassword());

            System.out.println(usuarrio.getEmail());
            System.out.println(usuarrio.getPassword());
            System.out.println(usuarrio.getEstado());
            System.out.println(usuarrio.getRol_fk());

        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en login_C {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    public void acceso() {
        try {
            this.ingres();
            if (dao.logueo == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
            } else if (dao.logueo == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario", usuarrio);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/RSU-ASISTENCIA/faces/menuContenido.xhtml");
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en acceso_C {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(usuarrio);
            usuarrio = dao.validacionUsuario(usuarrio.getEmail());
            if (dao.validar.equals(true)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "La contraseña ha sido modificada con éxito"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Ingrese correctamente los datos"));
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en cambiar contraseña {0}", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR ", "error en cambiar contraseña"));
        }
    }

    public void limpiar() {
        usuarrio = new UsuarioModel();
    }

    public void cerrarSesion() throws IOException {
        limpiar();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/RSU-ASISTENCIA/");
    }

    // Obtener el objeto de la sesión activa
    public static UsuarioModel obtenerObjetoSesion() {
        return (UsuarioModel) FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("objetoUsuario");
    }
    // Si la sesión no está iniciada no permitirá entrar a otra vista de la aplicación

    public void seguridadSesion() throws IOException {
        if (obtenerObjetoSesion() == null) {
            limpiar();
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("/RSU-ASISTENCIA/");
        }
    }

    //si inicio sesion como usuario administrador no permitira que ingrese a otra vista que no sea del administrador
    public void seguradUsuarioAdmin() throws IOException {
        UsuarioModel us = obtenerObjetoSesion();
        if (us != null && us.getRol_fk() == 2) {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("/RSU-ASISTENCIA/faces/menuContenido.xhtml");
        }
    }

    // Si la sesión está activa se redirecciona a la vista principal
    public void seguridadLogin() throws IOException {
        UsuarioModel us = obtenerObjetoSesion();
        if (us != null) {
            if (us.getRol_fk() == 2) {
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("/RSU-ASISTENCIA/faces/menuContenido.xhtml");
            }
        }
    }

    public UsuarioImpl getDao() {
        return dao;
    }

    public void setDao(UsuarioImpl dao) {
        this.dao = dao;
    }

    public UsuarioModel getUsuarrio() {
        return usuarrio;
    }

    public void setUsuarrio(UsuarioModel usuarrio) {
        this.usuarrio = usuarrio;
    }

}
