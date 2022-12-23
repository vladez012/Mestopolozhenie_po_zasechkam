package kursovoi.mestopolozhenie_po_zasechkam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Класс для хранения результатов вычислений о местоположении пользователя
 * @author Владислав Брылёв
 * @version 2.0
 */
public class ResultsOfCalculation {

    private @Id
    @GeneratedValue(generator = "increment") Long id;
    private Long   id_polzovatelya;
    private String nazvanie;
    private Double ugol1;
    private Double ugol2;
    private Double x1;
    private Double y1;
    private Double x2;
    private Double y2;
    private Double mestopolozhenie_x;
    private Double mestopolozhenie_y;

}
