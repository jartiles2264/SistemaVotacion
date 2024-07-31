package ec.com.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.com.webmarket.restful.dto.v1.ParaleloDTO;
import ec.com.webmarket.restful.service.crud.ParaleloService;
import ec.com.webmarket.restful.security.ApiResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/paralelos")
public class ParaleloController {

    @Autowired
    private ParaleloService paraleloService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ParaleloDTO>> create(@RequestBody ParaleloDTO paraleloDTO) {
        ParaleloDTO created = paraleloService.create(paraleloDTO);
        ApiResponseDTO<ParaleloDTO> response = new ApiResponseDTO<>(true, created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ParaleloDTO>> update(@PathVariable Long id, @RequestBody ParaleloDTO paraleloDTO) {
        paraleloDTO.setId(id);
        ParaleloDTO updated = paraleloService.update(paraleloDTO);
        ApiResponseDTO<ParaleloDTO> response = new ApiResponseDTO<>(true, updated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<ApiResponseDTO<Void>> delete(@RequestBody ParaleloDTO paraleloDTO) {
        paraleloService.delete(paraleloDTO);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(true, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ParaleloDTO>> find(@PathVariable Long id) {
        ParaleloDTO dto = new ParaleloDTO(id);
        return paraleloService.find(dto)
            .map(paralelo -> new ResponseEntity<>(new ApiResponseDTO<>(true, paraleloService.mapToDto(paralelo)), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<ParaleloDTO>>> findAll() {
        List<ParaleloDTO> paralelos = paraleloService.findAll(new ParaleloDTO(null, null));
        ApiResponseDTO<List<ParaleloDTO>> response = new ApiResponseDTO<>(true, paralelos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
