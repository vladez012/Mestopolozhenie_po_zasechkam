package kursovoi.mestopolozhenie_po_zasechkam.services;

import jakarta.transaction.Transactional;
import kursovoi.mestopolozhenie_po_zasechkam.repositories.NastroykiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Сервис NastroykiService
 * @author Владислав Брылёв
 * @version 2.0
 */
@Service
public class NastroykiService {
    @Autowired
    private NastroykiRepository repository;

    @Transactional
    public void set_nastroyki(String id, String user_id, String tip_karty, String okruglenie){
        repository.set_nastroyki(Long.parseLong(id), Long.parseLong(user_id), Integer.parseInt(tip_karty),
                Integer.parseInt(okruglenie));
    }
    public Long get_Last_ID(){
        return  repository.getLastID();
    }

    public String get_Nastroyki_byID(String id){
        return  repository.findByID(Long.parseLong(id));
    }

    @Transactional
    public void update_nastroyki(String user_id,String tip_karty,String okruglenie){
        repository.update_nastroyki(Long.parseLong(user_id), Integer.parseInt(tip_karty),
                Integer.parseInt(okruglenie));
    }
}
