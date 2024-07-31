package ec.com.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.com.webmarket.restful.domain.Estudiante;
import ec.com.webmarket.restful.dto.v1.EstudianteDTO;
import ec.com.webmarket.restful.persistence.EstudianteRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService extends GenericCrudServiceImpl<Estudiante, EstudianteDTO> {

    @Autowired
    private EstudianteRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Estudiante mapToDomain(EstudianteDTO dto) {
        return modelMapper.map(dto, Estudiante.class);
    }

    @Override
    public EstudianteDTO mapToDto(Estudiante domain) {
        return modelMapper.map(domain, EstudianteDTO.class);
    }

    @Override
    public Optional<Estudiante> find(EstudianteDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public EstudianteDTO create(EstudianteDTO dto) {
        Estudiante estudiante = mapToDomain(dto);
        Estudiante savedEstudiante = repository.save(estudiante);
        return mapToDto(savedEstudiante);
    }

    @Override
    public EstudianteDTO update(EstudianteDTO dto) {
        if (repository.existsById(dto.getId())) {
            Estudiante estudiante = mapToDomain(dto);
            Estudiante updatedEstudiante = repository.save(estudiante);
            return mapToDto(updatedEstudiante);
        }
        throw new RuntimeException("Estudiante no encontrado");
    }

    @Override
    public void delete(EstudianteDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<EstudianteDTO> findAll(EstudianteDTO dto) {
        List<Estudiante> estudiantes = repository.findAll();
        return estudiantes.stream()
                          .map(this::mapToDto)
                          .collect(Collectors.toList());
    }
}
