/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AsistenciaDetalleModel;

/**
 *
 * @author edgard
 */
public class AsistenciaDetalleImpl extends Conexion  {

   public int obtenerHoraAsistencia(int idProyecto) throws SQLException {
        String sql = "SELECT contar_asistencia(?) as contAsistencia from dual";
        ResultSet rs;
        int asistencia = -1;
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();
            if (rs.next()) {
                asistencia = rs.getInt("contAsistencia");
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al obtener contAsistencia cuot {0} ", e.getMessage());
        }
        return asistencia;
    }
    public List<AsistenciaDetalleModel> ListarProyectos() throws SQLException {
        List<AsistenciaDetalleModel> listadoA = null;
        AsistenciaDetalleModel per;
        ResultSet rs;
        String sql = "select id, concat(tipo,\"_\",id,\"_\",semestre)as proyect from PROYECTOS";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new AsistenciaDetalleModel();
                per.setIdPro(rs.getInt("id"));
                per.setCodigoProyecto(rs.getString("proyect"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar PROYECTOS Dao asistencia {0} ", e.getMessage());
        }
        return listadoA;
    }
}
