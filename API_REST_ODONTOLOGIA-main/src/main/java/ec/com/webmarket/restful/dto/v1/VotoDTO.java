package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class VotoDTO {
    private Long id;
    private Long candidatoId;
    private Long votanteId;

    public VotoDTO() {}

    public VotoDTO(Long id) {
        this.id = id;
    }

    public VotoDTO(Long id, Long candidatoId, Long votanteId) {
        this.id = id;
        this.candidatoId = candidatoId;
        this.votanteId = votanteId;
    }
}
