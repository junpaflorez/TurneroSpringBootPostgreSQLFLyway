/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository;

import java.util.List;
import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Cola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junpa
 */
@Repository
public interface ColaRepository extends JpaRepository<Cola, Integer>{
    
    @Query(value = "Select * from cola ", nativeQuery = true)
    List<Cola> buscarPendientes(); 
    
    @Query(value = "Select * from cola where asesor = '' order by id limit 1", nativeQuery = true)
    Optional<Cola> siguiente();
    
    @Query(value = "Select * from cola where turno = :fkTurno", nativeQuery = true)
    Optional<Cola> findByTurno(@Param("fkTurno") String fkTurno);

}
