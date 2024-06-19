-- Inserción de jugadores
INSERT INTO jugador (dni, nombre_completo, telefono, email, sexo, estado, lesionado)
VALUES 
    ('12345678A', 'Juan Pérez', '123456789', 'juan@example.com', 'Masculino', 'Alta', false),
    ('87654321B', 'María Gómez', '987654321', 'maria@example.com', 'Femenino', 'Alta', false),
    ('56789123C', 'Carlos Martínez', '654321987', 'carlos@example.com', 'Masculino', 'Baja', true);

-- Insertar datos en la tabla jornada
INSERT INTO jornada (nombre, fecha) VALUES 
('Jornada 1', '2024-06-01'),
('Jornada 2', '2024-06-08');
    
-- Insertar datos en la tabla equipo
INSERT INTO equipo (nombre, jugador1_id, jugador2_id) VALUES 
('Equipo 1', 1, 2),
('Equipo 2', 2, 1);    
    
-- Insertar datos en la tabla partido
INSERT INTO partido (jornada_id, equipo1_jugador1_id, equipo1_jugador2_id, equipo2_jugador1_id, equipo2_jugador2_id, resultado, pista, fecha, ausente_id, lesionado_id, sustituto_id, juegos_ganados_equipo1, juegos_perdidos_equipo1, sets_ganados_equipo1, sets_perdidos_equipo1, juegos_ganados_equipo2, juegos_perdidos_equipo2, sets_ganados_equipo2, sets_perdidos_equipo2) VALUES 
(1, 1, 2, 2, 1, '6-4 6-4', 'Pista 1', '2024-06-01', NULL, NULL, NULL, 6, 4, 2, 0, 4, 6, 0, 2);

-- Inserción de campeonatos
INSERT INTO campeonato (nombre, categoria, activo, fecha_inicio, fecha_fin)
VALUES
    ('Campeonato de Verano', 'Masculina', true, '2024-06-01', '2024-08-31'),
    ('Torneo Mixto', 'Mixta', false, '2024-07-15', '2024-08-15'),
    ('Liga Femenina', 'Femenina', true, '2024-06-15', '2024-07-31');


