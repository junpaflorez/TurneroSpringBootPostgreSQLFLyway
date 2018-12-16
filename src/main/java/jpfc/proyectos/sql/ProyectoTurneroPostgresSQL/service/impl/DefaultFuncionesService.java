/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

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
}
