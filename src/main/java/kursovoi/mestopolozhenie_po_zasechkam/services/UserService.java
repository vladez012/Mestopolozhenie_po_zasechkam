package kursovoi.mestopolozhenie_po_zasechkam.services;

import jakarta.transaction.Transactional;
import kursovoi.mestopolozhenie_po_zasechkam.MestopolozheniePoZasechkamApplication;
import kursovoi.mestopolozhenie_po_zasechkam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Сервис User
 * @author Владислав Брылёв
 * @version 2.0
 */
@Service
public class UserService{
    @Autowired
    private UserRepository repository;

    public String get_User_ByLoginAndPassword(String str_login, String str_password){
        return  repository.findByLoginAndPassword(str_login, str_password);
    }

    public String get_User_ByLogin(String str_login){
        return  repository.findByLogin(str_login);
    }

    public Long get_Last_ID(){
        return  repository.getLastID();
    }

    @Transactional
    public void set_user(Long id, String str_login, String pass, String nickname, String role){
        repository.set_user(id, str_login, pass, nickname, role);
    }

    @Transactional
    public void delete_user(String str_login){
        repository.delete_user(str_login);
    }

    @Transactional
    public void update_user(String str_id,String str_nickname,String str_login,String str_password,String str_role){
        repository.update_user(Long.parseLong(str_id), str_nickname, str_login, str_password, str_role);
    }

    public String[] get_data_polzovately(){
        return  repository.get_data_polzovately();
    }
}
