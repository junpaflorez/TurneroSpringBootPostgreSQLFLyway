/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author junpa
 */
@RestController
@RequestMapping("api/v1/asesor")
public class AsesorController {
    private AsesorService asesorService;

    public AsesorController(AsesorService asesorService) {
        this.asesorService = asesorService;
    }
    
    
    @PostMapping("/crearAsesor")
    ResponseEntity<?> crearAsesor(@RequestBody AsesorDTO asesor){
        AsesorDTO asesorDTO = null; 
        if(asesor != null){
            String identificacion = asesor.getIdentificacion();
            String nombre = asesor.getNombre();
            asesorDTO = asesorService.crearAsesor(identificacion, nombre);
            if(asesorDTO!= null){
                return ResponseEntity.ok(asesorDTO);
            }
        }
        return ResponseEntity.badRequest().build();
    }
    
}