-- Creación de la tabla Jugador
CREATE TABLE jugador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    sexo VARCHAR(10) NOT NULL,
    estado VARCHAR(10) NOT NULL,
    lesionado BOOLEAN NOT NULL DEFAULT false,
    CONSTRAINT uk_jugador_dni UNIQUE (dni)
);

-- Creación de la tabla Campeonato
CREATE TABLE campeonato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(20) NOT NULL
);

-- Creación de la tabla Partido
CREATE TABLE partido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    jornada_id BIGINT,
    equipo1_jugador1_id BIGINT,
    equipo1_jugador2_id BIGINT,
    equipo2_jugador1_id BIGINT,
    equipo2_jugador2_id BIGINT,
    resultado VARCHAR(20),
    pista VARCHAR(100),
    fecha DATE NOT NULL,
    ausente_id BIGINT,
    lesionado_id BIGINT,
    sustituto_id BIGINT,
    juegos_ganados_equipo1 INTEGER,
    juegos_perdidos_equipo1 INTEGER,
    sets_ganados_equipo1 INTEGER,
    sets_perdidos_equipo1 INTEGER,
    juegos_ganados_equipo2 INTEGER,
    juegos_perdidos_equipo2 INTEGER,
    sets_ganados_equipo2 INTEGER,
    sets_perdidos_equipo2 INTEGER,
    FOREIGN KEY (jornada_id) REFERENCES jornada(id),
    FOREIGN KEY (equipo1_jugador1_id) REFERENCES jugador(id),
    FOREIGN KEY (equipo1_jugador2_id) REFERENCES jugador(id),
    FOREIGN KEY (equipo2_jugador1_id) REFERENCES jugador(id),
    FOREIGN KEY (equipo2_jugador2_id) REFERENCES jugador(id),
    FOREIGN KEY (ausente_id) REFERENCES jugador(id),
    FOREIGN KEY (lesionado_id) REFERENCES jugador(id),
    FOREIGN KEY (sustituto_id) REFERENCES jugador(id)
);

-- Creación de la tabla Jornada
CREATE TABLE jornada (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    campeonato_id BIGINT,
    numero INTEGER NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (campeonato_id) REFERENCES campeonato(id)
);
