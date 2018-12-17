/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AsesorDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.ColaDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.TurnoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Cola;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository.ColaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.ColaService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.TurnoService;

/**
 *
 * @author junpa
 */
@Service
public class DefaultColaTurnos implements ColaService {
    private ColaRepository colaRepository;
    private ModelMapper modelMapper;
    private TurnoService turnoService;

    public DefaultColaTurnos(ColaRepository colaRepository, ModelMapper modelMapper, TurnoService turnoService) {
        this.colaRepository = colaRepository;
        this.modelMapper = modelMapper;
        this.turnoService = turnoService;
    }

    
    
    @Override
    public List<ColaDTO> listaCola() {
        List<Cola> todos = new ArrayList<>();
        todos = colaRepository.findAll();
        List<ColaDTO> turnos = null;
        
        for(Cola turno: todos){
                turnos.add(modelMapper.map(turno, ColaDTO.class));
        }
        if(!turnos.isEmpty()){
            return  turnos;
        }
        return null;
    }

    @Override
    public List<ColaDTO> pendientesCola() {
        List<Cola> todos = new ArrayList<>();
        String fkAsesor = "";
        List<ColaDTO> turnos = null;
        todos = colaRepository.buscarPendientes();
        for(Cola turno: todos){
            fkAsesor = turno.getAsesor();
            if(!fkAsesor.isEmpty()){
                turnos.add(modelMapper.map(turno, ColaDTO.class));
            }
        }
        if(!turnos.isEmpty()){
            return turnos;
        }
        return null;
    }

    @Override
    public ColaDTO siguiente() {
        Optional<Cola> turno = null;
        Cola auxiliar = null;
        turno = colaRepository.siguiente();
        if(turno.isPresent()){
            auxiliar = turno.get();
            return modelMapper.map(auxiliar, ColaDTO.class);
        }
        return null;
    }

    @Override
    public ColaDTO guardarTurnoEnCola(TurnoDTO turno) {
        if(turno!=null){
            int id = turno.getId();
            Cola cola = new Cola();
            cola.setTurno(id);
            cola = colaRepository.save(cola);
            if(cola.getId() == id){
                return modelMapper.map(cola,ColaDTO.class);
            }
        }
        return null;
    }

    @Override
    public ColaDTO asignarAsesorTurno(int turno, AsesorDTO asesor) {
        Optional<Cola> cola = null;
        Cola auxiliar = null;
        String fkAsesor = asesor.getIdentificacion();
        cola = colaRepository.findByTurno(turno);
        auxiliar = cola.get();
        auxiliar.setAsesor(fkAsesor);
        auxiliar = colaRepository.save(auxiliar);
        if(auxiliar.getAsesor() == asesor.getIdentificacion()){
            return modelMapper.map(auxiliar, ColaDTO.class);
        }
        return null;
    }

    @Override
    public ColaDTO consultarTurnoEnCola(int fkturno) {
        Optional<Cola> turno = null;
        Cola auxiliar = null;
        turno = colaRepository.findByTurno(fkturno);
        if(turno.isPresent()){
            auxiliar = turno.get();
            return modelMapper.map(auxiliar, ColaDTO.class);
        }
        return null;
    }

    @Override
    public boolean turnoLibreEnCola(int fkTurno) {
        Optional<Cola> turno = null;
        turno = colaRepository.findByTurno(fkTurno);
        if(turno.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public List<ColaDTO> crearCola() {
        List<TurnoDTO> turnos = new ArrayList<>();
        turnos = turnoService.turnosParaEncolar();
        List<TurnoDTO> turnosCola = new ArrayList<>();
        ColaDTO cola = new ColaDTO();
        List<ColaDTO> listaCola = new ArrayList<>();
        int id = 0;
        if(!turnos.isEmpty()){
            for(TurnoDTO turno:turnos){
                id = turno.getId();
                if(!turnoLibreEnCola(id)){
                    turnosCola.add(modelMapper.map(turno, TurnoDTO.class));
                }
            }
        }
        if(!turnosCola.isEmpty()){
            for(TurnoDTO turno:turnosCola){
                cola = guardarTurnoEnCola(turno);
                if(cola!=null){
                    listaCola.add(cola);
                }
            }
            return listaCola;
        }
        return null;
    }
    
}
