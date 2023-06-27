/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EstudianteModel;

/**
 *
 * @author edgard
 */
public class EstudiantesImpl extends Conexion implements ICRUD<EstudianteModel> {

    @Override
    public void registrar(EstudianteModel obj) throws Exception {
        String sql = "INSERT INTO ESTUDIANTES (nombre,apellidos,password,email,DNI,celular,estado,rol_fk,carreras_fk,estudiantes_fk) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellidos());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getDNI());
            ps.setString(6, obj.getCelular());
            String est = "A";
            ps.setString(7, est);
            ps.setInt(8, obj.getRol_fk());
            ps.setInt(9, obj.getCarreras_fk());
            if (obj.getEstudiantes_fk() == 0) {
                ps.setNull(10, Types.INTEGER);
            } else {
                ps.setInt(10, obj.getEstudiantes_fk());
            }

            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar ESTUDIANTES Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(EstudianteModel obj) throws Exception {
        String sql = "update ESTUDIANTES set nombre=?,apellidos=?,password=?,email=?,DNI=?,celular=?,estado=?,rol_fk=?,carreras_fk=?,estudiantes_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellidos());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getDNI());
            ps.setString(6, obj.getCelular());
            ps.setString(7, obj.getEstado());
            ps.setInt(8, obj.getRol_fk());
            ps.setInt(9, obj.getCarreras_fk());
            if (obj.getEstudiantes_fk() == 0) {
                ps.setNull(10, Types.INTEGER);
            } else {
                ps.setInt(10, obj.getEstudiantes_fk());
            }
            ps.setInt(11, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar ESTUDIANTES Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(EstudianteModel obj) throws Exception {
        String sql = "delete from ESTUDIANTES where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar ESTUDIANTES Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<EstudianteModel> listarTodos() throws Exception {
        List<EstudianteModel> listado = null;
        EstudianteModel doc;
        ResultSet rs;
        String sql = "select * from  V_ESTUDIANTES";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doc = new EstudianteModel();
                doc.setFila(rs.getString("fila"));
                doc.setId(rs.getInt("id"));
                doc.setNombre(rs.getString("nombre"));
                doc.setApellidos(rs.getString("apellidos"));
                doc.setPassword(rs.getString("password"));
                doc.setEmail(rs.getString("email"));
                doc.setDNI(rs.getString("DNI"));
                doc.setCelular(rs.getString("celular"));
                doc.setEstado(rs.getString("estado"));
                doc.setRol_fk(rs.getInt("rol_fk"));
                doc.setCarreras_fk(rs.getInt("carreras_fk"));
                doc.setEstudiantes_fk(rs.getInt("estudiantes_fk"));
                doc.setRole(rs.getString("role"));
                doc.setCarrera(rs.getString("CARRERA"));
                doc.setRelacion(rs.getString("RELACION"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar ESTUDIANTES Dao {0} ", e.getMessage());
        }
        return listado;
    }
    public List<EstudianteModel> ListarEstudiantes() throws SQLException {
        List<EstudianteModel> listadoA = null;
        EstudianteModel per;
        ResultSet rs;
        String sql = "select * from ESTUDIANTES WHERE rol_fk=1";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new EstudianteModel();
                per.setId(rs.getInt("id"));
                per.setNombre(rs.getString("nombre"));
                per.setApellidos(rs.getString("apellidos"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al estudiantes Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
    public List<EstudianteModel> ListarCarreras() throws SQLException {
        List<EstudianteModel> listadoA = null;
        EstudianteModel per;
        ResultSet rs;
        String sql = "select * from V_CARRERAS";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new EstudianteModel();
                per.setIdCar(rs.getInt("id"));
                per.setConcatCarrer(rs.getString("concatCarrer"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar carreras Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
}
