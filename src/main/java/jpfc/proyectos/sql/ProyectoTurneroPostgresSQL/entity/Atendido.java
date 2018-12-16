/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author junpa
 */
@Entity
@Data
@NoArgsConstructor
public class Atendido implements Serializable{
    @Id
    private int id;
    private int fkturno;
    private String  fkasesor;
    private Time horainicio;
    private Time horafinal;
    private Time promedioespera;
    private Time promedioatendido;
    
    
}
