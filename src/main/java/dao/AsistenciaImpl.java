/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.protobuf.TextFormat.ParseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AsistenciaModel;

/**
 *
 * @author edgard
 */
public class AsistenciaImpl extends Conexion implements ICRUD<AsistenciaModel> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException, java.text.ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void registrar(AsistenciaModel obj) throws Exception {
        String sql = "INSERT INTO ASISTENCIA (dia,cantHoras,fecha,estado,estudiantes_fk,proyecto_detalle_fk) VALUES (?,?,?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {

            //SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            //String fechSystem = dateFormat2.format(fechaActual);

            ps.setString(1, obj.getDia());
            ps.setInt(2, obj.getCantHoras());
            ps.setTimestamp(3, fechaActual);
            ps.setString(4, obj.getEstado());
            ps.setInt(5, obj.getEstudiantes_fk());
            ps.setInt(6, obj.getProyecto_detalle_fk());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar ASISTENCIA Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(AsistenciaModel obj) throws Exception {
        String sql = "update ASISTENCIA set dia=?,cantHoras=?,fecha=?,estado=?,estudiantes_fk=?,proyecto_detalle_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            //SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            //String fechSystem = dateFormat2.format(fechaActual);

            ps.setString(1, obj.getDia());
            ps.setInt(2, obj.getCantHoras());
            ps.setTimestamp(3, fechaActual);
            ps.setString(4, obj.getEstado());
            ps.setInt(5, obj.getEstudiantes_fk());
            ps.setInt(6, obj.getProyecto_detalle_fk());
            ps.setInt(7, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar ASISTENCIA Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(AsistenciaModel obj) throws Exception {
        String sql = "delete from ASISTENCIA where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar ASISTENCIA Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<AsistenciaModel> listarTodos() throws Exception {
        List<AsistenciaModel> listado = null;
        AsistenciaModel doc;
        ResultSet rs;
        String sql = "select * from  V_ASISTENCIA";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doc = new AsistenciaModel();
                doc.setFila(rs.getString("fila"));
                doc.setId(rs.getInt("id"));
                doc.setDia(rs.getString("dia"));
                doc.setCantHoras(rs.getInt("cantHoras"));
                doc.setFecha(rs.getTimestamp("fecha")); 
                doc.setEstudiantes_fk(rs.getInt("estudiantes_fk"));
                doc.setProyecto_detalle_fk(rs.getInt("proyecto_detalle_fk"));
                doc.setConcatEst(rs.getString("concatEst")); 
                doc.setDescripcion(rs.getString("descripcion"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar ASISTENCIA Dao {0} ", e.getMessage());
        }
        return listado;
    }

}
