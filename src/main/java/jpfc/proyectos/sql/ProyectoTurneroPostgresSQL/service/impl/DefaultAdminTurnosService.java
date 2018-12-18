/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto.AtendidoDTO;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Atendido;
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
        Atendido turno = new Atendido();
        Optional<Atendido> auxiliar = null;
        if(id >= 0){
            auxiliar = atendidoRepository.findById(id);
            if(auxiliar.isPresent()){
                turno = auxiliar.get();
                return modelMapper.map(turno, AtendidoDTO.class);
            }
        }
        return null;
    }

    @Override
    public Time promedioTiemposAsesor(String fkAsesor) {
        List<Atendido> turnos = new ArrayList<>();
        Calendar tiempoTotal = funcionesService.iniciarReloj();
        long promedio = 0;
        Time promedioAsesor = new Time(tiempoTotal.getTimeInMillis());
        Calendar tiempo = Calendar.getInstance();
        tiempoTotal.setTime(promedioAsesor);
        long minutosLong = 0;
        int minutos = 0;
        int contador = 0;
        if(funcionesService.esValido(fkAsesor)){
            turnos = atendidoRepository.buscarPorAsesor(fkAsesor);
            for(Atendido turno:turnos){
                tiempo.setTime(turno.getPromedioatendido());
                minutosLong = tiempo.getTime().getTime();
                minutos = (int)(long) minutosLong;
                tiempoTotal.add(Calendar.MINUTE, minutos );
                contador ++;
            }
            if(promedio > 0){
                promedio = (tiempoTotal.getTime().getTime())/contador;
            
                promedioAsesor.setTime(promedio);
                return promedioAsesor;
            }
        }
        return promedioAsesor;
    }

    @Override
    public AtendidoDTO asignarAsesor(int turno, String asesor) {
        System.out.println("voy a asignar el asesor");
        Optional<Atendido> consulta = null;
        consulta = atendidoRepository.findByFkTurno(turno);
        System.out.println("encontre el turno correspondiente el turno: " + turno);
        System.out.println("busque el turno: " + consulta.get().getFkturno());
        Atendido turnoAtendido = new Atendido();
        if(consulta.isPresent()){
            turnoAtendido = consulta.get();
            System.out.println("el turno es: " + turnoAtendido.getFkturno());
            turnoAtendido.setFkasesor(asesor);
            turnoAtendido = atendidoRepository.save(turnoAtendido);
            System.out.println("ya guarde");
            return modelMapper.map(turnoAtendido, AtendidoDTO.class);
        }
        return null;
    }
   
}
