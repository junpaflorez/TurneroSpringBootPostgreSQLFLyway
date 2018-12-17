/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import java.util.ArrayList;
import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Turno;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
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
   
    @Test
    public void crarTurno_CategoriaNotEmpty_TurnoNotEmpty(){
       String categoria = "A";
       TurnoDTO turnoDTO = crearTurnoDTO();
       
       when(funcionesService.esValido(categoria)).thenReturn(Boolean.TRUE);
       when(turnoService.crearTurno(categoria)).thenReturn(turnoDTO);
       
       ResponseEntity<?> result = turnoController.crearTurno(categoria);
       
       assertEquals(HttpStatus.OK, result.getStatusCode());   
    }
   
    @Test
    public void crarTurno_CategoriaNotEmpty_TurnoEmpty(){
       String categoria = "A";
       
       when(funcionesService.esValido(categoria)).thenReturn(Boolean.TRUE);
       when(turnoService.crearTurno(categoria)).thenReturn(null);
       
       ResponseEntity<?> result = turnoController.crearTurno(categoria);
       
       assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());   
    }
    
    
    @Test
    public void todosLosTurnos_ListTurnosEmpty(){
               
        when(turnoService.listaTurno()).thenReturn(null);
       
        ResponseEntity<?> result = turnoController.todosLosTurnos();
       
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode()); 
    }
    
    @Test
    public void todosLosTurnos_ListTurnosNotEmpty(){
        
        List<TurnoDTO> listaTurnosDTO = crearListaTurnoDTO();
               
        when(turnoService.listaTurno()).thenReturn(listaTurnosDTO);
       
        ResponseEntity<?> result = turnoController.todosLosTurnos();
       
        assertEquals(HttpStatus.OK, result.getStatusCode()); 
    }
   
    public List<TurnoDTO> crearListaTurnoDTO(){
        List<TurnoDTO> listaTurnosDTO = new ArrayList();
        listaTurnosDTO.add(crearTurnoDTO());
        return listaTurnosDTO;
    }
    
    public TurnoDTO crearTurnoDTO(){
        TurnoDTO turno = new TurnoDTO();
        turno.setId(0);
        turno.setCategoria("A");
        return turno;
    }
    
    public Turno crearTurno(){
        Turno turno = new Turno();
        turno.setId(0);
        turno.setCategoria("A");
        return turno;
    }
    
}
