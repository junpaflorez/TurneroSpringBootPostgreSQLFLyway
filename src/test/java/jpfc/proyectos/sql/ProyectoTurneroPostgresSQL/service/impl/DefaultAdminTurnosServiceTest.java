/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AtendidoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Atendido;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository.AtendidoRepository;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
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
public class DefaultAdminTurnosServiceTest {
    @Mock
    private TurnoService turnoService;
    @Mock
    private AsesorService asesorService;
    @Mock
    private FuncionesService funcionesService;
    @Mock
    private AtendidoRepository atendidoRepository;
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private DefaultAdminTurnosService adminTurnosService;
    
    @Test
    public void crearTurnoAtendido_FkAsesorValid_AuxiliarEmpty(){
        Atendido turno = crearAtendido("01",1);
        Optional turnoOptional = Optional.empty();
        AtendidoDTO result = crearAtendidoDTO("01",1);
        
        when(funcionesService.esValido(turno.getFkasesor())).thenReturn(Boolean.TRUE);
        when(atendidoRepository.findByFkTurno(turno.getFkturno())).thenReturn(turnoOptional);
        when(atendidoRepository.save(turno)).thenReturn(turno);
        when(modelMapper.map(turno, AtendidoDTO.class)).thenReturn(result);
        
        result = adminTurnosService.crearTurnoAtendido(turno.getFkasesor(), turno.getFkturno());
        
        assertEquals("01", result.getFkasesor());
        assertEquals(1, result.getFkturno());
    }
    
    @Test
    public void crearTurnoAtendido_FkAsesorInvalid(){
        Atendido turno = crearAtendido("01",1);
        AtendidoDTO result = crearAtendidoDTO("01",1);
        
        when(funcionesService.esValido(turno.getFkasesor())).thenReturn(Boolean.FALSE);
        
        result = adminTurnosService.crearTurnoAtendido(turno.getFkasesor(), turno.getFkturno());
        
        assertEquals(null, result);
    }
    
    
    @Test
    public void crearTurnoAtendido_FkAsesorValid_AuxiliarNotEmpty(){
        Atendido turno = crearAtendido("01",1);
        Optional turnoOptional = Optional.of(turno);
        AtendidoDTO result = crearAtendidoDTO("01",1);

        
        when(funcionesService.esValido(turno.getFkasesor())).thenReturn(Boolean.TRUE);
        when(atendidoRepository.findByFkTurno(turno.getFkturno())).thenReturn(turnoOptional);
        
        result = adminTurnosService.crearTurnoAtendido(turno.getFkasesor(), turno.getFkturno());
        
        assertEquals(null, result);
    }
    
    @Test
    public void consultarTurnoAtendido_IdValid_AuxiliarPresent(){
        AtendidoDTO result = crearAtendidoDTO("01",1);
        Optional<Atendido> turnoOptional = Optional.of(crearAtendido("01",1));
        
        when(atendidoRepository.findById(result.getId())).thenReturn(turnoOptional);
        when(modelMapper.map(turnoOptional.get(), AtendidoDTO.class)).thenReturn(result);
        
        result = adminTurnosService.consultarTurnoAtendido(0);
        
        assertEquals("01", result.getFkasesor());
    }
    
    @Test
    public void consultarTurnoAtendido_IdNotValid(){
        AtendidoDTO result = crearAtendidoDTO("01",1);
        
        result = adminTurnosService.consultarTurnoAtendido(-1);
        
        assertEquals(null, result);
    }
    
    @Test
    public void consultarTurnoAtendido_IdValid_AuxiliarNotPresent(){
        AtendidoDTO result = crearAtendidoDTO("01",1);
        Optional<Atendido> turnoOptional = Optional.empty();
        
        when(atendidoRepository.findById(result.getId())).thenReturn(turnoOptional);
        
        result = adminTurnosService.consultarTurnoAtendido(0);
        
        assertEquals(null, result);
    }
    
    public Atendido crearAtendido(String fkAsesor, int fkTurno){
        Atendido turno = new Atendido();
        turno.setFkasesor(fkAsesor);
        turno.setFkturno(1);
        turno.setId(0);
        return turno;
    }
    
    public AtendidoDTO crearAtendidoDTO(String fkAsesor, int fkTurno){
        AtendidoDTO turno = new AtendidoDTO();
        turno.setFkasesor(fkAsesor);
        turno.setFkturno(1);
        turno.setId(0);
        return turno;
    }
}
