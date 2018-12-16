/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository;

import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junpa
 */
@Repository
public interface AsesorRepository extends JpaRepository<Asesor, String>{
    
}
