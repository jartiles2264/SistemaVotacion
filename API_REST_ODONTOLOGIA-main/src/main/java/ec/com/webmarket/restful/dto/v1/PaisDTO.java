package ec.com.webmarket.restful.dto.v1;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaisDTO {

	private Long id;
	private String nombre;
	private LocalDate fechaCreacion;
	
    public PaisDTO() {}

}
