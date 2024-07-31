package ec.com.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.com.webmarket.restful.dto.v1.EstudianteDTO;
import ec.com.webmarket.restful.service.crud.EstudianteService;
import jakarta.validation.Valid;
import ec.com.webmarket.restful.security.ApiResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<EstudianteDTO>> create(@RequestBody @Valid EstudianteDTO estudianteDTO) {
        EstudianteDTO createdEstudiante = estudianteService.create(estudianteDTO);
        ApiResponseDTO<EstudianteDTO> response = new ApiResponseDTO<>(true, createdEstudiante);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDTO<EstudianteDTO>> update(@RequestBody @Valid EstudianteDTO estudianteDTO) {
        EstudianteDTO updatedEstudiante = estudianteService.update(estudianteDTO);
        ApiResponseDTO<EstudianteDTO> response = new ApiResponseDTO<>(true, updatedEstudiante);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDTO<Void>> delete(@RequestBody @Valid EstudianteDTO estudianteDTO) {
        estudianteService.delete(estudianteDTO);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(true, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EstudianteDTO>> getById(@PathVariable Long id) {
        return estudianteService.find(new EstudianteDTO(id))
            .map(estudiante -> new ResponseEntity<>(new ApiResponseDTO<>(true, estudianteService.mapToDto(estudiante)), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<EstudianteDTO>>> findAll() {
        List<EstudianteDTO> estudiantes = estudianteService.findAll(new EstudianteDTO(null));
        ApiResponseDTO<List<EstudianteDTO>> response = new ApiResponseDTO<>(true, estudiantes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/padron")
    public ResponseEntity<ApiResponseDTO<List<EstudianteDTO>>> getPadron() {
        List<EstudianteDTO> estudiantes = estudianteService.findAll(new EstudianteDTO(null));
        ApiResponseDTO<List<EstudianteDTO>> response = new ApiResponseDTO<>(true, estudiantes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
