/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edgard
 */
import java.util.Date;
import lombok.Data;

@Data
public class ProyectosModel {
    int id;
    String nombre;
    String descripcion;
    String tipo;
    String estado;
    String revisado;
    String asesor_fk;
    String estudiantes_fk;
    String concatAse;
    String concatEst;
    
    String fila;
    String tipoConcat;
    String estadoConcat;
    String revisadoConcat;
    
    //exterm
    int idAse;
    String nombreAse;
    String apellidoAse;
    
    int idEst;
    String nombreEst;
    String apellidoEst;
    int rol_fk;
}
