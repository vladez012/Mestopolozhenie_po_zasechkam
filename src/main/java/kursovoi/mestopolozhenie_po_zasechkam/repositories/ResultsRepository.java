package kursovoi.mestopolozhenie_po_zasechkam.repositories;

import kursovoi.mestopolozhenie_po_zasechkam.ResultsOfCalculation;
import kursovoi.mestopolozhenie_po_zasechkam.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий ResultsRepository
 * @author Владислав Брылёв
 * @version 2.0
 */
@Repository
public interface ResultsRepository extends JpaRepository<ResultsOfCalculation, Long> {

    @Modifying
    @Query(value = "INSERT INTO results_of_calculation (id, id_polzovatelya, nazvanie, ugol1, ugol2,x1,y1,x2,y2,mestopolozhenie_x,mestopolozhenie_y) VALUES(:id, :id_polzovatelya, :nazvanie, :ugol1, :ugol2,:x1,:y1,:x2,:y2,:mestopolozhenie_x,:mestopolozhenie_y)", nativeQuery = true)
    void set_results(@Param("id") Long id,
                     @Param("id_polzovatelya") Long id_polzovatelya,
                     @Param("nazvanie") String nazvanie,
                     @Param("ugol1") Double ugol1,
                     @Param("ugol2") Double ugol2,
                     @Param("x1") Double x1,
                     @Param("y1") Double y1,
                     @Param("x2") Double x2,
                     @Param("y2") Double y2,
                     @Param("mestopolozhenie_x") Double mestopolozhenie_x,
                     @Param("mestopolozhenie_y") Double mestopolozhenie_y);

    @Query(value = "select MAX(id) from results_of_calculation", nativeQuery = true)
    Long getLastID();

    @Query(value = "select results_of_calculation.id, account.nickname, results_of_calculation.nazvanie, results_of_calculation.ugol1, results_of_calculation.ugol2, results_of_calculation.x1,results_of_calculation.y1,results_of_calculation.x2,results_of_calculation.y2,results_of_calculation.mestopolozhenie_x,results_of_calculation.mestopolozhenie_y from results_of_calculation LEFT JOIN account on results_of_calculation.id_polzovatelya = account.id", nativeQuery = true)
    String[] get_Data();

    @Modifying
    @Query(value = "UPDATE results_of_calculation set nazvanie = :nazvanie, ugol1 = :ugol1, ugol2 = :ugol2, x1 = :x1, y1 = :y1, x2 = :x2, y2 = :y2, mestopolozhenie_x = :mestopolozhenie_x, mestopolozhenie_y = :mestopolozhenie_y WHERE id = :id", nativeQuery = true)
    void update_result(@Param("id") Long id,
                     @Param("nazvanie") String nazvanie,
                     @Param("ugol1") Double ugol1,
                     @Param("ugol2") Double ugol2,
                     @Param("x1") Double x1,
                     @Param("y1") Double y1,
                     @Param("x2") Double x2,
                     @Param("y2") Double y2,
                     @Param("mestopolozhenie_x") Double mestopolozhenie_x,
                     @Param("mestopolozhenie_y") Double mestopolozhenie_y);

    @Modifying
    @Query(value = "DELETE from results_of_calculation where  id = :id", nativeQuery = true)
    void delete_result(@Param("id") Long id);
}
