package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.web;

import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Asesor;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
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
public class AsesorControllerTest {
    
    @Mock
    private AsesorService asesorService;
    @InjectMocks
    private AsesorController asesorController;
    
    @Test
    public void crearAsesor_AsesorEmpty(){
        ResponseEntity<?> result = asesorController.crearAsesor(null);
        
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());        
    }
    
    @Test
    public void crearAsesor_AsesorCreatedEmpty(){
        AsesorDTO asesorDTO = crearAsesorDTO();
        
        when(asesorService.crearAsesor(asesorDTO.getIdentificacion(), asesorDTO.getNombre())).thenReturn(null);
                
        ResponseEntity<?> result = asesorController.crearAsesor(asesorDTO);
        
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());        
    }
    
    @Test
    public void crearAsesor_AsesorCreated(){
        AsesorDTO asesorDTO = crearAsesorDTO();
        
        when(asesorService.crearAsesor(asesorDTO.getIdentificacion(), asesorDTO.getNombre())).thenReturn(asesorDTO);
                
        ResponseEntity<?> result = asesorController.crearAsesor(asesorDTO);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());        
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
