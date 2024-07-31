package ec.com.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.com.webmarket.restful.domain.Mesa;
import ec.com.webmarket.restful.dto.v1.MesaDTO;
import ec.com.webmarket.restful.persistence.MesaRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MesaService extends GenericCrudServiceImpl<Mesa, MesaDTO> {

    @Autowired
    private MesaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Mesa mapToDomain(MesaDTO dto) {
        return modelMapper.map(dto, Mesa.class);
    }

    @Override
    public MesaDTO mapToDto(Mesa domain) {
        return modelMapper.map(domain, MesaDTO.class);
    }

    @Override
    public Optional<Mesa> find(MesaDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public MesaDTO create(MesaDTO dto) {
        Mesa mesa = mapToDomain(dto);
        Mesa savedMesa = repository.save(mesa);
        return mapToDto(savedMesa);
    }

    @Override
    public MesaDTO update(MesaDTO dto) {
        if (repository.existsById(dto.getId())) {
            Mesa mesa = mapToDomain(dto);
            Mesa updatedMesa = repository.save(mesa);
            return mapToDto(updatedMesa);
        }
        return null; 
    }

    @Override
    public void delete(MesaDTO dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<MesaDTO> findAll(MesaDTO dto) {
        List<Mesa> mesas = repository.findAll();
        return mesas.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
    }
}
