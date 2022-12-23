package kursovoi.mestopolozhenie_po_zasechkam;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
/**
 * Класс User для хранения данных о пользователях
 * @author Владислав Брылёв
 * @version 2.0
 */
public class User {


    //(strategy = GenerationType.IDENTITY)

    private @Id @GeneratedValue(generator = "increment") Long id;
    private String nickname;
    private String login;
    private String password;
    private String role;

}
