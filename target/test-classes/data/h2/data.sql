-- Inserción de jugadores
INSERT INTO jugador (dni, nombre_completo, telefono, email, sexo, estado, lesionado)
VALUES 
    ('12345678A', 'Juan Pérez', '123456789', 'juan@example.com', 'Masculino', 'Alta', false),
    ('87654321B', 'María Gómez', '987654321', 'maria@example.com', 'Femenino', 'Alta', false),
    ('56789123C', 'Carlos Martínez', '654321987', 'carlos@example.com', 'Masculino', 'Baja', true);

-- Inserción de campeonatos
INSERT INTO campeonato (nombre, categoria, activo, fecha_inicio, fecha_fin)
VALUES
    ('Campeonato de Verano', 'Masculina', true, '2024-06-01', '2024-08-31'),
    ('Torneo Mixto', 'Mixta', false, '2024-07-15', '2024-08-15'),
    ('Liga Femenina', 'Femenina', true, '2024-06-15', '2024-07-31');

-- Inserción de jornadas
INSERT INTO jornada (campeonato_id, numero, fecha)
VALUES
    (1, 1, '2024-06-01'),
    (1, 2, '2024-06-15'),
    (2, 1, '2024-07-15');
