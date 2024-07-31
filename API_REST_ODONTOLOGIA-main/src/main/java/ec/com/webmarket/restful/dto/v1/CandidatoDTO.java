package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class CandidatoDTO {
    private Long id;
    private String nombre;
    private String puesto;
    private String partido;

    public CandidatoDTO() {
    }
    
    public CandidatoDTO(Long id) {
        this.id = id;
    }

    public CandidatoDTO(Long id, String nombre, String puesto, String partido) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.partido = partido;
    }
}
