/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Camilo
 */

@RunWith(SpringRunner.class)
public class TurnoControllerTest {
   @Mock
   private TurnoService turnoService;
   @Mock
   private FuncionesService funcionesService;
   
   @InjectMocks
   private TurnoController turnoController;
   
   @Test
   public void crearTurno_CategoriaEmpty(){
       ResponseEntity<?> result = turnoController.crearTurno(null);
       assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());              
   }
    
}
