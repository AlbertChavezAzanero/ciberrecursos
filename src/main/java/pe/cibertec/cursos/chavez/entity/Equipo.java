package pe.cibertec.cursos.chavez.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "equipo")
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_equipo")
    private Integer cod_equipo;

    @Column(name = "nom_equipo")
    private String nom_equipo;

    @Column(name = "tipo_equipo")
    private String tipo_equipo;

    @Column(name = "estado")
    private String estado;
}