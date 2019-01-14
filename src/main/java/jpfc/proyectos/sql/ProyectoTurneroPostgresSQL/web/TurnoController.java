/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author junpa
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/turno")
public class TurnoController {
    private TurnoService turnoService;
    private FuncionesService funcionesService;

    public TurnoController(TurnoService turnoService, FuncionesService funcionesService) {
        this.turnoService = turnoService;
        this.funcionesService = funcionesService;
    }
    
    
    @PostMapping("/crearTurno")
    public ResponseEntity<?> crearTurno(@RequestParam("categoria") String categoria){
        TurnoDTO turnoDTO = new TurnoDTO();
        System.out.println("categoria:" + categoria);
        if(funcionesService.esValido(categoria)){
            System.out.println("es valido");
            turnoDTO = turnoService.crearTurno(categoria);
            if(turnoDTO != null){
                return ResponseEntity.ok(turnoDTO);
            }
        }
        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/todosLosTurnos")
    public ResponseEntity<?> todosLosTurnos(){
        
        List<TurnoDTO> turnos = turnoService.listaTurno();

        if(turnos != null){
            return ResponseEntity.ok(turnos);
        } 
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/pendientes")
    public ResponseEntity<?> pendientes(){
        List<TurnoDTO> turnos = turnoService.turnosPendientes();
        if(turnos != null){
            return ResponseEntity.ok(turnos);
        }
        return ResponseEntity.notFound().build();
    }
}
