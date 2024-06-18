package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}