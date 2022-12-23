package kursovoi.mestopolozhenie_po_zasechkam.repositories;

import kursovoi.mestopolozhenie_po_zasechkam.TownsCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий TownsCoordinatesRepository
 * @author Владислав Брылёв
 * @version 2.0
 */
@Repository
public interface TownsCoordinatesRepository extends JpaRepository<TownsCoordinates, Long> {
    @Query(value = "select region, gorod, shirota, dolgota from towns_coordinates", nativeQuery = true)
    String[] get_Data();
}
