package kursovoi.mestopolozhenie_po_zasechkam;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Класс для хранения справочника городов России с их долготой и широтой
 * @author Владислав Брылёв
 * @version 2.0
 */
public class TownsCoordinates {
    private @Id @GeneratedValue(generator = "increment") Long id;
    private String Region;
    private String Gorod;
    private Double Shirota;
    private Double Dolgota;
}
