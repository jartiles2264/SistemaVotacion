package ec.com.webmarket.restful.service.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.Pais;
import ec.com.webmarket.restful.dto.v1.PaisDTO;
import ec.com.webmarket.restful.persistence.PaisRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class PaisService extends GenericCrudServiceImpl<Pais, PaisDTO> {

    @Autowired
    private PaisRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Pais> find(PaisDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public PaisDTO create(PaisDTO dto) {
        Pais pais = mapToDomain(dto);
        Pais savedPais = repository.save(pais);
        return mapToDto(savedPais);
    }

    @Override
    public PaisDTO update(PaisDTO dto) {
        if (repository.existsById(dto.getId())) {
            Pais pais = mapToDomain(dto);
            Pais updatedPais = repository.save(pais);
            return mapToDto(updatedPais);
        }
        return null; 
    }

    @Override
    public void delete(PaisDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<PaisDTO> findAll(PaisDTO dto) {
        List<Pais> paises = repository.findAll();
        return paises.stream()
                     .map(this::mapToDto)
                     .collect(Collectors.toList());
    }

    public List<Pais> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public List<Pais> findByFechaCreacion(LocalDate fechaCreacion) {
        return repository.findByFechaCreacion(fechaCreacion);
    }

    @Override
    public Pais mapToDomain(PaisDTO dto) {
        return modelMapper.map(dto, Pais.class);
    }

    @Override
    public PaisDTO mapToDto(Pais domain) {
        return modelMapper.map(domain, PaisDTO.class);
    }
}
