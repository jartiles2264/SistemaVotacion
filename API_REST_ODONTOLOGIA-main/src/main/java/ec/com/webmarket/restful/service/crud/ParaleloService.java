package ec.com.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.com.webmarket.restful.domain.Paralelo;
import ec.com.webmarket.restful.dto.v1.ParaleloDTO;
import ec.com.webmarket.restful.persistence.ParaleloRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParaleloService extends GenericCrudServiceImpl<Paralelo, ParaleloDTO> {

    @Autowired
    private ParaleloRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Paralelo mapToDomain(ParaleloDTO dto) {
        return modelMapper.map(dto, Paralelo.class);
    }

    @Override
    public ParaleloDTO mapToDto(Paralelo domain) {
        return modelMapper.map(domain, ParaleloDTO.class);
    }

    @Override
    public Optional<Paralelo> find(ParaleloDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public ParaleloDTO create(ParaleloDTO dto) {
        Paralelo paralelo = mapToDomain(dto);
        Paralelo savedParalelo = repository.save(paralelo);
        return mapToDto(savedParalelo);
    }

    @Override
    public ParaleloDTO update(ParaleloDTO dto) {
        if (repository.existsById(dto.getId())) {
            Paralelo paralelo = mapToDomain(dto);
            Paralelo updatedParalelo = repository.save(paralelo);
            return mapToDto(updatedParalelo);
        }
        return null;
    }

    @Override
    public void delete(ParaleloDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<ParaleloDTO> findAll(ParaleloDTO dto) {
        List<Paralelo> paralelos = repository.findAll();
        return paralelos.stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }
}
