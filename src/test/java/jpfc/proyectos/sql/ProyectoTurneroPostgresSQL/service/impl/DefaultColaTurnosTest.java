/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.ColaDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Camilo
 */
public class DefaultColaTurnosTest {
    
    public DefaultColaTurnosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listaCola method, of class DefaultColaTurnos.
     */
    @Test
    public void testListaCola() {
        System.out.println("listaCola");
        DefaultColaTurnos instance = null;
        List<ColaDTO> expResult = null;
        List<ColaDTO> result = instance.listaCola();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pendientesCola method, of class DefaultColaTurnos.
     */
    @Test
    public void testPendientesCola() {
        System.out.println("pendientesCola");
        DefaultColaTurnos instance = null;
        List<ColaDTO> expResult = null;
        List<ColaDTO> result = instance.pendientesCola();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of siguiente method, of class DefaultColaTurnos.
     */
    @Test
    public void testSiguiente() {
        System.out.println("siguiente");
        DefaultColaTurnos instance = null;
        ColaDTO expResult = null;
        ColaDTO result = instance.siguiente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarTurnoEnCola method, of class DefaultColaTurnos.
     */
    @Test
    public void testGuardarTurnoEnCola() {
        System.out.println("guardarTurnoEnCola");
        TurnoDTO turno = null;
        DefaultColaTurnos instance = null;
        ColaDTO expResult = null;
        ColaDTO result = instance.guardarTurnoEnCola(turno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignarAsesorTurno method, of class DefaultColaTurnos.
     */
    @Test
    public void testAsignarAsesorTurno() {
        System.out.println("asignarAsesorTurno");
        int turno = 0;
        AsesorDTO asesor = null;
        DefaultColaTurnos instance = null;
        ColaDTO expResult = null;
        ColaDTO result = instance.asignarAsesorTurno(turno, asesor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarTurnoEnCola method, of class DefaultColaTurnos.
     */
    @Test
    public void testConsultarTurnoEnCola() {
        System.out.println("consultarTurnoEnCola");
        int fkturno = 0;
        DefaultColaTurnos instance = null;
        ColaDTO expResult = null;
        ColaDTO result = instance.consultarTurnoEnCola(fkturno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of turnoLibreEnCola method, of class DefaultColaTurnos.
     */
    @Test
    public void testTurnoLibreEnCola() {
        System.out.println("turnoLibreEnCola");
        int fkTurno = 0;
        DefaultColaTurnos instance = null;
        boolean expResult = false;
        boolean result = instance.turnoLibreEnCola(fkTurno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearCola method, of class DefaultColaTurnos.
     */
    @Test
    public void testCrearCola() {
        System.out.println("crearCola");
        DefaultColaTurnos instance = null;
        List<ColaDTO> expResult = null;
        List<ColaDTO> result = instance.crearCola();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
