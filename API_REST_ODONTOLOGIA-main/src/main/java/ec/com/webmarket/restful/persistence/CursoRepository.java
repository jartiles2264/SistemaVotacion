package ec.com.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.webmarket.restful.domain.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
