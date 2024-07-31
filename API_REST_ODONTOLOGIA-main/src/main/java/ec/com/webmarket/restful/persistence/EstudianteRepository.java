package ec.com.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.webmarket.restful.domain.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
