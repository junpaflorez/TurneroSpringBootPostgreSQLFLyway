/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
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
public class Atendido implements Serializable{
    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "atendido_id_seq")
    @SequenceGenerator(name = "auto_gen", sequenceName = "atendido_id_seq")
    private int id;
    
    private int fkturno;
    private String  fkasesor;
    
        
    @CreationTimestamp
    private Time horainicio;
        
    @CreationTimestamp
    private Time horafinal;
        
    @CreationTimestamp
    private Time promedioespera;
        
    @CreationTimestamp
    private Time promedioatendido;
    
    
}
