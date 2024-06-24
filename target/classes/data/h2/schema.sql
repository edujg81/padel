-- Creación de la tabla Jugador
CREATE TABLE JUGADOR (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    sexo VARCHAR(10) NOT NULL,
    estado VARCHAR(10) NOT NULL,
    lesionado BOOLEAN NOT NULL DEFAULT false,
    fecha_alta DATE NOT NULL,
    fecha_baja DATE,
    UNIQUE (dni)
);

-- Creación de la tabla Campeonato
CREATE TABLE CAMPEONATO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    año INTEGER NOT NULL,
    categoria VARCHAR(10) NOT NULL,
    division INTEGER NOT NULL,
    estado VARCHAR(20) NOT NULL,
    activo BOOLEAN NOT NULL,
    puntos_por_victoria INTEGER NOT NULL DEFAULT 2,
    puntos_por_derrota INTEGER NOT NULL DEFAULT 0,
    UNIQUE (año, categoria, division)
);

-- Creación de la tabla INSCRIPCION
CREATE TABLE INSCRIPCION (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    jugador_id BIGINT,
    campeonato_id BIGINT,
    FOREIGN KEY (jugador_id) REFERENCES JUGADOR(id),
    FOREIGN KEY (campeonato_id) REFERENCES CAMPEONATO(id),
    UNIQUE (jugador_id, campeonato_id)
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
    ausente_id BIGINT,
    lesionado_id BIGINT,
    sustituto_id BIGINT,
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
    FOREIGN KEY (equipo2_jugador2_id) REFERENCES JUGADOR(id),
    FOREIGN KEY (ausente_id) REFERENCES jugador(id),
    FOREIGN KEY (lesionado_id) REFERENCES jugador(id),
    FOREIGN KEY (sustituto_id) REFERENCES jugador(id)
);