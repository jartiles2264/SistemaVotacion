package ec.com.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.webmarket.restful.domain.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
