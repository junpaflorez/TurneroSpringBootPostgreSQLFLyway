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
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.ColaTurnosService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author junpa
 */
@Service
public class DefaultColaTurnos implements ColaTurnosService {
    private ColaRepository colaRepository;
    private ModelMapper modelMapper;

    public DefaultColaTurnos(ColaRepository colaRepository, ModelMapper modelMapper) {
        this.colaRepository = colaRepository;
        this.modelMapper = modelMapper;
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
    public boolean guardarTurnoEnCola(TurnoDTO turno) {
        int id = turno.getId();
        String fkAsesor = "";
        long cuantos = colaRepository.count() + 1;
        String secuencia = Long.toString(cuantos);
        Cola cola = new Cola();
        cola.setAsesor(fkAsesor);
        cola.setTurno(id);
        cola = colaRepository.save(cola);
        if(cola.getId() == id){
            return true;
        }
        return false;
    }

    @Override
    public boolean asignarAsesorTurno(ColaDTO turno, AsesorDTO asesor) {
        Cola cola = null;
        Cola auxiliar = null;
        String fkAsesor = asesor.getIdentificacion();
        cola = modelMapper.map(turno, Cola.class);
        cola.setAsesor(fkAsesor);
        auxiliar = colaRepository.save(cola);
        if(auxiliar.getAsesor() == asesor.getIdentificacion()){
            return true;
        }
        return false;
    }

    @Override
    public ColaDTO consultarTurnoEnCola(String secuencia) {
        Optional<Cola> turno = null;
        Cola auxiliar = null;
        turno = colaRepository.findByTurno(secuencia);
        if(turno.isPresent()){
            auxiliar = turno.get();
            return modelMapper.map(auxiliar, ColaDTO.class);
        }
        return null;
    }

    @Override
    public boolean TurnoLibreEnCola(String fkTurno) {
        Optional<Cola> turno = null;
        turno = colaRepository.findByTurno(fkTurno);
        if(turno.isPresent()){
            return true;
        }
        return false;
    }

    
    
}
