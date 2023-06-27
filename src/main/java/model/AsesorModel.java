/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.Date;
import lombok.Data;

@Data
public class AsesorModel {
    
    
    int id;
    String nombre;
    String apellidos;
    String password;
    String email;
    String DNI;
    String celular;
    String estado;
    int rol_fk;
    
    String fila;
    String role;
    
}
