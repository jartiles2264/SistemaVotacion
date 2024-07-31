package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    
    public CursoDTO() {
    }

    public CursoDTO(Long id) {
        this.id = id;
    }

    public CursoDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
