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
    UNIQUE KEY uk_jugador_dni (dni)
);

-- Creación de la tabla Campeonato
CREATE TABLE campeonato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT false,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    UNIQUE KEY uk_campeonato_nombre_categoria (nombre, categoria)
);

-- Creación de la tabla Partido
CREATE TABLE partido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    jornada_id BIGINT NOT NULL,
    equipo1_jugador1_id BIGINT NOT NULL,
    equipo1_jugador2_id BIGINT NOT NULL,
    equipo2_jugador1_id BIGINT NOT NULL,
    equipo2_jugador2_id BIGINT NOT NULL,
    resultado VARCHAR(10),
    pista VARCHAR(50),
    fecha DATE NOT NULL,
    ausente_id BIGINT,
    lesionado_id BIGINT,
    sustituto_id BIGINT,
    juegos_ganados_equipo1 INT NOT NULL DEFAULT 0,
    juegos_perdidos_equipo1 INT NOT NULL DEFAULT 0,
    sets_ganados_equipo1 INT NOT NULL DEFAULT 0,
    sets_perdidos_equipo1 INT NOT NULL DEFAULT 0,
    juegos_ganados_equipo2 INT NOT NULL DEFAULT 0,
    juegos_perdidos_equipo2 INT NOT NULL DEFAULT 0,
    sets_ganados_equipo2 INT NOT NULL DEFAULT 0,
    sets_perdidos_equipo2 INT NOT NULL DEFAULT 0,
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
    campeonato_id BIGINT NOT NULL,
    numero INT NOT NULL,
    fecha DATE NOT NULL,
    UNIQUE KEY uk_jornada_campeonato_numero (campeonato_id, numero),
    FOREIGN KEY (campeonato_id) REFERENCES campeonato(id)
);
