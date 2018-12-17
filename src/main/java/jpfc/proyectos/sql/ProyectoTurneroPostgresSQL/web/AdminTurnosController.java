/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AtendidoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.ColaDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AdminTurnosService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.ColaService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author junpa
 */
@Controller
@RequestMapping("api/v1/adminTurnos")
public class AdminTurnosController {
    private AsesorService asesorService;
    private FuncionesService funcionesService;
    private TurnoService turnoService;
    private AdminTurnosService adminTurnosService;
    private ColaService colaService;

    public AdminTurnosController(AsesorService asesorService, FuncionesService funcionesService, TurnoService turnoService, AdminTurnosService adminTurnosService, ColaService colaService) {
        this.asesorService = asesorService;
        this.funcionesService = funcionesService;
        this.turnoService = turnoService;
        this.adminTurnosService = adminTurnosService;
        this.colaService = colaService;
    }


    
    @PostMapping("/crearTurno")
    public ResponseEntity<?> crearTurno(@RequestParam("turno") String turno, @RequestBody AsesorDTO asesor){
        AtendidoDTO atendidoDTO = new AtendidoDTO();
        AsesorDTO consultarAsesor = new AsesorDTO();
        if(funcionesService.esValido(turno) && asesor!=null){
            int fkTurno = Integer.parseInt(turno);
            String fkAsesor = asesor.getIdentificacion();
            consultarAsesor = asesorService.consultarAsesor(asesor);
            if(consultarAsesor!= null){
                atendidoDTO = adminTurnosService.crearTurnoAtendido(fkAsesor,fkTurno);
                if(atendidoDTO!=null){
                    return ResponseEntity.ok(atendidoDTO);
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/promedioAsesor")
    public ResponseEntity<?> promedioAsesor(@RequestBody AsesorDTO asesor){
        Time tiempoPromedio = new Time(0);
        AsesorDTO asesorDTO = new AsesorDTO();
        if(asesor!=null){
            String idAsesor = asesor.getIdentificacion();
            asesorDTO = asesorService.consultarAsesor(asesor);
            if(asesorDTO!=null){
                tiempoPromedio = adminTurnosService.promedioTiemposAsesor(idAsesor);
                return ResponseEntity.ok(tiempoPromedio);
            }
        }
        return ResponseEntity.badRequest().build();
    }
    
    
    @GetMapping("/pendientes")
    public ResponseEntity<?> pendientes(){
        List<TurnoDTO> turnos = turnoService.turnosPendientes();
        if(turnos != null){
            return ResponseEntity.ok(turnos);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/pedirTurno")
    public ResponseEntity<?> pedirTurno(@RequestBody AsesorDTO asesor){
        TurnoDTO turno = null;
        ColaDTO siguiente = new ColaDTO();
        AtendidoDTO turnoAtendido = null;
        int secuencia = 0;
        AsesorDTO asesorIdentificado = new AsesorDTO();
        if(asesor == null){
            return ResponseEntity.badRequest().build();
        }
        System.out.println("existe un asesor");
        asesorIdentificado = asesorService.consultarAsesor(asesor);
        if(asesorIdentificado != null){
            System.out.println("lo consulte y existe el asesor");
            siguiente = colaService.siguiente();
            if(siguiente == null){
                System.out.println("no existe una lista de turnos asi que la creare");
                colaService.crearCola();
                siguiente = colaService.siguiente();
            }
            if(siguiente!=null){
                System.out.println("Tenemos un turno para trabajar");
                secuencia = siguiente.getTurno();
                turno = turnoService.consultarTurno(secuencia);
                if(turno != null){
                    System.out.println("compruebo mi turno para asignarlo a un asesor");
                    turnoAtendido = adminTurnosService.asignarAsesor(turno.getId(), asesor.getIdentificacion());
                    if(turnoAtendido!=null){
                        System.out.println("esta asignado y listo para ser atendido");
                        turno = turnoService.cambiarEstadoLlamadoTurno(secuencia);
                        return ResponseEntity.ok(turno);
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
