package ec.com.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.webmarket.restful.domain.Candidato;
import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByParalelo_Id(Long paraleloId);
}
