/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.repository;

import java.util.Optional;
import jpfc.proyectos.sql.ProyectoTurneroPostgresSQL.entity.Atendido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junpa
 */
@Repository
public interface AtendidoRepository extends JpaRepository<Atendido, Integer> {
    
    @Query(value="select * from atendido where fkTurno = :turno",nativeQuery = true)
    public Optional<Atendido> findByFkTurno(@Param("turno") int fkTurno);
}
