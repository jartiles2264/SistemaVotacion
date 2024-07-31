package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class EstudianteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private Long cursoId; 
    private Long paraleloId;
    
    public EstudianteDTO() {
    }
    
    public EstudianteDTO(Long id, String nombre, String apellido, String cedula, Long cursoId, Long paraleloId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cursoId = cursoId;
        this.paraleloId = paraleloId;
    }

    public EstudianteDTO(Long id) {
        this.id = id;
    }
}
