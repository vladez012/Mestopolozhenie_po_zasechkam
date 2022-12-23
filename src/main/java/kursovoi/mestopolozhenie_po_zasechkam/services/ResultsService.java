package kursovoi.mestopolozhenie_po_zasechkam.services;

import jakarta.transaction.Transactional;
import kursovoi.mestopolozhenie_po_zasechkam.repositories.ResultsRepository;
import kursovoi.mestopolozhenie_po_zasechkam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Сервис ResultsService
 * @author Владислав Брылёв
 * @version 2.0
 */
@Service
public class ResultsService {
    @Autowired
    private ResultsRepository repository;

    @Transactional
    public void set_results(String str_id,String id_polzovatelya,String Nazvanie,String ugol1,String ugol2,
                            String x1,String y1,String x2,String y2,String mestopolozhenie_x, String mestopolozhenie_y){

        repository.set_results(Long.parseLong(str_id), Long.parseLong(id_polzovatelya),Nazvanie,
                Double.parseDouble(ugol1), Double.parseDouble(ugol2),Double.parseDouble(x1),Double.parseDouble(y1),Double.parseDouble(x2),
                Double.parseDouble(y2),Double.parseDouble(mestopolozhenie_x),Double.parseDouble(mestopolozhenie_y));
    }

    public Long get_Last_ID(){
        return  repository.getLastID();
    }

    public String[] get_Data(){
        return  repository.get_Data();
    }

    @Transactional
    public void update_result(String str_id,String nazvanie,String ugol1,String ugol2,String x1,
                            String y1,String x2,String y2,String mest_x,String mest_y){
        repository.update_result(Long.parseLong(str_id), nazvanie, Double.parseDouble(ugol1), Double.parseDouble(ugol2),
                Double.parseDouble(x1),Double.parseDouble(y1),Double.parseDouble(x2),Double.parseDouble(y2),
                Double.parseDouble(mest_x),Double.parseDouble(mest_y));
    }

    @Transactional
    public void delete_result(String id){
        repository.delete_result(Long.parseLong(id));
    }
}
