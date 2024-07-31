package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.Pais;
import ec.com.webmarket.restful.domain.Provincia;
import ec.com.webmarket.restful.dto.v1.ProvinciaDTO;
import ec.com.webmarket.restful.persistence.ProvinciaRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class ProvinciaService extends GenericCrudServiceImpl<Provincia, ProvinciaDTO> {

    @Autowired
    private ProvinciaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Provincia> find(ProvinciaDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public ProvinciaDTO create(ProvinciaDTO dto) {
        Provincia provincia = mapToDomain(dto);
        Provincia savedProvincia = repository.save(provincia);
        return mapToDto(savedProvincia);
    }

    @Override
    public ProvinciaDTO update(ProvinciaDTO dto) {
        if (repository.existsById(dto.getId())) {
            Provincia provincia = mapToDomain(dto);
            Provincia updatedProvincia = repository.save(provincia);
            return mapToDto(updatedProvincia);
        }
        return null; 
    }

    @Override
    public void delete(ProvinciaDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<ProvinciaDTO> findAll(ProvinciaDTO dto) {
        List<Provincia> provincias = repository.findAll();
        return provincias.stream()
                         .map(this::mapToDto)
                         .collect(Collectors.toList());
    }

    public List<Provincia> findByPais(Long id) {
        Pais pais = new Pais();
        pais.setId(id);
        return repository.findByPais(pais);
    }

    @Override
    public Provincia mapToDomain(ProvinciaDTO dto) {
        return modelMapper.map(dto, Provincia.class);
    }

    @Override
    public ProvinciaDTO mapToDto(Provincia domain) {
        return modelMapper.map(domain, ProvinciaDTO.class);
    }
}
