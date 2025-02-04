package es.laspalmeras.padel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.dto.AusenciaDTO;
import es.laspalmeras.padel.mapper.AusenciaMapper;
import es.laspalmeras.padel.model.Ausencia;
import es.laspalmeras.padel.repository.AusenciaRepository;
import es.laspalmeras.padel.service.AusenciaService;

@Service
public class AusenciaServiceImpl implements AusenciaService{

	@Autowired
	private AusenciaRepository ausenciaRepository;
	
	@Autowired
	private AusenciaMapper ausenciaMapper;
    
    public void AusenciaService(AusenciaRepository ausenciaRepository, AusenciaMapper ausenciaMapper) {
        this.ausenciaRepository = ausenciaRepository;
        this.ausenciaMapper = ausenciaMapper;
    }

	@Override
	@Transactional
	public AusenciaDTO createAusencia(AusenciaDTO ausenciaDTO) {
		// Convertir el DTO a entidad utilizando el mapper
        Ausencia ausencia = ausenciaMapper.toEntity(ausenciaDTO);

        // Aquí podrías añadir la lógica adicional, como validaciones o actualizaciones de estadísticas.
        // Guarda la entidad en la base de datos:
        ausencia = ausenciaRepository.save(ausencia);

        // Convertir la entidad guardada de vuelta a DTO para retornar la respuesta
        return ausenciaMapper.toDto(ausencia);
	}

}
