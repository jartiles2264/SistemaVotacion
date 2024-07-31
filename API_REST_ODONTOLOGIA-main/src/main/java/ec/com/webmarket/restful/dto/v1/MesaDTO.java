package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class MesaDTO {

    private Long id;
    private String nombre;
    private String ubicacion; 

    public MesaDTO() {}
}
