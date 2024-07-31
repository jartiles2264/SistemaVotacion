package ec.com.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.com.webmarket.restful.dto.v1.CursoDTO;
import ec.com.webmarket.restful.service.crud.CursoService;
import ec.com.webmarket.restful.security.ApiResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<CursoDTO>> create(@RequestBody CursoDTO cursoDTO) {
        CursoDTO createdCurso = cursoService.create(cursoDTO);
        ApiResponseDTO<CursoDTO> response = new ApiResponseDTO<>(true, createdCurso);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDTO<CursoDTO>> update(@RequestBody CursoDTO cursoDTO) {
        CursoDTO updatedCurso = cursoService.update(cursoDTO);
        ApiResponseDTO<CursoDTO> response = new ApiResponseDTO<>(true, updatedCurso);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDTO<Void>> delete(@RequestBody CursoDTO cursoDTO) {
        cursoService.delete(cursoDTO);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(true, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CursoDTO>> find(@PathVariable Long id) {
        CursoDTO dto = new CursoDTO(id);
        return cursoService.find(dto)
            .map(curso -> new ResponseEntity<>(new ApiResponseDTO<>(true, cursoService.mapToDto(curso)), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<CursoDTO>>> findAll() {
        List<CursoDTO> cursos = cursoService.findAll(new CursoDTO(null, null, null));
        ApiResponseDTO<List<CursoDTO>> response = new ApiResponseDTO<>(true, cursos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
