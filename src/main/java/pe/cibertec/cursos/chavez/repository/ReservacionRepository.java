package pe.cibertec.cursos.chavez.repository;

import pe.cibertec.cursos.chavez.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {

    @Query("SELECT r FROM Reservacion r WHERE r.equipo.cod_equipo = ?1 AND r.fecha = ?2 AND r.hora_inicio < ?4 AND r.hora_fin > ?3")
    List<Reservacion> findOverlappingReservations(Integer codEquipo, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);

    @Query("SELECT r FROM Reservacion r WHERE (?1 IS NULL OR r.usuario = ?1) AND (?2 IS NULL OR r.equipo.cod_equipo = ?2)")
    List<Reservacion> findByFiltros(String usuario, Integer codEquipo);
}
