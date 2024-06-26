-- Insertar jugadores de ejemplo
INSERT INTO jugador (dni, nombre_completo, telefono, email, sexo, estado, lesionado, fecha_alta, fecha_baja) VALUES
('12345678A', 'Juan Pérez', '600000001', 'juan.perez@example.com', 'Masculino', 'Alta', false, '2024-01-01', null),
('87654321B', 'Ana García', '600000002', 'ana.garcia@example.com', 'Femenino', 'Alta', false, '2024-01-01', null),
('33333333C', 'Jugador Tres', '600333333', 'jugador3@padel.com', 'Masculino', 'Alta', false, '2021-03-20', null),
('44444444D', 'Jugador Cuatro', '600444444', 'jugador4@padel.com', 'Femenino', 'Alta', false, '2021-07-25', null),
('55555555E', 'Jugador Cinco', '600555555', 'jugador5@padel.com', 'Masculino', 'Alta', false, '2022-05-10', null),
('66666666F', 'Jugador Seis', '600666666', 'jugador6@padel.com', 'Femenino', 'Alta', false, '2022-08-14', null),
('77777777G', 'Jugador Siete', '600777777', 'jugador7@padel.com', 'Masculino', 'Alta', false, '2022-11-11', null),
('88888888H', 'Jugador Ocho', '600888888', 'jugador8@padel.com', 'Femenino', 'Alta', false, '2023-02-20', null),
('99999999I', 'Jugador Nueve', '600999999', 'jugador9@padel.com', 'Masculino', 'Alta', false, '2023-04-30', null),
('10101010J', 'Jugador Diez', '601010101', 'jugador10@padel.com', 'Femenino', 'Alta', false, '2023-05-15', null),
('11111111K', 'Jugador Once', '601111111', 'jugador11@padel.com', 'Masculino', 'Alta', false, '2023-06-01', null),
('12121212L', 'Jugador Doce', '601212121', 'jugador12@padel.com', 'Femenino', 'Alta', false, '2023-06-15', null),
('13131313M', 'Jugador Trece', '601313131', 'jugador13@padel.com', 'Masculino', 'Alta', false, '2023-07-01', null),
('14141414N', 'Jugador Catorce', '601414141', 'jugador14@padel.com', 'Femenino', 'Alta', false, '2023-07-15', null),
('15151515O', 'Jugador Quince', '601515151', 'jugador15@padel.com', 'Masculino', 'Alta', false, '2023-08-01', null),
('16161616P', 'Jugador Dieciséis', '601616161', 'jugador16@padel.com', 'Femenino', 'Alta', false, '2023-08-15', null),
('17171717Q', 'Jugador Diecisiete', '601717171', 'jugador17@padel.com', 'Masculino', 'Alta', false, '2023-09-01', null),
('18181818R', 'Jugador Dieciocho', '601818181', 'jugador18@padel.com', 'Femenino', 'Alta', false, '2023-09-15', null),
('19191919S', 'Jugador Diecinueve', '601919191', 'jugador19@padel.com', 'Masculino', 'Alta', false, '2023-10-01', null),
('20202020T', 'Jugador Veinte', '602020202', 'jugador20@padel.com', 'Femenino', 'Alta', false, '2023-10-15', null),
('21212121U', 'Jugador Veintiuno', '602121212', 'jugador21@padel.com', 'Masculino', 'Alta', false, '2023-11-01', null),
('22222222V', 'Jugador Veintidós', '602222222', 'jugador22@padel.com', 'Femenino', 'Alta', false, '2023-11-15', null),
('23232323W', 'Jugador Veintitrés', '602323232', 'jugador23@padel.com', 'Masculino', 'Alta', false, '2023-12-01', null),
('24242424X', 'Jugador Veinticuatro', '602424242', 'jugador24@padel.com', 'Femenino', 'Alta', false, '2023-12-15', null);

-- Insertar campeonatos de ejemplo
INSERT INTO campeonato (anio, categoria, division, estado, activo, puntos_por_victoria, puntos_por_derrota) VALUES
(2024, 'Masculino', 1, 'Sin iniciar', true, 2, 0),
(2024, 'Femenino', 1, 'Sin iniciar', true, 2, 0),
(2024, 'Masculino', 2, 'Sin iniciar', true, 2, 0),
(2024, 'Femenino', 2, 'Sin iniciar', true, 2, 0),
(2024, 'Mixto', 1, 'Sin iniciar', true, 2, 0);

-- Insertar inscripciones de ejemplo
INSERT INTO inscripcion (campeonato_id, jugador_id) VALUES
(1, 1), (1, 3), (1, 5), (1, 7), (1, 9), (1, 11), (1, 13), (1, 15),
(2, 2), (2, 4), (2, 6), (2, 8), (2, 10), (2, 12), (2, 14), (2, 16),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8);

-- Insertar jornadas de ejemplo

INSERT INTO jornada (campeonato_id, fecha_inicio, numero) VALUES
(1, '2024-01-15', 1),
(2, '2024-01-15', 1),
(3, '2024-01-15', 1);

-- Insertar partidos de ejemplo
-- Campeonato Masculino 2024, Jornada 1
INSERT INTO partido (jornada_id, equipo1_jugador1_id, equipo1_jugador2_id, equipo2_jugador1_id, equipo2_jugador2_id, fecha) VALUES
(1, 1, 7, 3, 5, '2024-01-15'), 
(1, 9, 15, 11, 13, '2024-01-15');

-- Campeonato Femenino 2024, Jornada 1
INSERT INTO partido (jornada_id, equipo1_jugador1_id, equipo1_jugador2_id, equipo2_jugador1_id, equipo2_jugador2_id, fecha) VALUES
(2, 2, 8, 4, 6, '2024-01-15'), 
(2, 10, 16, 12, 14, '2024-01-15');

-- Campeonato Mixto 2024, Jornada 1
INSERT INTO partido (jornada_id, equipo1_jugador1_id, equipo1_jugador2_id, equipo2_jugador1_id, equipo2_jugador2_id, fecha) VALUES
(3, 1, 4, 2, 3, '2024-01-15'), 
(3, 5, 8, 6, 7, '2024-01-15');

-- Insertar clasificacion (inicial) de ejemplo
INSERT INTO clasificacion (campeonato_id, jugador_id, posicion, puntos, partidos_jugados, partidos_ganados, partidos_perdidos, sets_ganados, sets_perdidos, juegos_ganados, juegos_perdidos) VALUES
(1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 7, 4, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 9, 5, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 11, 6, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 13, 7, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 15, 8, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 6, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 12, 6, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 14, 7, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 16, 8, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0);

-- Puedes agregar más datos según sea necesario