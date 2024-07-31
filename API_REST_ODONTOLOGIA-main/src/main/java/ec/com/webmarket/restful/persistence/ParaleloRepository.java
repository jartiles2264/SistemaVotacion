package ec.com.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.webmarket.restful.domain.Paralelo;
import java.util.List;

public interface ParaleloRepository extends JpaRepository<Paralelo, Long> {
    List<Paralelo> findByCurso_Id(Long cursoId);
}
