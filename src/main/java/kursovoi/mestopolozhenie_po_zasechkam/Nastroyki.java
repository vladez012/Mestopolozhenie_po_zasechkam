package kursovoi.mestopolozhenie_po_zasechkam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Класс для хранения настроек пользователя
 * @author Владислав Брылёв
 * @version 2.0
 */
public class Nastroyki {
    private @Id
    @GeneratedValue(generator = "increment") Long id;
    private Long user_id;
    private Integer tip_karty;
    private Integer okruglenie_resultatov_mestopolozheniya;
}
