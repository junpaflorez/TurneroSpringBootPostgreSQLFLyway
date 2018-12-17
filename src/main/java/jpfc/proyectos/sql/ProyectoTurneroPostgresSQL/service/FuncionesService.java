/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service;

import java.util.Calendar;

/**
 *
 * @author junpa
 */
public interface FuncionesService {
    
    boolean esValido(String identificacion);
    
    Calendar iniciarReloj();
}
