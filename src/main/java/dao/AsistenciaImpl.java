/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.protobuf.TextFormat.ParseException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AsistenciaModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author edgard
 */
public class AsistenciaImpl extends Conexion implements ICRUD<AsistenciaModel> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException, java.text.ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }


    public void registrarAsistencia(UploadedFile archivo, AsistenciaModel obj) throws Exception {
        String sql = "INSERT INTO ASISTENCIA (dia,cantHoras,fecha,estado,evidencia,estudiantes_fk,proyecto_detalle_fk) VALUES (?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {

            //SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            //String fechSystem = dateFormat2.format(fechaActual);

            ps.setString(1, obj.getDia());
            ps.setInt(2, obj.getCantHoras());
            ps.setTimestamp(3, fechaActual);
            String est = "A";
            ps.setString(4, est);
            ps.setBinaryStream(5, archivo.getInputStream());
            ps.setInt(6, obj.getEstudiantes_fk());
            ps.setInt(7, obj.getProyecto_detalle_fk());
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
                doc.setDiaConcat(rs.getString("diaConcat"));
                doc.setCantHoras(rs.getInt("cantHoras"));
                doc.setFecha(rs.getTimestamp("fecha"));
                doc.setEstado(rs.getString("estado")); 
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

    public List<AsistenciaModel> ListarEstudiantes() throws SQLException {
        List<AsistenciaModel> listadoA = null;
        AsistenciaModel per;
        ResultSet rs;
        String sql = " select * from ESTUDIANTES WHERE rol_fk=2";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new AsistenciaModel();
                per.setIdEst(rs.getInt("id"));
                per.setNombreEst(rs.getString("nombre"));
                per.setApellidoEst(rs.getString("apellidos"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar ESTUDIANTES Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
    
    public List<AsistenciaModel> ListarProyectoDetalle(int estudiantes_fk) throws SQLException {
        List<AsistenciaModel> listadoA = null;
        AsistenciaModel per;
        ResultSet rs;
        String sql = "select id,descripcion,estudiantes_fk from PROYECTO_DETALLE where estudiantes_fk=?";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, estudiantes_fk);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new AsistenciaModel();
                per.setIdProDet(rs.getInt("id"));
                per.setDestProDet(rs.getString("descripcion"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar ESTUDIANTES Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
    public void modificarArchivo(UploadedFile archivo, AsistenciaModel obj) throws Exception {
        String sql = "update ASISTENCIA set evidencia=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setBinaryStream(1, archivo.getInputStream());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar archivo Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    public StreamedContent traerImagen(StreamedContent archivo, int id) {

        String sql = "select evidencia, fecha from ASISTENCIA WHERE id =?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet st = ps.executeQuery();
            while (st.next()) {
                InputStream stream = st.getBinaryStream("evidencia");
                String fecha = st.getString("fecha");
                archivo = DefaultStreamedContent.builder()
                        .name(fecha + ".jpg")
                        .contentType("image/jpg")
                        .stream(() -> stream)
                        .build();

                System.out.println("Estoy en while dao traer imagen, " + archivo);
            }
        } catch (Exception e) {
            System.out.println("Error en traer imagen: " + e.getMessage());
        }
        return archivo;
    }

    @Override
    public void registrar(AsistenciaModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
