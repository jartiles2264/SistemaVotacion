package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.com.webmarket.restful.domain.Candidato;
import ec.com.webmarket.restful.dto.v1.CandidatoDTO;
import ec.com.webmarket.restful.persistence.CandidatoRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class CandidatoService extends GenericCrudServiceImpl<Candidato, CandidatoDTO> {

    @Autowired
    private CandidatoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Candidato mapToDomain(CandidatoDTO dto) {
        return modelMapper.map(dto, Candidato.class);
    }

    @Override
    public CandidatoDTO mapToDto(Candidato domain) {
        return modelMapper.map(domain, CandidatoDTO.class);
    }

    @Override
    public Optional<Candidato> find(CandidatoDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public CandidatoDTO create(CandidatoDTO dto) {
        Candidato candidato = mapToDomain(dto);
        Candidato savedCandidato = repository.save(candidato);
        return mapToDto(savedCandidato);
    }

    @Override
    public CandidatoDTO update(CandidatoDTO dto) {
        if (repository.existsById(dto.getId())) {
            Candidato candidato = mapToDomain(dto);
            Candidato updatedCandidato = repository.save(candidato);
            return mapToDto(updatedCandidato);
        }
        return null;
    }

    @Override
    public void delete(CandidatoDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<CandidatoDTO> findAll(CandidatoDTO dto) {
        List<Candidato> candidatos = repository.findAll();
        return candidatos.stream()
                         .map(this::mapToDto)
                         .collect(Collectors.toList());
    }
}
