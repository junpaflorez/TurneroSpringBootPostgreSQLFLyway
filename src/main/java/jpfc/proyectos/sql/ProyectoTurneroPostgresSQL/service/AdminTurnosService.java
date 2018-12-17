/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service;

import java.sql.Time;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AtendidoDTO;

/**
 *
 * @author junpa
 */
public interface AdminTurnosService {
    
    AtendidoDTO crearTurnoAtendido(String fkAsesor,int fkTurno);
    
    AtendidoDTO consultarTurnoAtendido(int id);
    
    Time promedioTiemposAsesor(String fkAsesor);
   
    
}
