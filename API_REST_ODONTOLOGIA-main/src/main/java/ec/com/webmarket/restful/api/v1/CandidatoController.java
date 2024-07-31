package ec.com.webmarket.restful.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.webmarket.restful.dto.v1.CandidatoDTO;
import ec.com.webmarket.restful.security.ApiResponseDTO;
import ec.com.webmarket.restful.service.crud.CandidatoService;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<CandidatoDTO>> create(@RequestBody CandidatoDTO candidatoDTO) {
        CandidatoDTO createdCandidato = candidatoService.create(candidatoDTO);
        ApiResponseDTO<CandidatoDTO> response = new ApiResponseDTO<>(true, createdCandidato);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDTO<CandidatoDTO>> update(@RequestBody CandidatoDTO candidatoDTO) {
        CandidatoDTO updatedCandidato = candidatoService.update(candidatoDTO);
        ApiResponseDTO<CandidatoDTO> response = new ApiResponseDTO<>(true, updatedCandidato);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDTO<Void>> delete(@RequestBody CandidatoDTO candidatoDTO) {
        candidatoService.delete(candidatoDTO);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(true, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CandidatoDTO>> find(@PathVariable Long id) {
        CandidatoDTO dto = new CandidatoDTO(id);
        return candidatoService.find(dto)
            .map(candidato -> new ResponseEntity<>(new ApiResponseDTO<>(true, candidatoService.mapToDto(candidato)), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<CandidatoDTO>>> findAll() {
        List<CandidatoDTO> candidatos = candidatoService.findAll(new CandidatoDTO(null, null, null, null));
        ApiResponseDTO<List<CandidatoDTO>> response = new ApiResponseDTO<>(true, candidatos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
