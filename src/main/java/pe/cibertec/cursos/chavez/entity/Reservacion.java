package pe.cibertec.cursos.chavez.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservacion")
@Data
public class Reservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_reservacion")
    private Integer num_reservacion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime hora_inicio;

    @Column(name = "hora_fin")
    private LocalTime hora_fin;

    @Column(name = "usuario")
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "cod_equipo")
    private Equipo equipo;
}