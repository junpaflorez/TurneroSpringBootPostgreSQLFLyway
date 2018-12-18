/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.Calendar;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Camilo
 */

@RunWith(SpringRunner.class)
public class DefaultFuncionesServiceTest {
    @InjectMocks
    private DefaultFuncionesService funcionesService;
    
    
    @Test
    public void testEsValido_CadenaNotEmpty(){
        boolean result = false;
        
        result = funcionesService.esValido("CARLOS");
        
        assertEquals(true, result);
    }
    
    @Test
    public void testEsValido_CadenaEmpty(){
        boolean result = false;
        
        result = funcionesService.esValido("");
        
        assertEquals(false, result);
    }
    
    @Test
    public void testEsValido_CadenaNull(){
        boolean result = false;
        
        result = funcionesService.esValido(null);
        
        assertEquals(false, result);
    }
    
    
    @Test
    public void testIniciarReloj() {       
        Calendar result = funcionesService.iniciarReloj();
        
        assert(result != null);
    }
    
}
