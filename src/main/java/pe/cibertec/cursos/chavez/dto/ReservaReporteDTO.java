package pe.cibertec.cursos.chavez.dto;

import pe.cibertec.cursos.chavez.entity.Reservacion;
import lombok.Data;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaReporteDTO {

    private Integer num_reservacion;
    private LocalDate fecha;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private String usuario;
    private String nom_equipo;
    private String tipo_equipo;
    private double totalHorasReservadas;

    public ReservaReporteDTO(Reservacion reservacion) {
        this.num_reservacion = reservacion.getNum_reservacion();
        this.fecha = reservacion.getFecha();
        this.hora_inicio = reservacion.getHora_inicio();
        this.hora_fin = reservacion.getHora_fin();
        this.usuario = reservacion.getUsuario();
        this.nom_equipo = reservacion.getEquipo().getNom_equipo();
        this.tipo_equipo = reservacion.getEquipo().getTipo_equipo();

        // Cálculo del total de horas
        if (reservacion.getHora_inicio() != null && reservacion.getHora_fin() != null) {
            Duration duracion = Duration.between(reservacion.getHora_inicio(), reservacion.getHora_fin());
            this.totalHorasReservadas = duracion.toMinutes() / 60.0;
        } else {
            this.totalHorasReservadas = 0;
        }
    }
}