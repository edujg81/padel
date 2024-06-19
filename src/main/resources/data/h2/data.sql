-- Insertar jugadores de ejemplo
INSERT INTO jugador (dni, nombre_completo, telefono, email, sexo, estado, lesionado)
VALUES 
    ('12345678A', 'Juan Pérez', '123456789', 'juan@example.com', 'Masculino', 'Activo', false),
    ('87654321B', 'María López', '987654321', 'maria@example.com', 'Femenino', 'Activo', false),
    ('11111111C', 'Pedro Gómez', '555555555', 'pedro@example.com', 'Masculino', 'Activo', false),
    ('22222222D', 'Ana Martínez', '666666666', 'ana@example.com', 'Femenino', 'Activo', false);

-- Insertar campeonatos de ejemplo
INSERT INTO campeonato (nombre, fecha_inicio, fecha_fin, estado)
VALUES 
    ('Campeonato de Verano', '2024-07-01', '2024-08-31', 'Planificado'),
    ('Torneo de Otoño', '2024-09-01', '2024-10-31', 'Planificado');

-- Insertar jornadas de ejemplo
INSERT INTO jornada (campeonato_id, numero, fecha, estado)
VALUES 
    (1, 1, '2024-07-10', 'Programada'),
    (1, 2, '2024-07-17', 'Programada'),
    (2, 1, '2024-09-15', 'Programada');

-- Insertar partidos de ejemplo
INSERT INTO partido (jornada_id, equipo1_jugador1_id, equipo1_jugador2_id, equipo2_jugador1_id, equipo2_jugador2_id, resultado, pista, fecha, ausente_id, lesionado_id, sustituto_id, juegos_ganados_equipo1, juegos_perdidos_equipo1, sets_ganados_equipo1, sets_perdidos_equipo1, juegos_ganados_equipo2, juegos_perdidos_equipo2, sets_ganados_equipo2, sets_perdidos_equipo2)
VALUES 
    (1, 1, 2, 3, 4, '2-0', 'Pista 1', '2024-07-10', NULL, NULL, NULL, 21, 15, 2, 0, 0, 0),
    (1, 3, 4, 1, 2, '1-2', 'Pista 2', '2024-07-10', NULL, NULL, NULL, 15, 21, 1, 2, 21, 15, 0, 2),
    (2, 1, 2, 3, 4, NULL, 'Pista 1', '2024-07-17', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    (3, 3, 4, 1, 2, NULL, 'Pista 2', '2024-09-15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- Puedes agregar más datos según sea necesario