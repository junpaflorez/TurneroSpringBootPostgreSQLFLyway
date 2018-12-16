/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Turno;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository.TurnoRepository;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author junpa
 */
@Service
public class DefaultTurnoService implements TurnoService{
    private TurnoRepository turnoRepository;
    private FuncionesService funcionesService;
    private ModelMapper modelMapper;

    public DefaultTurnoService(TurnoRepository turnoRepository, FuncionesService funcionesService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.funcionesService = funcionesService;
        this.modelMapper = modelMapper;
    }
    
    
    @Override
    public TurnoDTO crearTurno(String categoria) {
        Turno turno = new Turno();
        int secuencia = 0;
        if(funcionesService.esValido(categoria)){
            secuencia = turnoRepository.findByCategoria(categoria) + 1;
            turno.setCategoria(categoria);
            turno.setSecuencia(secuencia);
            turno.setAtendido(false);
            turno.setLlamado(false);
            turno = turnoRepository.save(turno);
            return modelMapper.map(turno,TurnoDTO.class);
        }
        return null;
    }

    @Override
    public List<TurnoDTO> listaTurno() {
        List<TurnoDTO> lista = null;
        return null;
    }
    
    
    
}
