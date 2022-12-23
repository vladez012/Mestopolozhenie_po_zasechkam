package kursovoi.mestopolozhenie_po_zasechkam;


import kursovoi.mestopolozhenie_po_zasechkam.services.NastroykiService;
import kursovoi.mestopolozhenie_po_zasechkam.services.ResultsService;
import kursovoi.mestopolozhenie_po_zasechkam.services.TownsCoordinatesService;
import kursovoi.mestopolozhenie_po_zasechkam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
/**
 * Рест-контроллер
 * @author Владислав Брылёв
 * @version 2.0
 */
@RestController
public class Controller {
    @Autowired
    UserService usservice;

    @Autowired
    TownsCoordinatesService townsCoordinatesService;

    @Autowired
    ResultsService resultsservice;

    @Autowired
    NastroykiService nastroykiservice;

    @GetMapping("/user_by_loginAndPassword")
    public String get_User_ByLoginAndPassword(@RequestParam String str_login, @RequestParam String str_password){

        var result = usservice.get_User_ByLoginAndPassword(str_login, str_password);
        return result;
    }

    @GetMapping("/user_by_login")
    public String get_User_ByLogin(@RequestParam String str_login){

        var result = usservice.get_User_ByLogin(str_login);
        return result;
    }

    @GetMapping("/get_user_last_id")
    public Long get_User_Last_ID(){
        return usservice.get_Last_ID();
    }

    @PostMapping("/set_user")
    public ResponseEntity<String> set_user(@RequestBody User user_obj) {
        try {
            usservice.set_user(user_obj.getId(), user_obj.getLogin(), user_obj.getPassword(), user_obj.getNickname(), user_obj.getRole());
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка записи в БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @PostMapping("/delete_user")
    public ResponseEntity<String> delete_user(@RequestBody String login) {
        try {
            usservice.delete_user(login);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @PostMapping("/update_user")
    public ResponseEntity<String> update_user(@RequestBody String[] parametry_update) {
        try {
            usservice.update_user(parametry_update[0],parametry_update[1],parametry_update[2],parametry_update[3],parametry_update[4]);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @GetMapping("/get_data_TownsCoordinates")
    public String[] get_data_TownsCoordinates(){
        var result = townsCoordinatesService.get_Data();
        return result;
    }

    @PostMapping("/set_results")
    public ResponseEntity<String> set_results(@RequestBody String[] parametry_set) {
        try {
            resultsservice.set_results(parametry_set[0],parametry_set[1],parametry_set[2],parametry_set[3],parametry_set[4],
                    parametry_set[5],parametry_set[6],parametry_set[7],parametry_set[8],parametry_set[9],parametry_set[10]);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @GetMapping("/get_results_last_id")
    public Long set_results(){
        return resultsservice.get_Last_ID();
    }

    @GetMapping("/get_data_results")
    public String[] get_data_results(){
        var result = resultsservice.get_Data();
        return result;
    }

    @PostMapping("/update_result")
    public ResponseEntity<String> update_result(@RequestBody String[] parametry_update) {
        try {
            resultsservice.update_result(parametry_update[0],parametry_update[1],parametry_update[2],parametry_update[3],parametry_update[4],
                    parametry_update[5],parametry_update[6],parametry_update[7],parametry_update[8],parametry_update[9]);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @PostMapping("/delete_result")
    public ResponseEntity<String> delete_result(@RequestBody String id) {
        try {
            resultsservice.delete_result(id);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @GetMapping("/get_data_polzovately")
    public String[] get_data_polzovately(){
        var result = usservice.get_data_polzovately();
        return result;
    }

    @PostMapping("/set_nastroyki")
    public ResponseEntity<String> set_nastroyki(@RequestBody String[] parametry_set) {
        try {
            nastroykiservice.set_nastroyki(parametry_set[0],parametry_set[1],parametry_set[2],parametry_set[3]);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

    @GetMapping("/get_nastroyki_last_id")
    public Long get_nastroyki_last_id(){
        return nastroykiservice.get_Last_ID();
    }


    @GetMapping("/nastroyki_by_id")
    public String nastroyki_by_id(@RequestParam String id){

        var result = nastroykiservice.get_Nastroyki_byID(id);
        return result;
    }

    @PostMapping("/update_nastroyki")
    public ResponseEntity<String> update_nastroyki(@RequestBody String[] parametry_update) {
        try {
            nastroykiservice.update_nastroyki(parametry_update[1],parametry_update[2],parametry_update[3]);
            return ResponseEntity.ok("ok");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ошибка удаления из БД: " + e);
            return ResponseEntity.ok("not ok");
        }
    }

}
