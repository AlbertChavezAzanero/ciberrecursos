package pe.cibertec.cursos.chavez.controller;

import pe.cibertec.cursos.chavez.entity.Equipo;
import pe.cibertec.cursos.chavez.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public List<Equipo> listarEquipos() {
        return equipoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Equipo> agregarEquipo(@RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoService.save(equipo);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }
}