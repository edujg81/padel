package es.laspalmeras.padel.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.business.service.model.Campeonato;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

	// Puedes agregar métodos específicos de consulta si son necesarios
	// Spring Data JPA proporciona métodos por defecto para operaciones CRUD
	// No es necesario definirlos aquí a menos que necesites consultas
	// personalizadas

}