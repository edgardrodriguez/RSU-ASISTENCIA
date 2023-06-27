USE RSU_ASISTENCIA;
SELECT * FROM ASESOR;
select * from ESTUDIANTES;
select * from PROYECTOS;

 
 select * from ESTUDIANTES WHERE rol_fk=1;
INSERT INTO PROYECTOS (nombre,descripcion,tipo,estado,revisado,asesor_fk,estudiantes_fk) VALUES (?,?,?,?,?,?,?)
update PROYECTOS set nombre=?,descripcion=?,tipo=?,estado=?,revisado=?,asesor_fk=?,estudiantes_fk=? where id=?