/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service;

import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.ColaDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;

/**
 *
 * @author junpa
 */
public interface ColaTurnosService {
    
    List<ColaDTO> listaCola();
    
    List<ColaDTO> pendientesCola();
    
    ColaDTO siguiente();
    
    boolean guardarTurnoEnCola(TurnoDTO turno);
    
    boolean asignarAsesorTurno(ColaDTO turno, AsesorDTO asesor);
    
    ColaDTO consultarTurnoEnCola(String secuencia); 
    
    boolean TurnoLibreEnCola(String fkTurno);

    
}
