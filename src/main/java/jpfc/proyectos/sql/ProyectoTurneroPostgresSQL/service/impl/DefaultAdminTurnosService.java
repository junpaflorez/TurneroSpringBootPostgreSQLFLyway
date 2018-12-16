/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.sql.Time;
import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AtendidoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Atendido;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Turno;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository.AtendidoRepository;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AdminTurnosService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author junpa
 */
@Service
public class DefaultAdminTurnosService implements AdminTurnosService{
    private TurnoService turnoService;
    private AsesorService asesorService;
    private FuncionesService funcionesService;
    private AtendidoRepository atendidoRepository;
    private ModelMapper modelMapper;

    public DefaultAdminTurnosService(TurnoService turnoService, AsesorService asesorService, FuncionesService funcionesService, AtendidoRepository atendidoRepository, ModelMapper modelMapper) {
        this.turnoService = turnoService;
        this.asesorService = asesorService;
        this.funcionesService = funcionesService;
        this.atendidoRepository = atendidoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AtendidoDTO crearTurnoAtendido(String fkAsesor, int fkTurno) {
        Atendido turno = new Atendido();
        Optional<Atendido> auxiliar = null;
        if(funcionesService.esValido(fkAsesor)){
            auxiliar = atendidoRepository.findByFkTurno(fkTurno);
            if(!auxiliar.isPresent()){
                turno.setFkasesor(fkAsesor);
                turno.setFkturno(fkTurno);
                turno = atendidoRepository.save(turno);
                return modelMapper.map(turno,AtendidoDTO.class);
            }
        }
        return null;
    }

    @Override
    public AtendidoDTO consultarTurnoAtendido(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Time promedioTiemposAsesor(String fkAsesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Time promedioTiempoEsperaCategoria(String categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
