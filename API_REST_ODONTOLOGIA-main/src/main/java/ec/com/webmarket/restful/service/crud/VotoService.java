package ec.com.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.com.webmarket.restful.domain.Voto;
import ec.com.webmarket.restful.dto.v1.VotoDTO;
import ec.com.webmarket.restful.persistence.VotoRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotoService extends GenericCrudServiceImpl<Voto, VotoDTO> {

    @Autowired
    private VotoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Voto mapToDomain(VotoDTO dto) {
        return modelMapper.map(dto, Voto.class);
    }

    @Override
    public VotoDTO mapToDto(Voto domain) {
        return modelMapper.map(domain, VotoDTO.class);
    }

    @Override
    public Optional<Voto> find(VotoDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public VotoDTO create(VotoDTO dto) {
        Voto voto = mapToDomain(dto);
        Voto savedVoto = repository.save(voto);
        return mapToDto(savedVoto);
    }

    @Override
    public VotoDTO update(VotoDTO dto) {
        if (repository.existsById(dto.getId())) {
            Voto voto = mapToDomain(dto);
            Voto updatedVoto = repository.save(voto);
            return mapToDto(updatedVoto);
        }
        return null;
    }

    @Override
    public void delete(VotoDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<VotoDTO> findAll(VotoDTO dto) {
        List<Voto> votos = repository.findAll();
        return votos.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
    }
}
