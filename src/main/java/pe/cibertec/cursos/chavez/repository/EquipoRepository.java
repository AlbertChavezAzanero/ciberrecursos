package pe.cibertec.cursos.chavez.repository;

import pe.cibertec.cursos.chavez.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
