use sys;

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
    nombre varchar(30)  NOT NULL COMMENT 'nombres de los docentes',
    apellidos varchar(30)  NOT NULL COMMENT 'apellidos de los docentes',
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
    estudiantes_fk int  NOT NULL COMMENT 'llave foránea de la tabla estudiantes.',
    proyecto_detalle_fk int  NOT NULL COMMENT 'llave foranea de proyecto detalle.',
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
    nombre varchar(30)  NOT NULL COMMENT 'nombre de los estudiantes',
    apellidos varchar(30)  NOT NULL COMMENT 'apellidos de los estudiantes',
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
    nombre varchar(60)  NOT NULL COMMENT 'nombre de la tabla proyectos',
    descripcion varchar(80)  NOT NULL COMMENT 'descripción de la tabla proyectos.',
    tipo char(1)  NOT NULL COMMENT 'tipo de proyecto de resposabilidad social (proyeccion social, voluntariado, extension universitaria)',
    estado char(1)  NOT NULL COMMENT 'estado de la tabla proyectos (aprobacion=A, ejecucion(suben evidencia)=E, finalizacion=F)',
    revisado char(1)  NOT NULL COMMENT 'revisado por (coordinador=C, direccion RSU=D)',
    asesor_fk int  NOT NULL COMMENT 'llave foránea de la tabla docentes',
    estudiantes_fk int  NOT NULL COMMENT 'llave foránea de la tabla estudiantes',
    CONSTRAINT PROYECTOS_pk PRIMARY KEY (id)
) COMMENT 'tabla de proyectos registrados';

-- Table: PROYECTO_DETALLE
CREATE TABLE PROYECTO_DETALLE (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'id de proyecto detalle',
    descripcion varchar(100)  NOT NULL COMMENT 'descripcion de registros de proyecto',
    proyectos_fk int  NOT NULL COMMENT 'llave foránea de la tabla proyectos',
    estudiantes_fk int  NOT NULL COMMENT 'llave foránea de la tabla estudiantes',
    CONSTRAINT PROYECTO_DETALLE_pk PRIMARY KEY (id)
) COMMENT 'Detalles del proyecto numero de registro de cada asistente.';

-- Table: ROL
CREATE TABLE ROL (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'identificador de la tabla rol',
    role char(1)  NOT NULL COMMENT 'roles de usuario',
    CONSTRAINT ROL_pk PRIMARY KEY (id)
) COMMENT 'Tabla rol';

-- foreign keys
-- Reference: ASISTENCIA_PROYECTO_DETALLE (table: ASISTENCIA)
ALTER TABLE ASISTENCIA ADD CONSTRAINT ASISTENCIA_PROYECTO_DETALLE FOREIGN KEY ASISTENCIA_PROYECTO_DETALLE (proyecto_detalle_fk)
    REFERENCES PROYECTO_DETALLE (id) on delete cascade on update cascade;

-- Reference: CARRERAS_ALUMNOS (table: ESTUDIANTES)
ALTER TABLE ESTUDIANTES ADD CONSTRAINT CARRERAS_ALUMNOS FOREIGN KEY CARRERAS_ALUMNOS (carreras_fk)
    REFERENCES CARRERAS (id) on delete cascade on update cascade;

-- Reference: DOCENTES_PROYECTOS (table: PROYECTOS)
ALTER TABLE PROYECTOS ADD CONSTRAINT DOCENTES_PROYECTOS FOREIGN KEY DOCENTES_PROYECTOS (asesor_fk)
    REFERENCES ASESOR (id) on delete cascade on update cascade;

-- Reference: ESTUDIANTES_ASISTENCIA (table: ASISTENCIA)
ALTER TABLE ASISTENCIA ADD CONSTRAINT ESTUDIANTES_ASISTENCIA FOREIGN KEY ESTUDIANTES_ASISTENCIA (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) on delete cascade on update cascade;

-- Reference: ESTUDIANTES_ESTUDIANTES (table: ESTUDIANTES)
ALTER TABLE ESTUDIANTES ADD CONSTRAINT ESTUDIANTES_ESTUDIANTES FOREIGN KEY ESTUDIANTES_ESTUDIANTES (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) on delete cascade on update cascade;

-- Reference: ESTUDIANTES_PROYECTOS (table: PROYECTOS)
ALTER TABLE PROYECTOS ADD CONSTRAINT ESTUDIANTES_PROYECTOS FOREIGN KEY ESTUDIANTES_PROYECTOS (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) on delete cascade on update cascade;

-- Reference: ESTUDIANTES_PROYECTO_DETALLE (table: PROYECTO_DETALLE)
ALTER TABLE PROYECTO_DETALLE ADD CONSTRAINT ESTUDIANTES_PROYECTO_DETALLE FOREIGN KEY ESTUDIANTES_PROYECTO_DETALLE (estudiantes_fk)
    REFERENCES ESTUDIANTES (id) on delete cascade on update cascade;

-- Reference: PROYECTO_DETALLE_PROYECTOS (table: PROYECTO_DETALLE)
ALTER TABLE PROYECTO_DETALLE ADD CONSTRAINT PROYECTO_DETALLE_PROYECTOS FOREIGN KEY PROYECTO_DETALLE_PROYECTOS (proyectos_fk)
    REFERENCES PROYECTOS (id) on delete cascade on update cascade;

-- Reference: ROL_ADMIN (table: ADMIN)
ALTER TABLE ADMIN ADD CONSTRAINT ROL_ADMIN FOREIGN KEY ROL_ADMIN (rol_fk)
    REFERENCES ROL (id) on delete cascade on update cascade;

-- Reference: ROL_DOCENTES (table: ASESOR)
ALTER TABLE ASESOR ADD CONSTRAINT ROL_DOCENTES FOREIGN KEY ROL_DOCENTES (rol_fk)
    REFERENCES ROL (id) on delete cascade on update cascade;

-- Reference: ROL_ESTUDIANTES (table: ESTUDIANTES)
ALTER TABLE ESTUDIANTES ADD CONSTRAINT ROL_ESTUDIANTES FOREIGN KEY ROL_ESTUDIANTES (rol_fk)
    REFERENCES ROL (id) on delete cascade on update cascade;

-- End of file.
