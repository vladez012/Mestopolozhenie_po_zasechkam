package kursovoi.mestopolozhenie_po_zasechkam.repositories;

import kursovoi.mestopolozhenie_po_zasechkam.Nastroyki;
import kursovoi.mestopolozhenie_po_zasechkam.ResultsOfCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий NastroykiRepository
 * @author Владислав Брылёв
 * @version 2.0
 */
@Repository
public interface NastroykiRepository extends JpaRepository<Nastroyki, Long> {

    @Modifying
    @Query(value = "INSERT INTO nastroyki (id, user_id, tip_karty, okruglenie_resultatov_mestopolozheniya) VALUES(:id, :user_id, :tip_karty, :okruglenie)", nativeQuery = true)
    void set_nastroyki(@Param("id") Long id,
                  @Param("user_id") Long user_id,
                  @Param("okruglenie") Integer okruglenie,
                  @Param("tip_karty") Integer tip_karty);

    @Query(value = "select MAX(id) from nastroyki", nativeQuery = true)
    Long getLastID();

    @Query(value = "select tip_karty, okruglenie_resultatov_mestopolozheniya from nastroyki where  user_id = :id", nativeQuery = true)
    String findByID(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE nastroyki set user_id = :user_id, tip_karty = :tip_karty, okruglenie_resultatov_mestopolozheniya = :okruglenie_resultatov_mestopolozheniya WHERE user_id = :user_id", nativeQuery = true)
    void update_nastroyki(@Param("user_id") Long user_id,
                       @Param("tip_karty") Integer tip_karty,
                       @Param("okruglenie_resultatov_mestopolozheniya") Integer okruglenie_resultatov_mestopolozheniya);
}
