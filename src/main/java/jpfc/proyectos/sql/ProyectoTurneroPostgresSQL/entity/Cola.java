/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author junpa
 */
@Entity
@Data
@NoArgsConstructor
public class Cola implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cola_id_seq")
    @SequenceGenerator(name = "auto_gen", sequenceName = "cola_id_seq")
    private int id;
    
    private int turno;
    private String asesor;
}
