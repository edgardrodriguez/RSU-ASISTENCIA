USE RSU_ASISTENCIA;
SELECT * FROM ASESOR;
select * from PROYECTO_DETALLE;
select id,nombre from PROYECTOS where estado ='A' OR estado='E';

 
 select * from ESTUDIANTES WHERE rol_fk=1;
INSERT INTO PROYECTO_DETALLE (descripcion,proyectos_fk,estudiantes_fk,evidencia) VALUES (?,?,?,?)
update PROYECTO_DETALLE set descripcion=?,proyectos_fk=?,estudiantes_fk=?,evidencia=? where id=?