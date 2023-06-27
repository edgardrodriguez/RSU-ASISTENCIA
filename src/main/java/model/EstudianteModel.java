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
public class EstudianteModel {
    int id;
    String nombre;
    String apellidos;
    String password;
    String email;
    String DNI;
    String celular;
    String estado;
    int rol_fk;
    int carreras_fk;
    int estudiantes_fk;
    
    String fila;
    String role;
    String carrera;
    String relacion;
    
    // CARRERA
    int idCar;
    String concatCarrer;
}
