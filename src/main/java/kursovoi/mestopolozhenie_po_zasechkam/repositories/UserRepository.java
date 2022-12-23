package kursovoi.mestopolozhenie_po_zasechkam.repositories;

import kursovoi.mestopolozhenie_po_zasechkam.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий UserRepository
 * @author Владислав Брылёв
 * @version 2.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select id, nickname, login, password, role from account where  login = :login and password = :password", nativeQuery = true)
    String findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query(value = "select id, nickname, login, password, role from account where  login = :login", nativeQuery = true)
    String findByLogin(@Param("login") String login);

    @Query(value = "select MAX(id) from account", nativeQuery = true)
    Long getLastID();

    @Modifying
    @Query(value = "INSERT INTO account (id, nickname, login, password, role) VALUES(:id, :nick, :login, :pass, :role)", nativeQuery = true)
    void set_user(@Param("id") Long id,
                  @Param("login") String login,
                  @Param("pass") String pass,
                  @Param("nick") String nick,
                  @Param("role") String role);

    @Modifying
    @Query(value = "DELETE from account where  login = :login", nativeQuery = true)
    void delete_user(@Param("login") String login);

    @Modifying
    @Query(value = "UPDATE account set id = :id, nickname = :nick, login = :login, password = :pass, role = :role WHERE login = :login", nativeQuery = true)
    void update_user(@Param("id") Long id,
                     @Param("nick") String nickname,
                     @Param("login") String login,
                     @Param("pass") String password,
                     @Param("role") String role);

    @Query(value = "select id, nickname, login, role from account", nativeQuery = true)
    String[] get_data_polzovately();


}
