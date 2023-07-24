/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProyectosModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author edgard
 */
public class ProyectosImpl extends Conexion implements ICRUD<ProyectosModel> {

    
    public void registrarProyectos(UploadedFile archivo, ProyectosModel obj) throws Exception {
        String sql = "INSERT INTO PROYECTOS (nombre,descripcion,tipo,estado,revisado,link,acta,asesor_fk,estudiantes_fk) VALUES (?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getTipo());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getRevisado());
            ps.setString(6, obj.getLink());
            ps.setBinaryStream(7, archivo.getInputStream());
            ps.setString(8, obj.getAsesor_fk());
            ps.setString(9, obj.getEstudiantes_fk());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar PROYECTOS Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(ProyectosModel obj) throws Exception {
        String sql = "update PROYECTOS set nombre=?,descripcion=?,tipo=?,estado=?,revisado=?,link=?,asesor_fk=?,estudiantes_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getTipo());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getRevisado());
            ps.setString(6, obj.getLink());
            ps.setString(7, obj.getAsesor_fk());
            ps.setString(8, obj.getEstudiantes_fk());
            ps.setInt(9, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar PROYECTOS Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ProyectosModel obj) throws Exception {
        String sql = "delete from PROYECTOS where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar PROYECTOS Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<ProyectosModel> listarTodos() throws Exception {
        List<ProyectosModel> listado = null;
        ProyectosModel doc;
        ResultSet rs;
        String sql = "select * from  V_PROYECTOS";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doc = new ProyectosModel();
                doc.setFila(rs.getString("fila"));
                doc.setId(rs.getInt("id"));
                doc.setNombre(rs.getString("nombre"));
                doc.setDescripcion(rs.getString("descripcion"));
                doc.setTipo(rs.getString("tipo"));
                doc.setEstado(rs.getString("estado"));
                doc.setRevisado(rs.getString("revisado"));
                doc.setLink(rs.getString("link"));
                doc.setAsesor_fk(rs.getString("asesor_fk"));
                doc.setEstudiantes_fk(rs.getString("estudiantes_fk"));
                doc.setTipoConcat(rs.getString("tipoConcat"));
                doc.setEstadoConcat(rs.getString("estadoConcat"));
                doc.setRevisadoConcat(rs.getString("revisadoConcat"));
                doc.setConcatAse(rs.getString("concatAse"));
                doc.setConcatEst(rs.getString("concatEst"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar PROYECTOS Dao {0} ", e.getMessage());
        }
        return listado;
    }

    public List<ProyectosModel> ListarAsesor() throws SQLException {
        List<ProyectosModel> listadoA = null;
        ProyectosModel per;
        ResultSet rs;
        String sql = "select * from ASESOR";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectosModel();
                per.setIdAse(rs.getInt("id"));
                per.setNombreAse(rs.getString("nombre"));
                per.setApellidoAse(rs.getString("apellidos")); 
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar ASESOR Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
    public List<ProyectosModel> ListarEstudiantes() throws SQLException {
        List<ProyectosModel> listadoA = null;
        ProyectosModel per;
        ResultSet rs;
        String sql = " select * from ESTUDIANTES WHERE rol_fk=1";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectosModel();
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

    @Override
    public void registrar(ProyectosModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void modificarArchivo(UploadedFile archivo, ProyectosModel obj) throws Exception {
        String sql = "update PROYECTOS set acta=? where id=?";
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

        String sql = "select acta, nombre from PROYECTOS WHERE id =?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet st = ps.executeQuery();
            while (st.next()) {
                InputStream stream = st.getBinaryStream("acta");
                String description = st.getString("nombre");
                archivo = DefaultStreamedContent.builder()
                        .name(description + ".jpg")
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

   
}
