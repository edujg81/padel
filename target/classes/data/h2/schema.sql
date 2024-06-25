-- Creación de la tabla Jugador
CREATE TABLE JUGADOR (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    sexo VARCHAR(10) CHECK (sexo IN ('Masculino', 'Femenino')) NOT NULL,
    estado VARCHAR(10) CHECK (estado IN ('Alta', 'Baja')) NOT NULL,
    lesionado BOOLEAN NOT NULL DEFAULT false,
    fecha_alta DATE NOT NULL,
    fecha_baja DATE,
    UNIQUE(dni)
);

-- Creación de la tabla Campeonato
CREATE TABLE CAMPEONATO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    anio INTEGER NOT NULL,
    categoria VARCHAR(20) CHECK (categoria IN ('Masculino', 'Femenino', 'Mixto')) NOT NULL,
    division INTEGER NOT NULL,
    estado VARCHAR(20) CHECK (estado IN ('Sin iniciar', 'En curso', 'Finalizado')) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT true,
    puntos_por_victoria INTEGER NOT NULL DEFAULT 2,
    puntos_por_derrota INTEGER NOT NULL DEFAULT 0,
    UNIQUE (anio, categoria, division, activo)
);

-- Creación de la tabla INSCRIPCION
CREATE TABLE INSCRIPCION (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    campeonato_id BIGINT,
    jugador_id BIGINT,
    FOREIGN KEY (campeonato_id) REFERENCES CAMPEONATO(id),
    FOREIGN KEY (jugador_id) REFERENCES JUGADOR(id),
    UNIQUE (campeonato_id, jugador_id)
);

-- Creación de la tabla Jornada
CREATE TABLE JORNADA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    campeonato_id BIGINT,
    FOREIGN KEY (campeonato_id) REFERENCES CAMPEONATO(id),
    UNIQUE (numero, campeonato_id)
);

-- Creación de la tabla Partido
CREATE TABLE PARTIDO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    jornada_id BIGINT,
    equipo1_jugador1_id BIGINT,
    equipo1_jugador2_id BIGINT,
    equipo2_jugador1_id BIGINT,
    equipo2_jugador2_id BIGINT,
    resultado VARCHAR(20),
    pista VARCHAR(100),
    juegos_ganados_equipo1_set1 INTEGER,
    juegos_ganados_equipo2_set1 INTEGER,
    juegos_ganados_equipo1_set2 INTEGER,
    juegos_ganados_equipo2_set2 INTEGER,
    juegos_ganados_equipo1_set3 INTEGER,
    juegos_ganados_equipo2_set3 INTEGER,
    equipo_ganador VARCHAR(20),
    FOREIGN KEY (jornada_id) REFERENCES JORNADA(id),
    FOREIGN KEY (equipo1_jugador1_id) REFERENCES JUGADOR(id),
    FOREIGN KEY (equipo1_jugador2_id) REFERENCES JUGADOR(id),
    FOREIGN KEY (equipo2_jugador1_id) REFERENCES JUGADOR(id),
    FOREIGN KEY (equipo2_jugador2_id) REFERENCES JUGADOR(id)
);

CREATE TABLE ausencia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    partido_id BIGINT,
    ausente_id BIGINT,
    sustituto_id BIGINT,
    FOREIGN KEY (partido_id) REFERENCES partido(id),
    FOREIGN KEY (ausente_id) REFERENCES jugador(id),
    FOREIGN KEY (sustituto_id) REFERENCES jugador(id)
);