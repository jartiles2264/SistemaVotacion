package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.Curso;
import ec.com.webmarket.restful.dto.v1.CursoDTO;
import ec.com.webmarket.restful.persistence.CursoRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class CursoService extends GenericCrudServiceImpl<Curso, CursoDTO> {

    @Autowired
    private CursoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Curso mapToDomain(CursoDTO dto) {
        return modelMapper.map(dto, Curso.class);
    }

    @Override
    public CursoDTO mapToDto(Curso domain) {
        return modelMapper.map(domain, CursoDTO.class);
    }

    @Override
    public Optional<Curso> find(CursoDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public CursoDTO create(CursoDTO dto) {
        Curso curso = mapToDomain(dto);
        Curso savedCurso = repository.save(curso);
        return mapToDto(savedCurso);
    }

    @Override
    public CursoDTO update(CursoDTO dto) {
        if (repository.existsById(dto.getId())) {
            Curso curso = mapToDomain(dto);
            Curso updatedCurso = repository.save(curso);
            return mapToDto(updatedCurso);
        }
        return null;
    }

    @Override
    public void delete(CursoDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<CursoDTO> findAll(CursoDTO dto) {
        List<Curso> cursos = repository.findAll();
        return cursos.stream()
                     .map(this::mapToDto)
                     .collect(Collectors.toList());
    }
}
