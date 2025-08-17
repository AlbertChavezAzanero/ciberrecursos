package pe.cibertec.cursos.chavez.service;

import pe.cibertec.cursos.chavez.entity.Reservacion;
import pe.cibertec.cursos.chavez.repository.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.cibertec.cursos.chavez.dto.ReservaReporteDTO;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class ReservacionService {

    private final ReservacionRepository reservacionRepository;

    @Autowired
    public ReservacionService(ReservacionRepository reservacionRepository) {
        this.reservacionRepository = reservacionRepository;
    }

    public List<Reservacion> findAll() {
        return reservacionRepository.findAll();
    }

    public Optional<Reservacion> findById(Integer id) {
        return reservacionRepository.findById(id);
    }

    public Reservacion save(Reservacion reservacion) {
        List<Reservacion> overlapping = reservacionRepository.findOverlappingReservations(
                reservacion.getEquipo().getCod_equipo(),
                reservacion.getFecha(),
                reservacion.getHora_inicio(),
                reservacion.getHora_fin()
        );

        if (!overlapping.isEmpty()) {
            throw new IllegalArgumentException("Error: Ya existe una reserva en ese horario para el equipo seleccionado.");
        }

        return reservacionRepository.save(reservacion);
    }

    public void deleteById(Integer id) {
        reservacionRepository.deleteById(id);
    }

    // Agrega este método dentro de la clase ReservacionService
    public List<ReservaReporteDTO> generarReporte(String usuario, Integer codEquipo) {
        List<Reservacion> reservaciones = reservacionRepository.findByFiltros(usuario, codEquipo);

        return reservaciones.stream()
                .map(ReservaReporteDTO::new)
                .collect(Collectors.toList());
    }
}