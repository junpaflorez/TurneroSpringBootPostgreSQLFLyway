/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service;

import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;

/**
 *
 * @author junpa
 */
public interface AsesorService {
    
    AsesorDTO crearAsesor(String identificacion, String Nombre);
    
    AsesorDTO consultarAsesor(AsesorDTO asesorDTO);
    
}
