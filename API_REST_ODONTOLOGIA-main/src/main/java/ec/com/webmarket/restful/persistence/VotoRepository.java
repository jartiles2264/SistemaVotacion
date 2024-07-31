package ec.com.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.webmarket.restful.domain.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
