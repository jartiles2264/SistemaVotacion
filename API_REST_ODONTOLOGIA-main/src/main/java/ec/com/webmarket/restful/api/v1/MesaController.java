package ec.com.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.com.webmarket.restful.dto.v1.MesaDTO;
import ec.com.webmarket.restful.service.crud.MesaService;
import ec.com.webmarket.restful.security.ApiResponseDTO;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, mesaService.findAll(new MesaDTO())), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MesaDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, mesaService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody MesaDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, mesaService.update(dto)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        MesaDTO dto = new MesaDTO();
        dto.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, mesaService.find(dto)), HttpStatus.OK);
    }
}
