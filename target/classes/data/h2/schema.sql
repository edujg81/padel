-- Creación de la tabla Jugador
CREATE TABLE JUGADOR (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    sexo VARCHAR(10) CHECK (sexo IN ('Masculino', 'Femenino')),
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
    FOREIGN KEY (campeonato_id) REFERENCES CAMPEONATO(id) ON DELETE CASCADE,
    FOREIGN KEY (jugador_id) REFERENCES JUGADOR(id) ON DELETE CASCADE,
    UNIQUE (campeonato_id, jugador_id)
);

-- Creación de la tabla Jornada
CREATE TABLE JORNADA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    campeonato_id BIGINT NOT NULL,
    FOREIGN KEY (campeonato_id) REFERENCES CAMPEONATO(id) ON DELETE CASCADE,
    UNIQUE (campeonato_id, numero)
);

-- Creación de la tabla Partido
CREATE TABLE PARTIDO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    jornada_id BIGINT,
    equipo1_jugador1_id BIGINT NOT NULL DEFAULT -1,
    equipo1_jugador2_id BIGINT NOT NULL DEFAULT -1,
    equipo2_jugador1_id BIGINT NOT NULL DEFAULT -1,
    equipo2_jugador2_id BIGINT NOT NULL DEFAULT -1,
    resultado VARCHAR(20),
    pista VARCHAR(100),
    juegos_ganados_equipo1_set1 INTEGER,
    juegos_ganados_equipo2_set1 INTEGER,
    juegos_ganados_equipo1_set2 INTEGER,
    juegos_ganados_equipo2_set2 INTEGER,
    juegos_ganados_equipo1_set3 INTEGER,
    juegos_ganados_equipo2_set3 INTEGER,
    sets_ganados_Equipo1 INTEGER,
    sets_ganados_Equipo2 INTEGER,
    equipo_ganador VARCHAR(20),
    registrado BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (jornada_id) REFERENCES JORNADA(id) ON DELETE CASCADE,
    FOREIGN KEY (equipo1_jugador1_id) REFERENCES JUGADOR(id) ON DELETE SET DEFAULT,
    FOREIGN KEY (equipo1_jugador2_id) REFERENCES JUGADOR(id) ON DELETE SET DEFAULT,
    FOREIGN KEY (equipo2_jugador1_id) REFERENCES JUGADOR(id) ON DELETE SET DEFAULT,
    FOREIGN KEY (equipo2_jugador2_id) REFERENCES JUGADOR(id) ON DELETE SET DEFAULT
);

CREATE TABLE ausencia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    partido_id BIGINT NOT NULL,
    ausente_id BIGINT NOT NULL DEFAULT -1,
    sustituto_id BIGINT NOT NULL DEFAULT -1,
    FOREIGN KEY (partido_id) REFERENCES partido(id) ON DELETE CASCADE,
    FOREIGN KEY (ausente_id) REFERENCES jugador(id) ON DELETE SET DEFAULT,
    FOREIGN KEY (sustituto_id) REFERENCES jugador(id) ON DELETE SET DEFAULT
);

-- Clasificacion table
CREATE TABLE IF NOT EXISTS clasificacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    campeonato_id BIGINT NOT NULL,
    jugador_id BIGINT NOT NULL DEFAULT -1,
    posicion INT DEFAULT 0,
    puntos INT DEFAULT 0,
    partidos_jugados INT DEFAULT 0,
    partidos_ganados INT DEFAULT 0,
    partidos_perdidos INT DEFAULT 0,
    sets_ganados INT DEFAULT 0,
    sets_perdidos INT DEFAULT 0,
    juegos_ganados INT DEFAULT 0,
    juegos_perdidos INT DEFAULT 0,
    FOREIGN KEY (campeonato_id) REFERENCES campeonato(id) ON DELETE CASCADE,
    FOREIGN KEY (jugador_id) REFERENCES jugador(id) ON DELETE SET DEFAULT
);