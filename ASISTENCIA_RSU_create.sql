use sys;
SET GLOBAL log_bin_trust_function_creators = 1;

DROP database IF EXISTS RSU_ASISTENCIA;
CREATE DATABASE RSU_ASISTENCIA;

USE RSU_ASISTENCIA;
CREATE TABLE ADMIN (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'identificador de la tabla admin',
    email varchar(60)  NOT NULL COMMENT 'email del administrador',
    password varchar(20)  NOT NULL COMMENT 'contraseña del adminsitrador',
    estado char(1)  NOT NULL COMMENT 'estado del admin.',
    rol_fk int  NOT NULL COMMENT 'llave foránea de la tabla rol',
    CONSTRAINT ADMIN_pk PRIMARY KEY (id)
) COMMENT 'Tabla administradora';

-- Table: ASESOR
CREATE TABLE ASESOR (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'identificador de la tabla asesor.',
    nombre varchar(40)  NOT NULL COMMENT 'nombres de los docentes',
    apellidos varchar(40)  NOT NULL COMMENT 'apellidos de los docentes',
    password varchar(20)  NOT NULL COMMENT 'contraseña del docente',
    email varchar(60)  NOT NULL COMMENT 'correo del docente',
    DNI char(8)  NOT NULL COMMENT 'DNI del docente',
    celular char(9)  NOT NULL COMMENT 'celular del docente',
    estado char(1)  NOT NULL COMMENT 'estado del docente (activo=A, inactivo=I)',
    rol_fk int  NOT NULL COMMENT 'llave foránea de la tabla rol',
    CONSTRAINT ASESOR_pk PRIMARY KEY (id)
) COMMENT 'tabla asesores';

-- Table: ASISTENCIA
CREATE TABLE ASISTENCIA (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id identificador de la asistencia de estudiantes',
    dia char(1)  NOT NULL COMMENT 'día de la semana de la asistencia',
    cantHoras int  NOT NULL COMMENT 'cantidad de horas realizadas por día.',
    fecha timestamp  NOT NULL COMMENT 'fecha de la realización de la asistencia.',
    estado char(1)  NOT NULL COMMENT 'estado de la asistencia (activo=A, inactivo=I)',
    evidencia mediumblob  NULL,
    proyecto_fk int  NOT NULL COMMENT 'llave foránea de la tabla proyectos.',
    CONSTRAINT ASISTENCIA_pk PRIMARY KEY (id)
) COMMENT 'tabla asistencia de estudiantes';

-- Table: CARRERAS
CREATE TABLE CARRERAS (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id de la tabla de ubigeo.',
    nombre varchar(30)  NOT NULL COMMENT 'nombre de las carreras',
    ciclo char(4)  NOT NULL COMMENT 'ciclo de la carrera',
    turno char(1)  NOT NULL COMMENT 'turno de la carrera',
    CONSTRAINT CARRERAS_pk PRIMARY KEY (id)
) COMMENT 'tabla de carreras';

-- Table: ESTUDIANTES
CREATE TABLE ESTUDIANTES (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id de la tabla estudiantes',
    nombre varchar(40)  NOT NULL COMMENT 'nombre de los estudiantes',
    apellidos varchar(40)  NOT NULL COMMENT 'apellidos de los estudiantes',
    password varchar(20)  NOT NULL COMMENT 'contraseña de los estudiantes',
    email varchar(60)  NOT NULL COMMENT 'correo institucional del estudiante',
    DNI char(8)  NOT NULL COMMENT 'dni del estudiante',
    celular char(9)  NOT NULL COMMENT 'celular del estudiante',
    estado char(1)  NOT NULL COMMENT 'estado del estudiante  (activo=A, inactivo=I)',
    rol_fk int  NOT NULL COMMENT 'llave foránea de la tabla rol del estudiante.',
    carreras_fk int  NOT NULL COMMENT 'llave foránea de la tabla carrera del estudiante  ',
    estudiantes_fk int  NULL COMMENT 'llave foránea recursiva de la misma tabla etudiantes.',
    CONSTRAINT ESTUDIANTES_pk PRIMARY KEY (id)
) COMMENT 'tabla de los estudiantes';

-- Table: PROYECTOS
CREATE TABLE PROYECTOS (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id de la tabla proyectos',
    nombre varchar(500)  NOT NULL COMMENT 'nombre de la tabla proyectos',
    descripcion varchar(500)  NOT NULL COMMENT 'descripción de la tabla proyectos.',
    tipo char(2)  NOT NULL COMMENT 'tipo de proyecto de resposabilidad social (proyeccion social = P, voluntariado = V, extension universitaria = E)',
    estado char(1)  NOT NULL COMMENT 'estado de la tabla proyectos (aprobacion=A, ejecucion(suben evidencia)=E, finalizacion=F)',
    revisado char(1)  NOT NULL COMMENT 'revisado por (coordinador=C, direccion RSU=D)',
    ods char(2)  NOT NULL COMMENT 'OBJETIVOS DE DESARROLLO SOSTENIBLE',
    facultad char(1)  NOT NULL COMMENT 'facultad a la ue va dirigida el proyecto.',
    escuelaProfesional char(1)  NOT NULL COMMENT 'escuela profesional a la que va dirigido el proyecto.',
    semestre char(6) NOT NULL,
    fecha timestamp  NOT NULL COMMENT 'fecha de registro del proyecto',
    link text NULL COMMENT 'link de el enlace de google drive',
    resolucion mediumblob NULL,
    formato mediumblob  NULL COMMENT 'formato de levantamiento de proyecto',
    asesor_fk int  NOT NULL COMMENT 'llave foránea de la tabla docentes',
    estudiantes_fk int  NOT NULL COMMENT 'llave foránea de la tabla estudiantes',
    CONSTRAINT PROYECTOS_pk PRIMARY KEY (id)
) COMMENT 'tabla de proyectos registrados';

-- Table: PROYECTO_DETALLE
CREATE TABLE PROYECTO_DETALLE (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id de proyecto detalle',
    estado char(1)  NOT NULL,
    fecha timestamp  NOT NULL,
    proyectos_fk int  NOT NULL COMMENT 'llave foránea de la tabla proyectos',
    estudiantes_fk int  NOT NULL COMMENT 'llave foránea de la tabla estudiantes',
    CONSTRAINT PROYECTO_DETALLE_pk PRIMARY KEY (id)
) COMMENT 'Detalles del proyecto numero de registro de cada asistente.';

-- Table: REPOSITORIO_PROYECTO
CREATE TABLE REPOSITORIO_PROYECTO (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id de la tabla repositorio_proyecto',
    descripcion varchar(90)  NOT NULL COMMENT 'descripcion de lo que se va a guardar en el repositorio del proyecto.',
    documento mediumblob NULL COMMENT 'documento a guardar (repositorio, documentos, constancia, etc.)',
    proyecto_fk int  NOT NULL COMMENT 'llave foranea de la tabla proyecto',
    CONSTRAINT REPOSITORIO_PROYECTO_pk PRIMARY KEY (id)
) COMMENT 'Tabla de Repositorio de proyectos';

-- Table: ROL
CREATE TABLE ROL (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'identificador de la tabla rol',
    role char(1)  NOT NULL COMMENT 'roles de usuario',
    CONSTRAINT ROL_pk PRIMARY KEY (id)
) COMMENT 'Tabla rol';

-- foreign keys
-- Reference: ASESOR_PROYECTOS (table: PROYECTOS)
ALTER TABLE PROYECTOS ADD CONSTRAINT ASESOR_PROYECTOS FOREIGN KEY ASESOR_PROYECTOS (asesor_fk)
    REFERENCES ASESOR (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: CARRERAS_ALUMNOS (table: ESTUDIANTES)
ALTER TABLE ESTUDIANTES ADD CONSTRAINT CARRERAS_ALUMNOS FOREIGN KEY CARRERAS_ALUMNOS (carreras_fk)
    REFERENCES CARRERAS (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: ESTUDIANTES_ESTUDIANTES (table: ESTUDIANTES)
ALTER TABLE ESTUDIANTES ADD CONSTRAINT ESTUDIANTES_ESTUDIANTES FOREIGN KEY ESTUDIANTES_ESTUDIANTES (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: ESTUDIANTES_PROYECTOS (table: PROYECTOS)
ALTER TABLE PROYECTOS ADD CONSTRAINT ESTUDIANTES_PROYECTOS FOREIGN KEY ESTUDIANTES_PROYECTOS (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: ESTUDIANTES_PROYECTO_DETALLE (table: PROYECTO_DETALLE)
ALTER TABLE PROYECTO_DETALLE ADD CONSTRAINT ESTUDIANTES_PROYECTO_DETALLE FOREIGN KEY ESTUDIANTES_PROYECTO_DETALLE (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: PROYECTOS_ASISTENCIA (table: ASISTENCIA)
ALTER TABLE ASISTENCIA ADD CONSTRAINT PROYECTOS_ASISTENCIA FOREIGN KEY PROYECTOS_ASISTENCIA (proyecto_fk)
    REFERENCES PROYECTOS (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: PROYECTOS_PROYECTO_DETALLE (table: PROYECTO_DETALLE)
ALTER TABLE PROYECTO_DETALLE ADD CONSTRAINT PROYECTOS_PROYECTO_DETALLE FOREIGN KEY PROYECTOS_PROYECTO_DETALLE (proyectos_fk)
    REFERENCES PROYECTOS (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: PROYECTOS_REPOSITORIO_PROYECTO (table: REPOSITORIO_PROYECTO)
ALTER TABLE REPOSITORIO_PROYECTO ADD CONSTRAINT PROYECTOS_REPOSITORIO_PROYECTO FOREIGN KEY PROYECTOS_REPOSITORIO_PROYECTO (proyecto_fk)
    REFERENCES PROYECTOS (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: ROL_ADMIN (table: ADMIN)
ALTER TABLE ADMIN ADD CONSTRAINT ROL_ADMIN FOREIGN KEY ROL_ADMIN (rol_fk)
    REFERENCES ROL (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: ROL_DOCENTES (table: ASESOR)
ALTER TABLE ASESOR ADD CONSTRAINT ROL_DOCENTES FOREIGN KEY ROL_DOCENTES (rol_fk)
    REFERENCES ROL (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- Reference: ROL_ESTUDIANTES (table: ESTUDIANTES)
ALTER TABLE ESTUDIANTES ADD CONSTRAINT ROL_ESTUDIANTES FOREIGN KEY ROL_ESTUDIANTES (rol_fk)
    REFERENCES ROL (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- End of file.
CREATE OR REPLACE VIEW V_ASESOR AS
select ROW_NUMBER() OVER( ORDER BY ASESOR.id desc) AS FILA,
ASESOR.id, ASESOR.nombre, ASESOR.apellidos, ASESOR.password, ASESOR.email, ASESOR.DNI,
ASESOR.celular, ASESOR.estado, ASESOR.rol_fk, ROL.role  from ASESOR
INNER JOIN ROL ON ROL.id = ASESOR.rol_fk;

CREATE OR REPLACE VIEW V_ESTUDIANTES AS
select ROW_NUMBER() OVER( ORDER BY super.id desc) AS FILA,
super.id, super.nombre, super.apellidos, super.password, super.email, super.DNI,
super.celular, super.estado, super.rol_fk,
super.carreras_fk,super.estudiantes_fk, ROL.role,
CONCAT(CARRERAS.nombre," ",CARRERAS.ciclo," ",CARRERAS.turno) as CARRERA,
CONCAT(infer.nombre," ",infer.apellidos) as RELACION 
from ESTUDIANTES as super
INNER JOIN ROL ON ROL.id = super.rol_fk
INNER JOIN CARRERAS ON CARRERAS.ID = super.carreras_fk
left join ESTUDIANTES as infer on super.estudiantes_fk =infer.id;

CREATE OR REPLACE VIEW V_CARRERAS AS
select CARRERAS.id,CONCAT(CARRERAS.nombre," ",CARRERAS.ciclo," ", CARRERAS.turno) as concatCarrer from CARRERAS;

CREATE OR REPLACE VIEW V_PROYECTOS AS
select ROW_NUMBER() OVER( ORDER BY PRO.id desc) AS fila, PRO.id,PRO.fecha,
PRO.nombre, PRO.descripcion, PRO.tipo, PRO.estado, PRO.revisado, PRO.link, 
PRO.asesor_fk, PRO.estudiantes_fk, PRO.ods,PRO.facultad,PRO.escuelaProfesional,PRO.semestre,
CASE WHEN PRO.tipo='PS' THEN 'PROYECCION SOCIAL'
WHEN PRO.tipo='PV' THEN 'VOLUNTARIADO'
WHEN PRO.tipo='PE' THEN 'EXTENSION UNIVERSITARIA'
END AS tipoConcat,
CASE WHEN PRO.estado='A' THEN 'APROBACION'
WHEN PRO.estado='E' THEN 'EJECUCION'
WHEN PRO.estado='F' THEN 'FINALIZACION'
WHEN PRO.estado='P' THEN 'EN PROCESO'
END AS estadoConcat,
CASE WHEN PRO.revisado='C' THEN 'COORDINADOR'
WHEN PRO.revisado='D' THEN 'DIRECCION RSU'
WHEN PRO.revisado='S' THEN 'SIN REVISAR'
END AS revisadoConcat,
CASE WHEN PRO.ods='1' THEN '1 FIN DE LA POBREZA' 
WHEN PRO.ods='2' THEN '2 HAMBRE CERO' 
WHEN PRO.ods='3' THEN '3 SALUD Y BIENESTAR' 
WHEN PRO.ods='4' THEN '4 EDUCACIÒN DE CALIDAD' 
WHEN PRO.ods='5' THEN '5 IGUALDAD DE GENERO' 
WHEN PRO.ods='6' THEN '6 AGUA LIMPIA Y SANEAMIENTO' 
WHEN PRO.ods='7' THEN '7 ENERGIA ASEQUIBLE Y NO CONTAMINANTE' 
WHEN PRO.ods='8' THEN '8 TRABAJO DECENTE Y CRECIMIENTO ECONOMICO' 
WHEN PRO.ods='9' THEN '9 INDUSTRIA INNOVACION E INFRAESTRUCTURA' 
WHEN PRO.ods='10' THEN '10 REDUCCION DE LAS DESIGUALDADES' 
WHEN PRO.ods='11' THEN '11 CIUDADES Y COMUNIDADES SOSTENIBLES' 
WHEN PRO.ods='12' THEN '12 PRODUCCION Y CONSUMO RESPONSABLE' 
WHEN PRO.ods='13' THEN '13 ACCION POR EL CLIMA' 
WHEN PRO.ods='14' THEN '14 VIDA SUBMARINA' 
WHEN PRO.ods='15' THEN '15 VIDA DE ECOSISTEMAS TERRESTRES' 
WHEN PRO.ods='16' THEN '16 PAZ, JUSTICIA E INSTITUCIONES SOLIDAS' 
WHEN PRO.ods='17' THEN '17 ALIANZAS PARA LOGRAR OBJETIVOS' 
END AS odsConcat,
CASE WHEN PRO.facultad='1' THEN 'FACULTAD DE CIENCIAS AGRARIAS'
WHEN PRO.facultad='2' THEN 'FACULTAD DE INGENIERIA'
WHEN PRO.facultad='3' THEN 'FACULTAD DE CIENCIAS EMPRESARIALES'
END AS facultadConcat,
CASE WHEN PRO.escuelaProfesional='1' THEN 'INGENIERIA DE SISTEMAS'
WHEN PRO.escuelaProfesional='2' THEN 'AGRONOMIA'
WHEN PRO.escuelaProfesional='3' THEN 'ADMINISTRACION'
WHEN PRO.escuelaProfesional='4' THEN 'CONTABILIDAD'
WHEN PRO.escuelaProfesional='5' THEN 'ADMINITRACION DE TURISMO Y HOTELERIA'
END AS escuelProfesionalConcat,
CONCAT(ASESOR.nombre," ",ASESOR.apellidos) as concatAse, 
CONCAT(ESTUDIANTES.nombre," ",ESTUDIANTES.apellidos) as concatEst from PROYECTOS as PRO
INNER JOIN ASESOR ON ASESOR.id = PRO.asesor_fk
INNER JOIN ESTUDIANTES ON ESTUDIANTES.id = PRO.estudiantes_fk;

CREATE OR REPLACE VIEW V_ADMIN AS
SELECT ROW_NUMBER() OVER( ORDER BY id desc) AS fila,id,email,password,estado,rol_fk FROM ADMIN;

CREATE OR REPLACE VIEW V_PROYECTO_DETALLE AS
SELECT ROW_NUMBER() OVER( ORDER BY id desc) AS fila,PROD.fecha,
PROD.id,PROD.proyectos_fk,PROD.estudiantes_fk, PROD.estado,
CONCAT(ESTUDIANTES.nombre," ",ESTUDIANTES.apellidos) as concatEst,
CASE WHEN PROD.estado='A' THEN 'ACTIVO'
WHEN PROD.estado='I' THEN 'INACTIVO'
END AS estadoConcat,
concat(PROYECTOS.tipo,"_",PROYECTOS.id,"_",PROYECTOS.semestre)as concatCodigo,
PROYECTOS.nombre as proNom
FROM PROYECTO_DETALLE AS PROD
INNER JOIN ESTUDIANTES ON ESTUDIANTES.id = PROD.estudiantes_fk
INNER JOIN PROYECTOS ON PROYECTOS.id =PROD.proyectos_fk;

CREATE OR REPLACE VIEW V_ASISTENCIA AS
SELECT ROW_NUMBER() OVER( ORDER BY id desc) AS fila, ASIS.id, ASIS.dia,
CASE 
WHEN ASIS.dia='1' THEN 'LUNES'
WHEN ASIS.dia='2' THEN 'MARTES'
WHEN ASIS.dia='3' THEN 'MIERCOLES'
WHEN ASIS.dia='4' THEN 'JUEVES'
WHEN ASIS.dia='5' THEN 'VIERNES'
WHEN ASIS.dia='6' THEN 'SABADO'
WHEN ASIS.dia='7' THEN 'DOMINGO'
END AS diaConcat, ASIS.cantHoras,
ASIS.fecha,ASIS.estado,
CASE WHEN ASIS.estado ='A' THEN 'ACTIVO'
WHEN ASIS.estado ='I' THEN 'INACTIVO'END AS estadoConcat, ASIS.proyecto_fk,
PROYECTOS.nombre FROM ASISTENCIA AS ASIS
INNER JOIN PROYECTOS ON PROYECTOS.id = ASIS.proyecto_fk;

select * from V_ASISTENCIA where proyecto_fk="2";
DELIMITER $$
DROP FUNCTION IF EXISTS contar_asistencia$$
CREATE FUNCTION contar_asistencia(proyecto int)
  RETURNS INT 
BEGIN
  -- Paso 1. Declaramos una variable local
  DECLARE total INT;

  -- Paso 2. Contamos los productos

  SET total = (SELECT sum(cantHoras) FROM ASISTENCIA WHERE proyecto_fk = proyecto);
  -- Paso 3. Devolvemos el resultado
  RETURN total;
END
$$

DELIMITER ;
SELECT contar_asistencia(1) as contAsistencia from dual;
select * from ASISTENCIA;

