package kursovoi.mestopolozhenie_po_zasechkam.services;

import kursovoi.mestopolozhenie_po_zasechkam.repositories.TownsCoordinatesRepository;
import kursovoi.mestopolozhenie_po_zasechkam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Сервис TownsCoordinatesService
 * @author Владислав Брылёв
 * @version 2.0
 */
@Service
public class TownsCoordinatesService {
    @Autowired
    private TownsCoordinatesRepository repository;

    public String[] get_Data(){
        return  repository.get_Data();
    }
}
