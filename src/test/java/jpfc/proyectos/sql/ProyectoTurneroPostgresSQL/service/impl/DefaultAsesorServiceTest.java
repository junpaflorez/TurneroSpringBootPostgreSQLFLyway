/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Asesor;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository.AsesorRepository;
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
public class DefaultAsesorServiceTest {
    @Mock
    private FuncionesService funcionesService;
    @Mock
    private AsesorRepository asesorRepository;
    @Mock
    private ModelMapper modelMapper = new ModelMapper();
    
    @InjectMocks
    private DefaultAsesorService asesorService;
     
    /*@Before
    public void setUp() {
        funcionesService = Mockito.mock(FuncionesService.class);
        asesorRepository = Mockito.mock(AsesorRepository.class);        
    }*/
    @Test
    public void crearAsesor_IdentificacionEmpty_NombreEmpty(){
        AsesorDTO result = null;
        String identificacion = null;
        String nombre = null;
        when(funcionesService.esValido(identificacion)).thenReturn(Boolean.FALSE);
        when(funcionesService.esValido(nombre)).thenReturn(Boolean.FALSE);
        
        result = asesorService.crearAsesor(identificacion, nombre);
        
        assertEquals(result,null);
    }
    
    @Test
    public void crearAsesor_IdentificacionNotEmpty_NombreNotEmpty(){
        AsesorDTO result = null;
        String identificacion = "02";
        String nombre = "CARLOS";
        Asesor asesor = crearAsesor();
        AsesorDTO asesorDTO = crearAsesorDTO();
        Optional<Asesor> asesorOptional = Optional.empty();
        //Optional<Asesor> asesorOptional = Optional.of(crearAsesor());
        
        when(funcionesService.esValido(identificacion)).thenReturn(Boolean.TRUE);
        when(funcionesService.esValido(nombre)).thenReturn(Boolean.TRUE);
        when(asesorRepository.findById(identificacion)).thenReturn(asesorOptional);
        when(asesorRepository.save(asesor)).thenReturn(asesor);
        
        result = asesorService.crearAsesor(identificacion, nombre);
        
        assertEquals(identificacion, result.getIdentificacion());
    }
    
    @Test
    public void crearAsesor_IdentificacionNotEmpty_NombreNotEmpty_AlredyBD(){
        AsesorDTO result = null;
        String identificacion = "02";
        String nombre = "CARLOS";
        Asesor asesor = crearAsesor();
        AsesorDTO asesorDTO = crearAsesorDTO();
        //Optional<Asesor> asesorOptional = Optional.empty();
        Optional<Asesor> asesorOptional = Optional.of(asesor);
        
        System.out.println("Sta Mierda esta sospechosa: " + asesorOptional.isPresent());
        
        when(funcionesService.esValido(identificacion)).thenReturn(Boolean.TRUE);
        when(funcionesService.esValido(nombre)).thenReturn(Boolean.TRUE);
        when(asesorRepository.findById(identificacion)).thenReturn(asesorOptional);
        when(asesorRepository.save(asesor)).thenReturn(asesor);
        when(modelMapper.map(asesor, AsesorDTO.class)).thenReturn(asesorDTO);
        
        result = asesorService.crearAsesor(identificacion, nombre);
        
        assertEquals(null, result);
    }
    
    @Test
    public void crearAsesor_IdentificacionNotEmpty_NombreNotEmpty_IdentificacionNotMatches(){
        AsesorDTO result = null;
        String identificacion = "02";
        String nombre = "CARLOS";
        Asesor asesor = crearAsesor();
        AsesorDTO asesorDTO = crearAsesorDTO();
        //Optional<Asesor> asesorOptional = Optional.empty();
        Optional<Asesor> asesorOptional = Optional.of(asesor);
        
        System.out.println("Sta Mierda esta sospechosa: " + asesorOptional.isPresent());
        
        when(funcionesService.esValido(identificacion)).thenReturn(Boolean.TRUE);
        when(funcionesService.esValido(nombre)).thenReturn(Boolean.TRUE);
        when(asesorRepository.findById(identificacion)).thenReturn(asesorOptional);
        when(modelMapper.map(asesor, AsesorDTO.class)).thenReturn(asesorDTO);
        
        result = asesorService.crearAsesor(identificacion, nombre);
        
        assertEquals(null, result);
    }
    
    
    public AsesorDTO crearAsesorDTO(){
        AsesorDTO asesor = new AsesorDTO();
        asesor.setIdentificacion("02");
        asesor.setNombre("CARLOS");
        return asesor;
    }
    
    public Asesor crearAsesor(){
        Asesor asesor = new Asesor();
        asesor.setIdentificacion("02");
        asesor.setNombre("CARLOS");
        return asesor;
    }
}