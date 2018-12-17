/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository;

import java.util.List;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junpa
 */
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    
    @Query(value="SELECT COUNT(*) FROM turno WHERE categoria = :categoria", nativeQuery = true)
    int findByCategoria(@Param("categoria") String categoria);
    
    @Query(value="select * from turno where llamado = true and atendido = false order by secuencia", nativeQuery = true)
    List<Turno> turnosPorLLamar();
    
    @Query(value = "select * from turno where categoria = 'a' and llamado = false and atendido = false order by secuencia limit 3", nativeQuery = true)
    List<Turno> nivelPrioritario();
    
    @Query(value = "select * from turno where categoria = 'b' and llamado = false and atendido = false order by secuencia limit 2", nativeQuery = true)
    List<Turno> nivelSecundario();
    
    @Query(value = "select * from turno where categoria = 'c' and llamado = false and atendido = false order by secuencia limit 1", nativeQuery = true)
    Turno nivelTerciario();
}
