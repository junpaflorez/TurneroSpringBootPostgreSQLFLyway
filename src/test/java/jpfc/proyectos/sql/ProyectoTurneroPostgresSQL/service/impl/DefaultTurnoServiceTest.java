/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Turno;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository.TurnoRepository;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Camilo
 */

@RunWith(SpringRunner.class)
public class DefaultTurnoServiceTest {
    @Mock
    private TurnoRepository turnoRepository;
    @Mock
    private FuncionesService funcionesService;
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private DefaultTurnoService turnoService;
    
    
    @Test
    public void crearTurno_CategoriaValid(){
        TurnoDTO result = crearTurnoDTO();
        Turno turno = crearTurno();
        
        when(funcionesService.esValido("A")).thenReturn(Boolean.TRUE);
        when(turnoRepository.findByCategoria("A")).thenReturn(0);
        when(turnoRepository.save(turno)).thenReturn(turno);
        when(modelMapper.map(turno, TurnoDTO.class)).thenReturn(result);
        
        result = turnoService.crearTurno("A");
        
        assertEquals(0, result.getId());
        assertEquals("A", result.getCategoria());
    }
    
    @Test
    public void crearTurno_CategoriaNotValid(){
        TurnoDTO result = crearTurnoDTO();
        
        when(funcionesService.esValido("A")).thenReturn(Boolean.FALSE);
        
        result = turnoService.crearTurno("A");
        
        assertEquals(null, result);
    }
    
    
    @Test
    public void consultarTurno_TurnoInBD(){
        TurnoDTO result = crearTurnoDTO();
        Turno turno = crearTurno();
        Optional turnoOptional = Optional.of(turno);
        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        when(modelMapper.map(turno, TurnoDTO.class)).thenReturn(result);
        
        result = turnoService.consultarTurno(0);
        
        assertEquals(turno.getId(), result.getId());
        assertEquals(turno.getCategoria(), result.getCategoria());
        assertEquals(turno.getSecuencia(), result.getSecuencia());
    }
    
    @Test
    public void consultarTurno_TurnoNotInBD(){
        TurnoDTO result = crearTurnoDTO();
        Optional turnoOptional = Optional.empty();
        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        
        result = turnoService.consultarTurno(0);
        
        assertEquals(null, result);
    }
    
    @Test
    public void cambiarEstadoLlamadoTurno_TurnoOnBD_IdSame(){
        TurnoDTO result = crearTurnoDTO();
        result.setLlamado(true);
        Turno turno = crearTurno();
        Turno turnoLlamado = turno;
        turnoLlamado.setLlamado(true);
        Optional turnoOptional = Optional.of(turno);

        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        when(turnoRepository.save(turno)).thenReturn(turnoLlamado);
        when(modelMapper.map(turnoLlamado, TurnoDTO.class)).thenReturn(result);
        
        result = turnoService.cambiarEstadoLlamadoTurno(0);
        
        assertEquals(true, result.isLlamado());
    }
    
    @Test
    public void cambiarEstadoLlamadoTurno_TurnoOnBD_IdNotSame(){
        TurnoDTO result = crearTurnoDTO();
        result.setLlamado(true);
        Turno turno = crearTurno();
        Turno turnoLlamado = turno;
        turnoLlamado.setLlamado(true);
        Optional turnoOptional = Optional.empty();

        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        
        result = turnoService.cambiarEstadoLlamadoTurno(0);
        
        assertEquals(null, result);
    }
    
    @Test
    public void cambiarEstadoLlamadoTurno_TurnoOnBD_IdNotSame_saveFail(){
        TurnoDTO result = crearTurnoDTO();
        result.setLlamado(true);
        Turno turno = crearTurno();
        turno.setId(1);
        Turno turnoLlamado = turno;
        turnoLlamado.setLlamado(true);
        Optional turnoOptional = Optional.of(turno);
        
        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        when(turnoRepository.save(turno)).thenReturn(turnoLlamado);
        
        result = turnoService.cambiarEstadoLlamadoTurno(0);
        
        assertEquals(null, result);
    }
    
    @Test
    public void cambiarEstadoAtendido_TurnoOnBD_IdSame(){
        TurnoDTO result = crearTurnoDTO();
        result.setAtendido(true);
        Turno turno = crearTurno();
        Turno turnoAtendido = turno;
        turnoAtendido.setAtendido(true);
        Optional turnoOptional = Optional.of(turnoAtendido);

        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        when(turnoRepository.save(turno)).thenReturn(turnoAtendido);
        when(modelMapper.map(turnoAtendido, TurnoDTO.class)).thenReturn(result);
        
        result = turnoService.cambiarEstadoLlamadoTurno(0);
        
        assertEquals(true, result.isAtendido());
    }
    
    @Test
    public void cambiarEstadoAtendido_TurnoNotOnBD_IdSame(){
        TurnoDTO result = crearTurnoDTO();
        result.setAtendido(true);
        Turno turno = crearTurno();
        Turno turnoAtendido = turno;
        turnoAtendido.setAtendido(true);
        Optional turnoOptional = Optional.empty();

        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        
        result = turnoService.cambiarEstadoLlamadoTurno(0);
        
        assertEquals(null, result);
    }
    
    @Test
    public void cambiarEstadoAtendido_TurnoOnBD_IdNotSame(){
        TurnoDTO result = crearTurnoDTO();
        result.setAtendido(true);
        Turno turno = crearTurno();
        Turno turnoAtendido = turno;
        turnoAtendido.setAtendido(true);
        turnoAtendido.setId(1);
        Optional turnoOptional = Optional.of(turnoAtendido);

        when(turnoRepository.findById(0)).thenReturn(turnoOptional);
        when(turnoRepository.save(turno)).thenReturn(turnoAtendido);
        
        result = turnoService.cambiarEstadoLlamadoTurno(0);
        
        assertEquals(null, result);
    }
    
    @Test
    public void turnosPendientes_TurnosPorLlamarNotEmpty(){
        List<TurnoDTO> result = new ArrayList<>();
        List<Turno> turnosPendientes = new ArrayList<>();
        Turno turno = crearTurno();
        TurnoDTO turnoDTO = crearTurnoDTO();
        turnosPendientes.add(turno);
        
        when(turnoRepository.turnosPorLLamar()).thenReturn(turnosPendientes);
        when(modelMapper.map(turno, TurnoDTO.class)).thenReturn(turnoDTO);
        
        result = turnoService.turnosPendientes();
        
        assertEquals(turnoDTO, result.get(0));
    }
    
    @Test
    public void turnosPendientes_TurnosPorLlamarEmpty(){
        List<TurnoDTO> result = new ArrayList<>();
        List<Turno> turnosPendientes = new ArrayList<>();
        
        when(turnoRepository.turnosPorLLamar()).thenReturn(turnosPendientes);
        
        result = turnoService.turnosPendientes();
        
        assertEquals(null, result);
    }
    
    public TurnoDTO crearTurnoDTO(){
        TurnoDTO turno = new TurnoDTO();
        turno.setId(0);
        turno.setSecuencia(1);
        turno.setCategoria("A");
        turno.setLlamado(false);
        turno.setAtendido(false);
        return turno;
    }
    
    public Turno crearTurno(){
        Turno turno = new Turno();
        turno.setId(0);
        turno.setSecuencia(1);
        turno.setCategoria("A");
        turno.setLlamado(false);
        turno.setAtendido(false);
        return turno;
    }            
}
