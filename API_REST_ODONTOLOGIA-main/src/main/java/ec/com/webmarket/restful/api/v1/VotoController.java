package ec.com.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.com.webmarket.restful.dto.v1.VotoDTO;
import ec.com.webmarket.restful.service.crud.VotoService;
import ec.com.webmarket.restful.security.ApiResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<VotoDTO>> create(@RequestBody VotoDTO votoDTO) {
        VotoDTO createdVoto = votoService.create(votoDTO);
        ApiResponseDTO<VotoDTO> response = new ApiResponseDTO<>(true, createdVoto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDTO<VotoDTO>> update(@RequestBody VotoDTO votoDTO) {
        VotoDTO updatedVoto = votoService.update(votoDTO);
        ApiResponseDTO<VotoDTO> response = new ApiResponseDTO<>(true, updatedVoto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDTO<Void>> delete(@RequestBody VotoDTO votoDTO) {
        votoService.delete(votoDTO);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(true, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<VotoDTO>> find(@PathVariable Long id) {
        VotoDTO dto = new VotoDTO(id);
        return votoService.find(dto)
            .map(voto -> new ResponseEntity<>(new ApiResponseDTO<>(true, votoService.mapToDto(voto)), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<VotoDTO>>> findAll() {
        List<VotoDTO> votos = votoService.findAll(new VotoDTO(null, null, null));
        ApiResponseDTO<List<VotoDTO>> response = new ApiResponseDTO<>(true, votos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
