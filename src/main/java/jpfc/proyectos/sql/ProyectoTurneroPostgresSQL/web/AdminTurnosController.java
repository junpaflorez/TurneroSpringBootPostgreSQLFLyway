/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AtendidoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AdminTurnosService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    public AdminTurnosController(AsesorService asesorService, FuncionesService funcionesService, TurnoService turnoService, AdminTurnosService adminTurnosService) {
        this.asesorService = asesorService;
        this.funcionesService = funcionesService;
        this.turnoService = turnoService;
        this.adminTurnosService = adminTurnosService;
    }
    
    @PostMapping("/crearTurno")
    public ResponseEntity<?> crearTurno(@RequestParam("turno") String turno, @RequestBody AsesorDTO asesor){
        AtendidoDTO atendidoDTO = new AtendidoDTO();
        AsesorDTO consultarAsesor = new AsesorDTO();
        if(funcionesService.esValido(turno) && asesor!=null){
            int fkTurno = Integer.parseInt(turno);
            String fkAsesor = asesor.getIdentificacion();
            consultarAsesor = asesorService.consultarAsesor(fkAsesor);
            if(consultarAsesor!= null){
                atendidoDTO = adminTurnosService.crearTurnoAtendido(fkAsesor,fkTurno);
                if(atendidoDTO!=null){
                    return ResponseEntity.ok(atendidoDTO);
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
    
}
