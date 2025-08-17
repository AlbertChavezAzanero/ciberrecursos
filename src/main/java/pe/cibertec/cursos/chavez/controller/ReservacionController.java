package pe.cibertec.cursos.chavez.controller;

import pe.cibertec.cursos.chavez.entity.Reservacion;
import pe.cibertec.cursos.chavez.service.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.cursos.chavez.dto.ReservaReporteDTO;

import java.util.List;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    private final ReservacionService reservacionService;

    @Autowired
    public ReservacionController(ReservacionService reservacionService) {
        this.reservacionService = reservacionService;
    }

    @GetMapping
    public List<Reservacion> listarReservaciones() {
        return reservacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservacion> obtenerReservacion(@PathVariable Integer id) {
        return reservacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> agregarReservacion(@RequestBody Reservacion reservacion) {
        try {
            Reservacion nuevaReservacion = reservacionService.save(reservacion);
            return new ResponseEntity<>(nuevaReservacion, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReservacion(@PathVariable Integer id) {
        reservacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Agrega este método dentro de la clase ReservacionController
    @GetMapping("/reporte")
    public List<ReservaReporteDTO> generarReporte(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) Integer codEquipo) {
        return reservacionService.generarReporte(usuario, codEquipo);
    }
}