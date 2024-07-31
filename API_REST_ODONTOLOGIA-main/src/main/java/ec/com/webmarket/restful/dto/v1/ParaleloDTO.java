package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class ParaleloDTO {
    private Long id;
    private String nombre;

    public ParaleloDTO() {}

    public ParaleloDTO(Long id) {
        this.id = id;
    }

    public ParaleloDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
