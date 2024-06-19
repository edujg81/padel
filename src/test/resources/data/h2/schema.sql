-- Crear tabla jugador
CREATE TABLE jugador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    sexo VARCHAR(10) NOT NULL,
    estado VARCHAR(10) NOT NULL,
    lesionado BOOLEAN NOT NULL DEFAULT false,
    UNIQUE (dni)
);

-- Crear tabla jornada
CREATE TABLE jornada (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL
);

-- Crear tabla equipo
CREATE TABLE equipo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    jugador1_id BIGINT,
    jugador2_id BIGINT,
    FOREIGN KEY (jugador1_id) REFERENCES jugador(id),
    FOREIGN KEY (jugador2_id) REFERENCES jugador(id)
);

-- Crear tabla partido
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

-- Crear tabla campeonato
CREATE TABLE campeonato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);