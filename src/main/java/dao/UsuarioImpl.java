/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.UsuarioModel;

/**
 *
 * @author edgard
 */
public class UsuarioImpl extends Conexion {

    public static Boolean logueo = false;
    public static String nivel = "";
    public static Boolean validar = false;
    public static Boolean validarCorreo=false;

    public UsuarioModel ingresoLogin(String email, String password) throws Exception {
        UsuarioModel ingreso = new UsuarioModel();
        String sql = "select email,password,estado,rol_fk,id from ADMIN where email=? and password=?";
        ResultSet rs;
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                ingreso.setEmail(rs.getString("email"));
                ingreso.setPassword(rs.getString("password"));
                ingreso.setEstado(rs.getString("estado"));
                ingreso.setRol_fk(rs.getInt("rol_fk"));
                ingreso.setId(rs.getInt("id"));
                logueo = true;
            } else {
                logueo = false;
            }
        }
        return ingreso;
    }
    public UsuarioModel validacionUsuario(String DNI) throws Exception {
        UsuarioModel ingreso = new UsuarioModel();
        String sql = "select email,password,estado,rol_fk,id from ADMIN where email=?";
        ResultSet rs;
        try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, DNI);
            rs = ps.executeQuery();
            if (rs.next()) {
                ingreso.setEmail(rs.getString("email"));
                ingreso.setPassword(rs.getString("password"));
                ingreso.setEstado(rs.getString("estado"));
                ingreso.setRol_fk(rs.getInt("rol_fk"));
                ingreso.setId(rs.getInt("id"));
                validarCorreo=true;
            }else{
                validarCorreo=false;
            }
        }
        return ingreso;
    }

    public void modificar(UsuarioModel user) throws Exception {
        String sql = "update ADMIN set password=? where email=?";

        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            int rstp = ps.executeUpdate();
            if (rstp > 0) {
                validar = true;
            } else {
                validar = false;
            }
        }
    }
}
