/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author junpa
 */
@Entity
@Data
@NoArgsConstructor
public class Turno{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "turno_id_seq")
    @SequenceGenerator(name = "auto_gen", sequenceName = "turno_id_seq")
    private int id;
    private int secuencia;
    private String categoria;
    
    @CreationTimestamp
    private Time hora;
    
    @CreationTimestamp
    private Date fecha;
    
    private boolean llamado;
    private boolean atendido;    
    
}
