/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service;

import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;

/**
 *
 * @author junpa
 */
public interface TurnoService {
    
    TurnoDTO crearTurno(String categoria);
    
    List<TurnoDTO> listaTurno();
}
