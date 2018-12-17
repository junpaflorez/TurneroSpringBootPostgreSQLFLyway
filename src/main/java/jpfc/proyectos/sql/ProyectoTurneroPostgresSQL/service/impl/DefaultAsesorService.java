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
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.AsesorService;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.service.FuncionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author junpa
 */
@Service
public class DefaultAsesorService implements AsesorService{

    private AsesorRepository asesorRepository;
    private ModelMapper modelMapper;
    private FuncionesService funcionesService;

    public DefaultAsesorService(AsesorRepository asesorRepository, ModelMapper modelMapper, FuncionesService funcionesService) {
        this.asesorRepository = asesorRepository;
        this.modelMapper = modelMapper;
        this.funcionesService = funcionesService;
    }
    
    @Override
    public AsesorDTO crearAsesor(String identificacion , String nombre) {
        Asesor asesor = new Asesor();
        Optional<Asesor> resultado = null;
        if(funcionesService.esValido(identificacion) && funcionesService.esValido(nombre)){
            asesor.setIdentificacion(identificacion);
            asesor.setNombre(nombre);
            resultado = asesorRepository.findById(identificacion);
            if(!resultado.isPresent()){
                asesor = asesorRepository.save(asesor);
                if(asesor.getIdentificacion().matches(identificacion)){
                    return modelMapper.map(asesor, AsesorDTO.class);
                }
            }
        }
        return null;
    }

    @Override
    public AsesorDTO consultarAsesor(AsesorDTO asesorDTO) {
        Optional<Asesor> asesor = null;
        String identificacion = asesorDTO.getIdentificacion();
        Asesor auxiliar = new Asesor();
        String nombre = asesorDTO.getNombre();
        if(funcionesService.esValido(identificacion)&&funcionesService.esValido(nombre)){
            asesor = asesorRepository.findById(identificacion);
            if(asesor.isPresent()){
                auxiliar = asesor.get();
                if(auxiliar.getNombre().matches(nombre)){
                    return modelMapper.map(asesor.get(), AsesorDTO.class);
                }
            }
        }
        return null;
    }
    

}
