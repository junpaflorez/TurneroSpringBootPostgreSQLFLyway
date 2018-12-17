/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.Calendar;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import org.springframework.stereotype.Service;

/**
 *
 * @author junpa
 */
@Service
public class DefaultFuncionesService implements FuncionesService{
        
    @Override
    public boolean esValido(String cadena){
        if(cadena.isEmpty() || cadena == ""){
            return false;
        }
        return true;
    }

    @Override
    public Calendar iniciarReloj() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        return now;
    }
    
    
}
