/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.dto;

import java.sql.Time;

/**
 *
 * @author junpa
 */
public class AtendidoDTO {
    private int id;
    private int fkturno;
    private String  fkasesor;
    private Time horainicio;
    private Time horafinal;
    private Time promedioespera;
    private Time promedioatendido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkturno() {
        return fkturno;
    }

    public void setFkturno(int fkturno) {
        this.fkturno = fkturno;
    }

    public String getFkasesor() {
        return fkasesor;
    }

    public void setFkasesor(String fkasesor) {
        this.fkasesor = fkasesor;
    }

    public Time getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public Time getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(Time horafinal) {
        this.horafinal = horafinal;
    }

    public Time getPromedioespera() {
        return promedioespera;
    }

    public void setPromedioespera(Time promedioespera) {
        this.promedioespera = promedioespera;
    }

    public Time getPromedioatendido() {
        return promedioatendido;
    }

    public void setPromedioatendido(Time promedioatendido) {
        this.promedioatendido = promedioatendido;
    }
    
}
