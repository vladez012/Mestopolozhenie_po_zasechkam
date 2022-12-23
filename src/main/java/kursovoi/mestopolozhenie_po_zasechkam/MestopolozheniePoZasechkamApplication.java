package kursovoi.mestopolozhenie_po_zasechkam;


import org.hibernate.mapping.Map;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;

/**
 * Основной класс MestopolozheniePoZasechkamApplication
 * @author Владислав Брылёв
 * @version 2.0
 */

@SpringBootApplication
public class MestopolozheniePoZasechkamApplication {

    static public JFrame MainWindow = getFrame();

    static public JFrame AuthorizationWindow = getFrame();

    static public JFrame AddUserWindow = getFrame();

    static public JFrame NastroykiWindow = getFrame();

    static public JFrame MapWindow = getFrame();

    static public JFrame RaschetUglovWindow = getFrame();

    static public JFrame RaschetPryamougolnyhKoordinatWindow = getFrame();

    static public JFrame SpisokResultatovWindow = getFrame();

    static public JFrame RedaktirovanieRezultataWindow = getFrame();

    static public JFrame SpisokPolzovateleyWindow = getFrame();

    static public JFrame ObAvtoreWindow = getFrame();

    static public String[] data_of_user; //0-id, 1-nick, 2-login, 3-password, 4-role

    static public String[] types ={
            "Рельеф",
            "Дорожная",
            "Гибридный",
            "Спутник",
    };

    static public JTable table_townsCoordinates;
    static public JTable table_results;

    static public JTable table_polzovateli;

    static public JComboBox types_of_map_combobox = new JComboBox(types);

    static public int Okruglenie_mestopolozheniya = 0;





    public static void main(String[] args) {

        SpringApplication.run(MestopolozheniePoZasechkamApplication.class, args);

        MainWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    MainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        AuthorizationWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    AuthorizationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    AuthorizationWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        MapWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    MapWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    MapWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        AddUserWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    AddUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    AddUserWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        NastroykiWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    NastroykiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    NastroykiWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        RaschetUglovWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        RaschetUglovWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//                String[] options = new String[2];
//                options[0]="Да";
//                options[1]="Нет";
//                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
//                        "закрыть окно расчета углов?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

//                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    RaschetUglovWindow.dispose();
//                } else {
//                    NastroykiWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//                }
            }
        });

        RaschetPryamougolnyhKoordinatWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        RaschetPryamougolnyhKoordinatWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                RaschetPryamougolnyhKoordinatWindow.dispose();
            }
        });

        SpisokResultatovWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    SpisokResultatovWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    SpisokResultatovWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        RedaktirovanieRezultataWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    RedaktirovanieRezultataWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    RedaktirovanieRezultataWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        SpisokPolzovateleyWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    SpisokPolzovateleyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    SpisokPolzovateleyWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        ObAvtoreWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ObAvtoreWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ObAvtoreWindow.dispose();
            }
        });


        JTextField loginField = new JTextField(25);
        loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginField.setFont(new Font("Serif",Font.BOLD, 25));
        JLabel label_login = new JLabel("Логин:");
        label_login.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_login.setFont(new Font("Serif",Font.BOLD, 25));

        JPasswordField passwordField = new JPasswordField(25);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Serif",Font.BOLD, 25));
        JLabel label_password = new JLabel("Пароль:");
        label_password.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_password.setFont(new Font("Serif",Font.BOLD, 25));

        JButton voity_button = new JButton("Войти");
        voity_button.setBounds(40, 0, 200,100);
        voity_button.setPreferredSize(new Dimension(25,50));
        voity_button.setFont(new Font("Serif",Font.BOLD, 25));

        JPanel data_panel = new JPanel();
        data_panel.setLayout(new GridLayout(20,1));
        data_panel.add(Box.createVerticalStrut(5));
//        data_panel.add(Box.createVerticalStrut(5));
//        data_panel.add(Box.createVerticalStrut(5));
//        data_panel.add(Box.createVerticalStrut(5));
//        data_panel.add(Box.createVerticalStrut(5));
        data_panel.add(label_login);
        data_panel.add(loginField);
        data_panel.add(Box.createVerticalStrut(5));
        data_panel.add(label_password);
        data_panel.add(passwordField);
//        data_panel.add(Box.createVerticalStrut(2));
        data_panel.add(Box.createVerticalStrut(2));
        data_panel.add(Box.createVerticalStrut(2));
        data_panel.add(voity_button);

        AuthorizationWindow.add(data_panel);
        AuthorizationWindow.setVisible(true);

        voity_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (loginField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Не заполнен логин!","Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (passwordField.getPassword().length == 0){
                    JOptionPane.showMessageDialog(null, "Не заполнен пароль!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                voity_button_actionPerformed(e, loginField.getText().replaceAll("\\s+",""), new String(passwordField.getPassword()));
            }
        });

    }

    public static JFrame getFrame(){

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        //int sizeWidth = screensize.width;
        //int sizeHeight = screensize.height;
        int sizeWidth = 1000;
        int sizeHeight = 600;
        int locationX = (screensize.width - sizeWidth) / 2;
        int locationY = (screensize.height - sizeHeight) / 2;

        JFrame jFrame = new JFrame();
        jFrame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
       // jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        ImageIcon img = new ImageIcon("marker.png");

//        jFrame.getContentPane().setBackground(Color.darkGray);

        jFrame.setIconImage(img.getImage());


        jFrame.setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLayout(new FlowLayout());
        jFrame.setTitle("Местоположение");

        return jFrame;
    }

    public static void voity_button_actionPerformed(ActionEvent e, String login, String password) {
        MainWindow.setLayout(null);
        RestTemplate r = new RestTemplate();
        String result = r.getForObject("http://localhost:8080/user_by_loginAndPassword?str_login=" + login + "&str_password=" + password, String.class);

        if(Objects.equals(result,null)){
            JOptionPane.showMessageDialog(null,"Ошибка! Неверный логин или пароль!");
            return;
        }
        data_of_user = result.replaceAll("\\s+","").split(",");
        AuthorizationWindow.setVisible(false);

        JButton add_user_button = new JButton("Создать/Удалить пользователя");
        add_user_button.setBounds(0, 150, 200,100);
        add_user_button.setPreferredSize(new Dimension(75,75));
        add_user_button.setFont(new Font("Serif",Font.BOLD, 25));
        add_user_button.setAlignmentX(MainWindow.getAlignmentX());
        add_user_button.setActionCommand("open_add_user_form");
        add_user_button.setVisible(true);

        add_user_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!data_of_user[4].equals("polnye")){
                    JOptionPane.showMessageDialog(null,"Создавать или удалять пользователей " +
                            "можно только с полными правами!","Инфо", JOptionPane.ERROR_MESSAGE);
                    return;
                };
                add_user_button_actionPerformed(e);
            }
        });

        JButton nastroyki_button = new JButton("Настройки");
        nastroyki_button.setBounds(0, 150, 200,100);
        nastroyki_button.setFont(new Font("Serif",Font.BOLD, 25));
        nastroyki_button.setAlignmentX(MainWindow.getAlignmentX());
        nastroyki_button.setVisible(true);

        nastroyki_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nastroyki_button_actionPerformed(e);
            }
        });

        JButton opredelit_mestopolozhenie = new JButton("Определить местоположение");
        opredelit_mestopolozhenie.setBounds(0, 150, 200,100);
        opredelit_mestopolozhenie.setFont(new Font("Serif",Font.BOLD, 25));
        opredelit_mestopolozhenie.setAlignmentX(MainWindow.getAlignmentX());
        opredelit_mestopolozhenie.setVisible(true);

        opredelit_mestopolozhenie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opredelit_mestopolozhenie_actionPerformed(e);
            }
        });

        JButton spisok_resultatov_button = new JButton("Список результатов вычислений");
        spisok_resultatov_button.setBounds(0, 150, 200,100);
        spisok_resultatov_button.setFont(new Font("Serif",Font.BOLD, 25));
        spisok_resultatov_button.setAlignmentX(MainWindow.getAlignmentX());
        spisok_resultatov_button.setVisible(true);

        spisok_resultatov_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spisok_resultatov_button_actionPerformed(e);
            }
        });

        JButton ob_avtore = new JButton("Об авторе");
        ob_avtore.setBounds(820, 490, 150,50);
        ob_avtore.setFont(new Font("Serif",Font.BOLD, 25));
        ob_avtore.setAlignmentX(MainWindow.getAlignmentX());
        ob_avtore.setVisible(true);

        ob_avtore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ObAvtoreWindow.setSize(new Dimension(435,210));
                ObAvtoreWindow.setTitle("Об авторе");
                ObAvtoreWindow.setLayout(null);
                JLabel obAvtore_label = new JLabel("<html><pre>       Финансовый университет<br></pre>" +
                        "<br>Разработчик: Брылёв Владислав Дмитриевич<br>" +
                        "Группа: ЗБ-ПИ21-1с<br>" +
                        "Год выпуска ПО: 2022<br>" +
                        "Электронная почта: 215733@edu.fa.ru</html>");
                obAvtore_label.setBounds(10,1,450,156);
                obAvtore_label.setFont(new Font("Serif",Font.BOLD, 18));
                ObAvtoreWindow.add(obAvtore_label);
                ObAvtoreWindow.setVisible(true);
            }
        });


        JPanel menu_panel = new JPanel();
        //menu_panel.add(Box.createVerticalStrut(2));
        menu_panel.setBounds(215,120,550,1300);
        menu_panel.setLayout(new GridLayout(20,1));
        //menu_panel.add(Box.createHorizontalStrut(300));

        menu_panel.add(opredelit_mestopolozhenie);
//        menu_panel.add(Box.createVerticalStrut(1));
        menu_panel.add(add_user_button);
//        menu_panel.add(Box.createVerticalStrut(1));
        menu_panel.add(spisok_resultatov_button);
//        menu_panel.add(Box.createVerticalStrut(1));
        menu_panel.add(nastroyki_button);
        menu_panel.setVisible(true);


        MainWindow.add(ob_avtore);
        MainWindow.add(menu_panel);
        MainWindow.setVisible(true);


    }


    public static void add_user_button_actionPerformed(ActionEvent e) {
        if("open_add_user_form".equals(e.getActionCommand())){
            MainWindow.setVisible(false);
            AddUserWindow.setSize(new Dimension(1000,610));
            AddUserWindow.setTitle("Пользователи");
           // JFrame AddUserWindow = getFrame();

            JButton back_to_main_window_button = new JButton("Назад");
            back_to_main_window_button.setBounds(0, 150, 200,100);
            back_to_main_window_button.setAlignmentX(MainWindow.getAlignmentX());
            back_to_main_window_button.setActionCommand("open_add_user_form");

            back_to_main_window_button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    back_to_main_window_button_actionPerformed(e, AddUserWindow);
                }
            });

            JTextField textfield_nickname = new JTextField(50);
            textfield_nickname.setAlignmentX(Component.CENTER_ALIGNMENT);
            textfield_nickname.setFont(new Font("Serif",Font.BOLD, 18));
            JLabel label_nickname = new JLabel("Никнейм:");
            label_nickname.setAlignmentX(Component.CENTER_ALIGNMENT);
            label_nickname.setFont(new Font("Serif",Font.BOLD, 18));

            JTextField textfield_login = new JTextField(50);
            textfield_login.setAlignmentX(Component.CENTER_ALIGNMENT);
            textfield_login.setFont(new Font("Serif",Font.BOLD, 18));
            JLabel label_login = new JLabel("Логин:");
            label_login.setAlignmentX(Component.CENTER_ALIGNMENT);
            label_login.setFont(new Font("Serif",Font.BOLD, 18));

            JPasswordField textfield_password = new JPasswordField(50);
            textfield_password.setAlignmentX(Component.CENTER_ALIGNMENT);
            textfield_password.setFont(new Font("Serif",Font.BOLD, 18));
            JLabel label_password = new JLabel("Пароль:");
            label_password.setAlignmentX(Component.CENTER_ALIGNMENT);
            label_password.setFont(new Font("Serif",Font.BOLD, 18));


            JPasswordField textfield_password_confirm = new JPasswordField(50);
            textfield_password_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
            textfield_password_confirm.setFont(new Font("Serif",Font.BOLD, 18));
            JLabel label_password_confirm = new JLabel("Подтвердите пароль:");
            label_password_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
            label_password_confirm.setFont(new Font("Serif",Font.BOLD, 18));

            JButton zapisat_v_bd_button = new JButton("Записать пользователя");
            zapisat_v_bd_button.setBounds(0, 150, 200,100);
            zapisat_v_bd_button.setAlignmentX(MainWindow.getAlignmentX());
            //zapisat_v_bd_button.setActionCommand("open_add_user_form");

            JButton udalit_iz_bd_button = new JButton("Удалить пользователя");
            udalit_iz_bd_button.setBounds(0, 150, 200,100);
            udalit_iz_bd_button.setAlignmentX(MainWindow.getAlignmentX());
            udalit_iz_bd_button.setPreferredSize(new Dimension(25,35));

            JButton poluchit_dannye_po_loginu = new JButton("Получить данные по логину");
            zapisat_v_bd_button.setBounds(0, 150, 200,100);
            zapisat_v_bd_button.setAlignmentX(MainWindow.getAlignmentX());

            JButton spisok_polzovateley_button = new JButton("Список пользователей");
            spisok_polzovateley_button.setBounds(0, 150, 200,100);
            spisok_polzovateley_button.setAlignmentX(MainWindow.getAlignmentX());


            String[] roles ={
                    "Полные права",
                    "Стандартные права"
            };
            JComboBox roles_combobox = new JComboBox<>(roles);
            roles_combobox.setFont(new Font("Serif",Font.BOLD, 18));
            JLabel label_roles = new JLabel("Права:");
            label_roles.setAlignmentX(Component.CENTER_ALIGNMENT);
            label_roles.setFont(new Font("Serif",Font.BOLD, 18));

            zapisat_v_bd_button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    zapisat_v_bd_button_polzovatelya_actionPerformed(e, textfield_login.getText(),
                                                           textfield_nickname.getText(),
                                                           new String(textfield_password.getPassword()),
                                                           new String(textfield_password_confirm.getPassword()),
                                                           (String)roles_combobox.getSelectedItem());
                }
            });

            udalit_iz_bd_button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    udalit_iz_bd_button_actionPerformed(e, textfield_login.getText());
                }
            });

            spisok_polzovateley_button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    SpisokPolzovateleyWindow.setTitle("Список пользователей");

                    JButton back_koordinaty_button = new JButton("Назад");
                    back_koordinaty_button.setBounds(430, 520, 130,30);
                    back_koordinaty_button.setAlignmentX(MainWindow.getAlignmentX());
                    back_koordinaty_button.setPreferredSize(new Dimension(130,35));
                    back_koordinaty_button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            SpisokPolzovateleyWindow.dispose();
                            AddUserWindow.setVisible(true);
                        }
                    });

                    RestTemplate r = new RestTemplate();
                    String result_data = r.getForObject("http://localhost:8080/get_data_polzovately", String.class);

                    List<String> result_data_list = Arrays.asList(result_data.split("\",\""));
                    result_data_list.removeAll(Arrays.asList("",null));


                    String[] result_data_mas = result_data_list.toArray(new String[result_data_list.size()]);

                    String[] columnsNames = {
                            "Идентификатор",
                            "Никнейм",
                            "Логин",
                            "Роль"
                    };

                    String[][] data_for_table = new String[result_data_mas.length][4];

                    for(int i=0;i<result_data_mas.length;i++){

                        var m = result_data_mas[i].replace("\"", "")
                                .replace("[", "")
                                .replace("]", "")
                                .split(",");
                        for(int j=0;j<4;j++) {
                            data_for_table[i][j] = m[j];
                        }


                    }


                    table_polzovateli = new JTable(data_for_table,columnsNames);
                    table_polzovateli.setBounds(0,0, 980,400);
                    table_polzovateli.setDefaultEditor(Object.class, null);
                    table_polzovateli.setAutoCreateRowSorter(true);


                    JLabel poisk_label = new JLabel("Поиск 1:");
                    poisk_label.setBounds(10, -2, 50,30);
                    JTextField poisk_textfield = new JTextField(100);
                    poisk_textfield.setBounds(10, 20, 100,30);

                    JLabel poisk2_label = new JLabel("Поиск 2:");
                    poisk2_label.setBounds(140, -2, 50,30);
                    JTextField poisk2_textfield = new JTextField(100);
                    poisk2_textfield.setBounds(140, 20, 100,30);

                    JButton poisk_button = new JButton("Искать");
                    poisk_button.setBounds(430, 490, 130,30);
                    poisk_button.setAlignmentX(MainWindow.getAlignmentX());
                    poisk_button.setPreferredSize(new Dimension(130,35));

                    JButton obnovit_button = new JButton("Обновить");
                    obnovit_button.setBounds(430, 460, 130,30);
                    obnovit_button.setAlignmentX(MainWindow.getAlignmentX());
                    obnovit_button.setPreferredSize(new Dimension(130,35));

                    JScrollPane scrollPane = new JScrollPane(table_polzovateli);
                    scrollPane.setBounds(10,50, 980,400);
                    scrollPane.setViewportView(table_polzovateli);

                    poisk_button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_polzovateli.getModel());
                            table_polzovateli.setRowSorter(sorter);
                            try {
                                if(!poisk_textfield.getText().isEmpty() && poisk2_textfield.getText().isEmpty() ) {
                                    sorter.setRowFilter(RowFilter.regexFilter(poisk_textfield.getText()));
                                } else if(!poisk2_textfield.getText().isEmpty() && poisk_textfield.getText().isEmpty()){
                                    sorter.setRowFilter(RowFilter.regexFilter(poisk2_textfield.getText()));
                                }else if(!poisk2_textfield.getText().isEmpty() && !poisk_textfield.getText().isEmpty()){
                                    RowFilter rv1 = RowFilter.regexFilter(poisk_textfield.getText(),0);
                                    RowFilter rv2 = RowFilter.regexFilter(poisk2_textfield.getText(),1);
                                    System.out.println(poisk_textfield.getText());
                                    System.out.println(poisk2_textfield.getText());
                                    List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
                                    filters.add(RowFilter.regexFilter(poisk2_textfield.getText(),0,1,2,3));
                                    filters.add(RowFilter.regexFilter(poisk2_textfield.getText(),0,1,2,3));
                                    RowFilter rv3 = RowFilter.andFilter(filters);
                                    sorter.setRowFilter(rv3);
                                } else{
                                    sorter.setRowFilter(null);
                                }
                            }catch(Throwable exp){}
                        }
                    });

                    obnovit_button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String result_data = r.getForObject("http://localhost:8080/get_data_polzovately", String.class);

                            List<String> result_data_list = Arrays.asList(result_data.split("\",\""));
                            result_data_list.removeAll(Arrays.asList("",null));

                            String[] result_data_mas = result_data_list.toArray(new String[result_data_list.size()]);

                            String[] columnsNames = {
                                    "Идентификатор",
                                    "Никнейм",
                                    "Логин",
                                    "Роль"
                            };

                            String[][] data_for_table = new String[result_data_mas.length][11];

                            for(int i=0;i<result_data_mas.length;i++){

                                var m = result_data_mas[i].replace("\"", "")
                                        .replace("[", "")
                                        .replace("]", "")
                                        .split(",");
                                for(int j=0;j<4;j++) {
                                    data_for_table[i][j] = m[j];
                                }


                            }

                            table_polzovateli = new JTable(data_for_table,columnsNames);
                            table_polzovateli.setDefaultEditor(Object.class, null);
                            table_polzovateli.setAutoCreateRowSorter(true);
                            scrollPane.setViewportView(table_polzovateli);

                        }
                    });


                    JPanel data_panel_results = new JPanel();
                    data_panel_results.setLayout(null);
                    data_panel_results.setBounds(0,0, 1000,600);
                    data_panel_results.add(poisk_label);
                    data_panel_results.add(poisk_textfield);
                    data_panel_results.add(poisk2_label);
                    data_panel_results.add(poisk2_textfield);
                    data_panel_results.add(scrollPane);
                    data_panel_results.add(obnovit_button);
                    data_panel_results.add(poisk_button);
                    data_panel_results.add(back_koordinaty_button);
                    data_panel_results.setPreferredSize(new Dimension(1000,600));

                    SpisokPolzovateleyWindow.add(data_panel_results);
                    AddUserWindow.setVisible(false);
                    SpisokPolzovateleyWindow.setVisible(true);



                }
            });







            poluchit_dannye_po_loginu.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

//                    udalit_iz_bd_button_actionPerformed(e, textfield_login.getText());
                    RestTemplate r = new RestTemplate();
                    if(textfield_login.getText().replaceAll("\\s+","").isEmpty()){
                        JOptionPane.showMessageDialog(null, "Не заполнен логин!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    String resultat_polucheniya_dannyh =  r.getForObject("http://localhost:8080/user_by_login?str_login=" + textfield_login.getText().replaceAll("\\s+",""), String.class);

                    if(Objects.equals(resultat_polucheniya_dannyh,null)){
                        JOptionPane.showMessageDialog(null, "Пользователь с таким логином не найден!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    String[] tekdannye = resultat_polucheniya_dannyh.split(",");
                    textfield_nickname.setText(tekdannye[1].replaceAll("\\s+",""));
                    textfield_login.setText(tekdannye[2].replaceAll("\\s+",""));
                    textfield_password.setText(tekdannye[3].replaceAll("\\s+",""));
                    textfield_password_confirm.setText(tekdannye[3].replaceAll("\\s+",""));
                   // textfield_nickname.setText(tekdannye[4].replaceAll("\\s+",""));

                    if(tekdannye[4].replaceAll("\\s+","").equals("polnye")){
                        roles_combobox.setSelectedIndex(0);
                    }
                    else{
                        roles_combobox.setSelectedIndex(1);
                    }

                }
            });

            JPanel data_panel = new JPanel();
            data_panel.setLayout(new GridLayout(20,1));
//            data_panel.add(Box.createVerticalStrut(5));
            data_panel.add(label_nickname);
            data_panel.add(textfield_nickname);
            data_panel.add(label_login);
            data_panel.add(textfield_login);
            data_panel.add(label_password);
            data_panel.add(textfield_password);
            data_panel.add(label_password_confirm);
            data_panel.add(textfield_password_confirm);
            data_panel.add(label_roles);
            data_panel.add(roles_combobox);
            data_panel.add(Box.createVerticalStrut(1));
//            data_panel.add(Box.createVerticalStrut(1));
//            data_panel.add(Box.createVerticalStrut(2));
            data_panel.add(zapisat_v_bd_button);
            data_panel.add(poluchit_dannye_po_loginu);
            data_panel.add(spisok_polzovateley_button);
            data_panel.add(udalit_iz_bd_button);
            data_panel.add(back_to_main_window_button);


            AddUserWindow.add(data_panel);

            data_panel.revalidate();

            AddUserWindow.setVisible(true);



        }
    }

    public static void back_to_main_window_button_actionPerformed(ActionEvent e, Window AddUserWindow) {
        AddUserWindow.dispose();
        MainWindow.setVisible(true);
    }

    public static void zapisat_v_bd_button_polzovatelya_actionPerformed(ActionEvent e, String login, String nickname, String password, String password_confirm, String role) {
        RestTemplate r = new RestTemplate();

        String result = r.getForObject("http://localhost:8080/user_by_login?str_login=" + login, String.class);

        if(nickname.isEmpty()){
            JOptionPane.showMessageDialog(null, "Не заполнен никнейм!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(login.isEmpty()){
            JOptionPane.showMessageDialog(null, "Не заполнен логин!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Не заполнен пароль!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(password.length()<8){
            JOptionPane.showMessageDialog(null, "Длина пароля должна быть не менее 8 символов!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(password_confirm.isEmpty()){
            JOptionPane.showMessageDialog(null, "Не заполнен подтвержденный пароль!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        if(!password.equals(password_confirm)){
            JOptionPane.showMessageDialog(null,"Пароль и подвержденный пароль не совпадают!","Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(!Objects.equals(result,null)){

            String[] options = new String[2];
            options[0]="Да";
            options[1]="Нет";

            String[] tek_dannye = result.split(",");

            String[] parametry_update = new String[5];

            parametry_update[0] = tek_dannye[0].replaceAll("\\s+","");
            parametry_update[1] = nickname.replaceAll("\\s+","");
            parametry_update[2] = login.replaceAll("\\s+","");
            parametry_update[3] = password;

            if(role.equals("Полные права")){
                role = "polnye";
            }
            else{
                role = "nepolnye";
            }

            parametry_update[4] = role;

            int vybor = JOptionPane.showOptionDialog(null, "Пользователь с таким логином уже существует! Вы действительно хотите " +
                    "изменить данные пользователя с логином " + login + "?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

            if(Objects.equals(vybor, JOptionPane.YES_OPTION)){
                String result_post = r.postForObject("http://localhost:8080/update_user", parametry_update, String.class);
                if(result_post.equals("ok")){
                    JOptionPane.showMessageDialog(null,"Данные пользователя изменены!","Инфо", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            return;
        }



        Long last_id = r.getForObject("http://localhost:8080/get_user_last_id", Long.class) + 1;
        if(role.equals("Полные права")){
            role = "polnye";
        }
        else{
            role = "nepolnye";
        }
        String result_post = r.postForObject("http://localhost:8080/set_user", new User(last_id,nickname,login,password,role), String.class);
        if(result_post.equals("ok")){
            JOptionPane.showMessageDialog(null,"Пользователь создан!","Инфо", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void udalit_iz_bd_button_actionPerformed(ActionEvent e, String login){

        if(login.isEmpty()){
            JOptionPane.showMessageDialog(null, "Не заполнен логин!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        RestTemplate r = new RestTemplate();

        String result = r.getForObject("http://localhost:8080/user_by_login?str_login=" + login, String.class);

        if(Objects.equals(result,null)){
            JOptionPane.showMessageDialog(null,"Пользователя с таким логином не существует!","Инфо", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] options = new String[2];
        options[0]="Да";
        options[1]="Нет";
        int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                "удалить пользователя с логином " + login + "?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

        if(Objects.equals(vybor, JOptionPane.YES_OPTION)){
            String result_post = r.postForObject("http://localhost:8080/delete_user", login, String.class);
            if(result_post.equals("ok")){
                JOptionPane.showMessageDialog(null,"Пользователь удален!","Инфо", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public static void opredelit_mestopolozhenie_actionPerformed(ActionEvent e){

        MapWindow = getFrame();

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

//        String type_of_map = (String)types_of_map_combobox.getSelectedItem();
        RestTemplate r = new RestTemplate();
        String nastroyki_polzovatelya = r.getForObject("http://localhost:8080/nastroyki_by_id?id="+ data_of_user[0],
                String.class);

        if(!Objects.equals(nastroyki_polzovatelya, null)) {
            nastroyki_polzovatelya = nastroyki_polzovatelya.replaceAll("\\s+","");
            String[] nastroyki_polzovatelya_mas = nastroyki_polzovatelya.split(",");
            types_of_map_combobox.setSelectedIndex(Integer.parseInt(nastroyki_polzovatelya_mas[0]));
            Okruglenie_mestopolozheniya = Integer.parseInt(nastroyki_polzovatelya_mas[1]);
        }
        String type_of_map = (String)types_of_map_combobox.getSelectedItem();

        TileFactoryInfo info = new OSMTileFactoryInfo();

        if (type_of_map.equals("Рельеф")){
            info = new OSMTileFactoryInfo();
        }else if(type_of_map.equals("Дорожная")){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        }else if(type_of_map.equals("Гибридный")){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        }else if(type_of_map.equals("Спутник")){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }


        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        JXMapViewer mapw = new JXMapViewer();
        mapw.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(55.5815181,36.8237172);
        mapw.setPreferredSize(new Dimension(screensize.width,screensize.height));
        mapw.setAddressLocation(geo);
        mapw.setZoom(12);


        JLabel label_ugol1 = new JLabel("Угол первой точки:");
        label_ugol1.setBounds(3, 0, 130,30);
        JTextField ugol1_textfield = new JTextField(80);
        ugol1_textfield.setBounds(119, 0, 80,30);

        JLabel label_ugol2 = new JLabel("Угол второй точки:");
        label_ugol2.setBounds(201, 0, 130,30);
        JTextField ugol2_textfield = new JTextField(80);
        ugol2_textfield.setBounds(313, 0, 80,30);

        JLabel x1_label = new JLabel("x1:");
        x1_label.setBounds(395, 0, 20,30);
        JTextField x1_textfield = new JTextField(80);
        x1_textfield.setBounds(413, 0, 80,30);

        JLabel y1_label = new JLabel("y1:");
        y1_label.setBounds(496, 0, 20,30);
        JTextField y1_textfield = new JTextField(80);
        y1_textfield.setBounds(514, 0, 80,30);

        JLabel x2_label = new JLabel("x2:");
        x2_label.setBounds(597, 0, 20,30);
        JTextField x2_textfield = new JTextField(80);
        x2_textfield.setBounds(615, 0, 80,30);

        JLabel y2_label = new JLabel("y2:");
        y2_label.setBounds(698, 0, 20,30);
        JTextField y2_textfield = new JTextField(80);
        y2_textfield.setBounds(716, 0, 80,30);


        //ugol1_textfield.setAlignmentX(Component.CENTER_ALIGNMENT);
        //ugol1_textfield.setFont(new Font("Serif",Font.BOLD, 18));

//        label_nickname.setAlignmentX(Component.CENTER_ALIGNMENT);
//        label_nickname.setFont(new Font("Serif",Font.BOLD, 18));

        JLabel mestopolozhenie_label = new JLabel("Местоположение:");
        mestopolozhenie_label.setBounds(799, 0, 180,30);

        JLabel mest_x_label = new JLabel("x:");
        mest_x_label.setBounds(910, 0, 20,30);
        JTextField mest_x_textfield = new JTextField(80);
        mest_x_textfield.setBounds(920, 0, 80,30);

        JLabel mest_y_label = new JLabel("y:");
        mest_y_label.setBounds(1003, 0, 20,30);
        JTextField mest_y_textfield = new JTextField(80);
        mest_y_textfield.setBounds(1013, 0, 80,30);


        JButton delete_marks_button_map = new JButton("Удалить метки");
        delete_marks_button_map.setBounds(1350, 0, 180,30);
        delete_marks_button_map.setAlignmentX(MainWindow.getAlignmentX());
        delete_marks_button_map.setPreferredSize(new Dimension(25,35));

        JButton back_button_map = new JButton("Назад");
        //back_button_map.setBounds(1435, 0, 100,30);
        back_button_map.setBounds(screensize.width-100, 0, 100,30);
        back_button_map.setAlignmentX(MainWindow.getAlignmentX());
        back_button_map.setPreferredSize(new Dimension(25,35));

        back_button_map.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back_button_map_actionPerformed(e);
            }
        });

        JButton opredelit_mestopolozhenie_button = new JButton("Определить местоположение");
        opredelit_mestopolozhenie_button.setBounds(1100, 0, 230,30);
        opredelit_mestopolozhenie_button.setAlignmentX(MainWindow.getAlignmentX());
        opredelit_mestopolozhenie_button.setPreferredSize(new Dimension(25,35));

        JButton rasschitat_ugly_button = new JButton("Рассчитать угол");
        rasschitat_ugly_button.setBounds(0, screensize.height-136, 230,30);
        rasschitat_ugly_button.setAlignmentX(MainWindow.getAlignmentX());
        rasschitat_ugly_button.setPreferredSize(new Dimension(25,35));

        JButton ustanovit_metki_po_koordinatan_ugly_button = new JButton("Установить метки по координатам");
        ustanovit_metki_po_koordinatan_ugly_button.setBounds(507, screensize.height-136, 235,30);
        ustanovit_metki_po_koordinatan_ugly_button.setAlignmentX(MainWindow.getAlignmentX());
        ustanovit_metki_po_koordinatan_ugly_button.setPreferredSize(new Dimension(25,35));

        JButton rasschitat_prymougolnye_koordinaty_button = new JButton("Рассчитать прямоугольные координаты");
        rasschitat_prymougolnye_koordinaty_button.setBounds(231, screensize.height-136, 275,30);
        rasschitat_prymougolnye_koordinaty_button.setAlignmentX(MainWindow.getAlignmentX());
        rasschitat_prymougolnye_koordinaty_button.setPreferredSize(new Dimension(25,35));

        JButton zapisat_rezultaty_v_bd_button = new JButton("Записать результаты в базу данных");
        zapisat_rezultaty_v_bd_button.setBounds(742, screensize.height-136, 275,30);
        zapisat_rezultaty_v_bd_button.setAlignmentX(MainWindow.getAlignmentX());
        zapisat_rezultaty_v_bd_button.setPreferredSize(new Dimension(25,35));


        JLayeredPane lpane = new JLayeredPane();
        //lpane.setLayout(null);
        lpane.setPreferredSize(new Dimension(screensize.width,screensize.height));
        JPanel menu_panel = new JPanel();
//        menu_panel.setLayout(new GridLayout(2,1));
        menu_panel.setBounds(0,0,screensize.width,screensize.height);
        menu_panel.add(mapw);
        JPanel buton_panel = new JPanel();
        buton_panel.setLayout(null);
        buton_panel.setBounds(0,screensize.height-105,screensize.width,30);
        buton_panel.add(label_ugol1);
        buton_panel.add(ugol1_textfield);
        buton_panel.add(label_ugol2);
        buton_panel.add(ugol2_textfield);
        buton_panel.add(x1_label);
        buton_panel.add(x1_textfield);
        buton_panel.add(y1_label);
        buton_panel.add(y1_textfield);
        buton_panel.add(x2_label);
        buton_panel.add(x2_textfield);
        buton_panel.add(y2_label);
        buton_panel.add(y2_textfield);
        buton_panel.add(mestopolozhenie_label);
        buton_panel.add(mest_x_label);
        buton_panel.add(mest_x_textfield);
        buton_panel.add(mest_y_label);
        buton_panel.add(mest_y_textfield);
        buton_panel.add(opredelit_mestopolozhenie_button);
        buton_panel.add(delete_marks_button_map);
       // buton_panel.add(back_button_map);


        lpane.add(menu_panel,0,0);
        lpane.add(buton_panel,1,0);

        lpane.add(back_button_map,1,0);
        lpane.add(rasschitat_ugly_button,1,0);
        lpane.add(ustanovit_metki_po_koordinatan_ugly_button,1,0);
        lpane.add(rasschitat_prymougolnye_koordinaty_button,1,0);
        lpane.add(zapisat_rezultaty_v_bd_button,1,0);

        MapWindow.add(lpane);
        MapWindow.setResizable(true);
        MapWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
        MainWindow.setVisible(false);
        MapWindow.setVisible(true);



        MouseInputListener mm = new PanMouseInputListener(mapw);
        mapw.addMouseListener(mm);
        mapw.addMouseMotionListener(mm);
        mapw.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapw));

        Set<Waypoint> set = new HashSet();

        delete_marks_button_map.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "удалить метки?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    set.clear();
                    x1_textfield.setText("");
                    y1_textfield.setText("");
                    x2_textfield.setText("");
                    y2_textfield.setText("");
                    mest_x_textfield.setText("");
                    mest_y_textfield.setText("");
                    delete_marks_button_map_actionPerformed(e, mapw);
                }


            }
        });

        rasschitat_ugly_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RaschetUglovWindow.setSize(400,550);
                RaschetUglovWindow.setTitle("Расчёт угла");

                JLabel gradys_label = new JLabel("Градус:");
                gradys_label.setBounds(910, 0, 20,30);
                JTextField gradys_textfield = new JTextField(30);
                gradys_textfield.setBounds(920, 0, 30,30);


                JLabel minuta_label = new JLabel("Минута:");
                minuta_label.setBounds(910, 0, 20,30);
                JTextField minuta_textfield = new JTextField(30);
                minuta_textfield.setBounds(920, 0, 30,30);

                JLabel secunda_label = new JLabel("Секунда:");
                secunda_label.setBounds(910, 0, 20,30);
                JTextField secunda_textfield = new JTextField(30);
                secunda_textfield.setBounds(920, 0, 30,30);

                String[] count_of_digits_okruglenie ={
                        "Не округлять",
                        "1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        "13",
                        "14",
                        "15",
                        "16"
                };

                JLabel okruglenie_label = new JLabel("Округлять до n знаков:");
                okruglenie_label.setBounds(910, 0, 20,30);
                JComboBox okruglenie_combobox = new JComboBox(count_of_digits_okruglenie);

                JLabel result_ugol_label = new JLabel("Результат:");
                result_ugol_label.setBounds(910, 0, 20,30);
                JTextField result_ugol_textfield = new JTextField(30);
                result_ugol_textfield.setBounds(920, 0, 30,30);

                JButton result_ugol_button = new JButton("Рассчитать");
                result_ugol_button.setBounds(0, 0, 50,30);
                result_ugol_button.setAlignmentX(MainWindow.getAlignmentX());
                result_ugol_button.setPreferredSize(new Dimension(25,35));

                JButton back_ugly_button = new JButton("Назад");
                back_ugly_button.setBounds(0, 0, 30,30);
                back_ugly_button.setAlignmentX(MainWindow.getAlignmentX());
                back_ugly_button.setPreferredSize(new Dimension(25,35));
                back_ugly_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        RaschetUglovWindow.dispose();
                    }
                });

                JPanel data_panel = new JPanel();
                data_panel.setLayout(new GridLayout(20,1));
                data_panel.setBounds(0,0, 100,300);

                data_panel.add(gradys_label);
                data_panel.add(gradys_textfield);
                data_panel.add(minuta_label);
                data_panel.add(minuta_textfield);
                data_panel.add(secunda_label);
                data_panel.add(secunda_textfield);
                data_panel.add(okruglenie_label);
                data_panel.add(okruglenie_combobox);
                data_panel.add(Box.createVerticalStrut(1));
                data_panel.add(result_ugol_label);
                data_panel.add(result_ugol_textfield);
//                data_panel.add(Box.createVerticalStrut(1));
                data_panel.add(Box.createVerticalStrut(1));
                data_panel.add(result_ugol_button);
                data_panel.add(back_ugly_button);
//                data_panel.add(udalit_iz_bd_button);
//                data_panel.add(back_to_main_window_button);
                result_ugol_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if(gradys_textfield.getText().replaceAll("\\s+","").isEmpty()){
                            JOptionPane.showMessageDialog(null, "Не заполнено значение градуса!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if(minuta_textfield.getText().replaceAll("\\s+","").isEmpty()){
                            JOptionPane.showMessageDialog(null, "Не заполнено значение минуты!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if(secunda_textfield.getText().replaceAll("\\s+","").isEmpty()){
                            JOptionPane.showMessageDialog(null, "Не заполнено значение секунды!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        Double gradus   = 0.0;
                        Double minuta   = 0.0;
                        Double secunda   = 0.0;

                        try{
                            gradus   = Double.parseDouble(gradys_textfield.getText().replaceAll("\\s+",""));
                        }catch (Throwable excep){
                            JOptionPane.showMessageDialog(null, "Значение градуса не может " +
                                    "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        };
                        try{
                            minuta   = Double.parseDouble(minuta_textfield.getText().replaceAll("\\s+",""));
                        }catch (Throwable excep){
                            JOptionPane.showMessageDialog(null, "Значение минуты не может " +
                                    "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        };
                        try{
                        secunda  = Double.parseDouble(secunda_textfield.getText().replaceAll("\\s+",""));
                        }catch (Throwable excep){
                            JOptionPane.showMessageDialog(null, "Значение секунды не может " +
                                    "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        };

                        Double result_ugol = gradus + minuta/60 + secunda/3600;
                        result_ugol = result_ugol * Math.PI/180;

                        BigDecimal vyvod = new BigDecimal(result_ugol);

                        if(okruglenie_combobox.getSelectedIndex()!=0){

                            vyvod = vyvod.setScale(okruglenie_combobox.getSelectedIndex(), RoundingMode.HALF_EVEN);

                        }

                        result_ugol_textfield.setText(vyvod.toString());
                        result_ugol_textfield.setCaretPosition(0);

                    }
                });


                RaschetUglovWindow.add(data_panel);
                RaschetUglovWindow.setVisible(true);

            }
        });


        rasschitat_prymougolnye_koordinaty_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RaschetPryamougolnyhKoordinatWindow.setSize(800,600);
                RaschetPryamougolnyhKoordinatWindow.setTitle("Рассчет прямоугольных координат");

                JButton back_koordinaty_button = new JButton("Назад");
                back_koordinaty_button.setBounds(380, 520, 130,30);
                back_koordinaty_button.setAlignmentX(MainWindow.getAlignmentX());
                back_koordinaty_button.setPreferredSize(new Dimension(130,35));
                back_koordinaty_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        RaschetPryamougolnyhKoordinatWindow.dispose();
                    }
                });

                RestTemplate r = new RestTemplate();
                String result_data = r.getForObject("http://localhost:8080/get_data_TownsCoordinates", String.class);

                List<String> result_data_list = Arrays.asList(result_data.split("\",\""));
                result_data_list.removeAll(Arrays.asList("",null));

                String[] result_data_mas = result_data_list.toArray(new String[result_data_list.size()]);

                String[] columnsNames = {
                        "Регион",
                        "Город",
                        "Широта",
                        "Долгота"
                };

                String[][] data_for_table = new String[result_data_mas.length][5];

                for(int i=0;i<result_data_mas.length;i++){

                        var m = result_data_mas[i].replace("\"", "")
                                .replace("[", "")
                                .replace("]", "")
                                .split(",");
                        for(int j=0;j<4;j++) {
                            data_for_table[i][j] = m[j];
                        }


                }


                table_townsCoordinates = new JTable(data_for_table,columnsNames);
                table_townsCoordinates.setBounds(0,0, 700,400);
                table_townsCoordinates.setDefaultEditor(Object.class, null);
                table_townsCoordinates.setAutoCreateRowSorter(true);



                //table_townsCoordinates.setRowSorter(sorter);

                JLabel poisk_label = new JLabel("Поиск 1:");
                poisk_label.setBounds(50, -2, 50,30);
                JTextField poisk_textfield = new JTextField(100);
                poisk_textfield.setBounds(50, 20, 100,30);

                JLabel poisk2_label = new JLabel("Поиск 2:");
                poisk2_label.setBounds(180, -2, 50,30);
                JTextField poisk2_textfield = new JTextField(100);
                poisk2_textfield.setBounds(180, 20, 100,30);

                JLabel x_label = new JLabel("x:");
                x_label.setBounds(30, 490, 50,30);
                JTextField x_textfield = new JTextField(100);
                x_textfield.setBounds(50, 490, 100,30);

                JLabel y_label = new JLabel("y:");
                y_label.setBounds(30, 520, 50,30);
                JTextField y_textfield = new JTextField(100);
                y_textfield.setBounds(50, 520, 100,30);

                JButton poisk_button = new JButton("Искать");
                poisk_button.setBounds(380, 490, 130,30);
                poisk_button.setAlignmentX(MainWindow.getAlignmentX());
                poisk_button.setPreferredSize(new Dimension(130,35));

                JButton obnovit_button = new JButton("Обновить");
                obnovit_button.setBounds(380, 460, 130,30);
                obnovit_button.setAlignmentX(MainWindow.getAlignmentX());
                obnovit_button.setPreferredSize(new Dimension(130,35));

                JScrollPane scrollPane = new JScrollPane(table_townsCoordinates);
                scrollPane.setBounds(50,50, 700,400);
                scrollPane.setViewportView(table_townsCoordinates);

                table_townsCoordinates.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
//                        int col = table_townsCoordinates.columnAtPoint(e.getPoint());
                        int row = table_townsCoordinates.rowAtPoint(e.getPoint());
                        //if(==3) {
                        double latitude   = Double.parseDouble(table_townsCoordinates.getValueAt(row,2).toString());
                        double longtitude = Double.parseDouble(table_townsCoordinates.getValueAt(row,3).toString());

                        System.out.println(latitude);
                        System.out.println(longtitude);


                        GeoPosition geo = new GeoPosition(latitude, longtitude);
                        Point2D p2d = mapw.convertGeoPositionToPoint(geo);
                        Double x = p2d.getX();
                        Double y = p2d.getY();
                        x_textfield.setText(x.toString());
                        x_textfield.setCaretPosition(0);
                        y_textfield.setText(y.toString());
                        y_textfield.setCaretPosition(0);

                        //}

                    }
                });


                poisk_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_townsCoordinates.getModel());
                        table_townsCoordinates.setRowSorter(sorter);
                        try {
                            if(!poisk_textfield.getText().isEmpty() && poisk2_textfield.getText().isEmpty() ) {
                                sorter.setRowFilter(RowFilter.regexFilter(poisk_textfield.getText()));
                            } else if(!poisk2_textfield.getText().isEmpty() && poisk_textfield.getText().isEmpty()){
                                sorter.setRowFilter(RowFilter.regexFilter(poisk2_textfield.getText()));
                            }else if(!poisk2_textfield.getText().isEmpty() && !poisk_textfield.getText().isEmpty()){
                                RowFilter rv1 = RowFilter.regexFilter(poisk_textfield.getText(),0);
                                RowFilter rv2 = RowFilter.regexFilter(poisk2_textfield.getText(),1);
                                List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
                                filters.add(rv1);
                                filters.add(rv2);
                                RowFilter rv3 = RowFilter.andFilter(filters);
                                sorter.setRowFilter(rv3);
                            } else{
                                sorter.setRowFilter(null);
                            }
                        }catch(Throwable exp){}
                    }
                });



                obnovit_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String result_data = r.getForObject("http://localhost:8080/get_data_TownsCoordinates", String.class);

                        List<String> result_data_list = Arrays.asList(result_data.split("\",\""));
                        result_data_list.removeAll(Arrays.asList("",null));

                        String[] result_data_mas = result_data_list.toArray(new String[result_data_list.size()]);

                        String[] columnsNames = {
                                "Регион",
                                "Город",
                                "Широта",
                                "Долгота"
                        };

                        String[][] data_for_table = new String[result_data_mas.length][5];

                        for(int i=0;i<result_data_mas.length;i++){

                            var m = result_data_mas[i].replace("\"", "")
                                    .replace("[", "")
                                    .replace("]", "")
                                    .split(",");
                            for(int j=0;j<4;j++) {
                                data_for_table[i][j] = m[j];
                            }


                        }

                        table_townsCoordinates = new JTable(data_for_table,columnsNames);
                        table_townsCoordinates.setDefaultEditor(Object.class, null);
                        table_townsCoordinates.setAutoCreateRowSorter(true);
                        table_townsCoordinates.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
//                        int col = table_townsCoordinates.columnAtPoint(e.getPoint());
                                int row = table_townsCoordinates.rowAtPoint(e.getPoint());
                                //if(==3) {
                                double latitude   = Double.parseDouble(table_townsCoordinates.getValueAt(row,2).toString());
                                double longtitude = Double.parseDouble(table_townsCoordinates.getValueAt(row,3).toString());

                                System.out.println(latitude);
                                System.out.println(longtitude);


                                GeoPosition geo = new GeoPosition(latitude, longtitude);
                                Point2D p2d = mapw.convertGeoPositionToPoint(geo);
                                Double x = p2d.getX();
                                Double y = p2d.getY();
                                x_textfield.setText(x.toString());
                                x_textfield.setCaretPosition(0);
                                y_textfield.setText(y.toString());
                                y_textfield.setCaretPosition(0);

                                //}

                            }
                        });
                        scrollPane.setViewportView(table_townsCoordinates);

                    }
                });








                JPanel data_panel_koordinaty = new JPanel();
//                data_panel_koordinaty.setLayout(new GridLayout(20,1));
                data_panel_koordinaty.setLayout(null);
                data_panel_koordinaty.setBounds(0,0, 800,600);
                data_panel_koordinaty.add(poisk_label);
                data_panel_koordinaty.add(poisk_textfield);
                data_panel_koordinaty.add(poisk2_label);
                data_panel_koordinaty.add(poisk2_textfield);
                data_panel_koordinaty.add(x_label);
                data_panel_koordinaty.add(x_textfield);
                data_panel_koordinaty.add(y_label);
                data_panel_koordinaty.add(y_textfield);
                data_panel_koordinaty.add(scrollPane);
                data_panel_koordinaty.add(obnovit_button);
                data_panel_koordinaty.add(poisk_button);
                data_panel_koordinaty.add(back_koordinaty_button);
                data_panel_koordinaty.setPreferredSize(new Dimension(800,600));

                RaschetPryamougolnyhKoordinatWindow.add(data_panel_koordinaty);
                RaschetPryamougolnyhKoordinatWindow.setVisible(true);


            }
        });

        ustanovit_metki_po_koordinatan_ugly_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(set.size()!=0) {
                    String[] options = new String[2];
                    options[0] = "Да";
                    options[1] = "Нет";
                    int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                            "установить метки по координатам? Текущие метки будут удалены", "Подтверждение", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

                    if (Objects.equals(vybor, JOptionPane.NO_OPTION)) {
                      return;
                    }
                }

                if (x1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение x1 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (y1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение y1 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (x2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение x2 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (y2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение y2 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }


                Double x1;
                Double y1;
                Double x2;
                Double y2;
                try {
                    x1 = Double.parseDouble(x1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    y1 = Double.parseDouble(y1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    x2 = Double.parseDouble(x2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    y2 = Double.parseDouble(y2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;

                Point2D pt2D_1 = new Point2D.Double(x1, y1);
                GeoPosition gp_1 = mapw.convertPointToGeoPosition(pt2D_1);

                Waypoint wp_1 = new Waypoint() {
                    @Override
                    public GeoPosition getPosition() {
                        return gp_1;
                    }
                };

                Point2D pt2D_2 = new Point2D.Double(x2, y2);
                GeoPosition gp_2 = mapw.convertPointToGeoPosition(pt2D_2);

                Waypoint wp_2 = new Waypoint() {
                    @Override
                    public GeoPosition getPosition() {
                        return gp_2;
                    }
                };

                set.clear();
//                x1_textfield.setText("");
//                y1_textfield.setText("");
//                x2_textfield.setText("");
//                y2_textfield.setText("");
                mest_x_textfield.setText("");
                mest_y_textfield.setText("");
                WaypointPainter<Waypoint> wpp = new WaypointPainter<Waypoint>();
                set.add(wp_1);
                set.add(wp_2);
                wpp.setWaypoints(set);
                mapw.setOverlayPainter(wpp);
                mapw.revalidate();
                mapw.repaint();


            }});

        zapisat_rezultaty_v_bd_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (ugol1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение угла первой точки " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ugol2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение угла второй точки " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (x1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение x1 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (y1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение y1 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (x2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение x2 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (y2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение y2 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (mest_x_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение x " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (mest_y_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение y " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Double ugol1;
                Double ugol2;
                Double x1;
                Double y1;
                Double x2;
                Double y2;
                Double mest_x;
                Double mest_y;

                try {
                    ugol1 = Double.parseDouble(ugol1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение угла первой точки не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    ugol2 = Double.parseDouble(ugol2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение угла второй точки не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    x1 = Double.parseDouble(x1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    y1 = Double.parseDouble(y1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение y1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    x2 = Double.parseDouble(x2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x2 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    y2 = Double.parseDouble(y2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение y2 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    mest_x = Double.parseDouble(mest_x_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение x не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    mest_y = Double.parseDouble(mest_y_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение y не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;

                String nazvanie_proekta = JOptionPane.showInputDialog(null,"Введите наименование проекта",
                        "Наименование проекта",JOptionPane.INFORMATION_MESSAGE);

                if(Objects.equals(nazvanie_proekta,null)){
                    JOptionPane.showMessageDialog(null, "Не указано имя проекта!",
                            "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if(nazvanie_proekta.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Не указано имя проекта!",
                            "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                RestTemplate r = new RestTemplate();

                Long last_id;
                if(Objects.equals(r.getForObject("http://localhost:8080/get_results_last_id", Long.class),null))
                {
                    last_id = Long.parseLong("0");
                }else{
                    last_id = r.getForObject("http://localhost:8080/get_results_last_id", Long.class) + 1;
                }


                String[] parametry_set = new String[11];
                parametry_set[0]  = last_id.toString();
                parametry_set[1]  = data_of_user[0].replaceAll("\\s+","");
                parametry_set[2]  = nazvanie_proekta.replaceAll("\\s+","");
                parametry_set[3]  = ugol1.toString();
                parametry_set[4]  = ugol2.toString();
                parametry_set[5]  = x1.toString();
                parametry_set[6]  = y1.toString();
                parametry_set[7]  = x2.toString();
                parametry_set[8]  = y2.toString();
                parametry_set[9]  = mest_x.toString();
                parametry_set[10]  = mest_y.toString();


                int vybor = JOptionPane.showOptionDialog(null, "Записать результаты в базу данных?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)){
                    String result_post = r.postForObject("http://localhost:8080/set_results", parametry_set, String.class);
                    if(result_post.equals("ok")){
                        JOptionPane.showMessageDialog(null,"Данные записаны!","Инфо", JOptionPane.INFORMATION_MESSAGE);
                    }
                }


            }
        });


        opredelit_mestopolozhenie_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    if(set.size()==3){
                        String[] options = new String[2];
                        options[0]="Да";
                        options[1]="Нет";
                        int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                                "переопределить местоположение? Текущие результаты будут удалены", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                        if(Objects.equals(vybor, JOptionPane.NO_OPTION)) {
                            return;
                        }
                    }

                    if (ugol1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение угла первой точки " +
                                "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (ugol2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение угла второй точки " +
                                "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (x1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение x1 " +
                                "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (y1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение y1 " +
                                "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (x2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение x2 " +
                                "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (y2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение y2 " +
                                "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }


                    Double ugol1;
                    Double ugol2;
                    Double x1;
                    Double y1;
                    Double x2;
                    Double y2;

                    try {
                        ugol1 = Double.parseDouble(ugol1_textfield.getText());
                    } catch (Throwable excep) {
                        JOptionPane.showMessageDialog(null, "Значение угла первой точки не может " +
                                "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ;
                    try {
                        ugol2 = Double.parseDouble(ugol2_textfield.getText());
                    } catch (Throwable excep) {
                        JOptionPane.showMessageDialog(null, "Значение угла второй точки не может " +
                                "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ;
                    try {
                        x1 = Double.parseDouble(x1_textfield.getText());
                    } catch (Throwable excep) {
                        JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                                "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ;
                    try {
                        y1 = Double.parseDouble(y1_textfield.getText());
                    } catch (Throwable excep) {
                        JOptionPane.showMessageDialog(null, "Значение y1 не может " +
                                "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ;
                    try {
                        x2 = Double.parseDouble(x2_textfield.getText());
                    } catch (Throwable excep) {
                        JOptionPane.showMessageDialog(null, "Значение x2 не может " +
                                "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ;
                    try {
                        y2 = Double.parseDouble(y2_textfield.getText());
                    } catch (Throwable excep) {
                        JOptionPane.showMessageDialog(null, "Значение y2 не может " +
                                "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ;


                     x1 = Double.parseDouble(x1_textfield.getText());
                     y1 = Double.parseDouble(y1_textfield.getText());
                     x2 = Double.parseDouble(x2_textfield.getText());
                     y2 = Double.parseDouble(y2_textfield.getText());
                     ugol1 = Double.parseDouble(ugol1_textfield.getText());
                     ugol2 = Double.parseDouble(ugol2_textfield.getText());


                    Double tek_x = ( x1 * (1/Math.tan(ugol2)) + x2 * (1/Math.tan(ugol1)) - y1 + y2)/ ((1/Math.tan(ugol1)) + (1/Math.tan(ugol2)));
                    Double tek_y = ( y1 * (1/Math.tan(ugol2)) + y2 * (1/Math.tan(ugol1)) + x1 - x2)/ ((1/Math.tan(ugol1)) + (1/Math.tan(ugol2)));

                    if(Okruglenie_mestopolozheniya != 0){
                        BigDecimal tek_x_decimal = new BigDecimal(tek_x);
                        BigDecimal tek_y_decimal = new BigDecimal(tek_y);
                        tek_x_decimal = tek_x_decimal.setScale(Okruglenie_mestopolozheniya, RoundingMode.HALF_EVEN);
                        tek_y_decimal = tek_y_decimal.setScale(Okruglenie_mestopolozheniya, RoundingMode.HALF_EVEN);

                        mest_x_textfield.setText(tek_x_decimal.toString());
                        mest_x_textfield.setCaretPosition(0);
                        mest_y_textfield.setText(tek_y_decimal.toString());
                        mest_y_textfield.setCaretPosition(0);
                    }else{
                        mest_x_textfield.setText(tek_x.toString());
                        mest_x_textfield.setCaretPosition(0);
                        mest_y_textfield.setText(tek_y.toString());
                        mest_y_textfield.setCaretPosition(0);
                    }

                    Point2D pt2D = new Point2D.Double(tek_x, tek_y);
                    GeoPosition gp = mapw.convertPointToGeoPosition(pt2D);

                    Waypoint wp = new Waypoint() {
                        @Override
                        public GeoPosition getPosition() {
                            return gp;
                        }
                    };

                    Point2D pt2D_2 = new Point2D.Double(x1, y1);
                    GeoPosition gp_2 = mapw.convertPointToGeoPosition(pt2D_2);

                    Waypoint wp_2 = new Waypoint() {
                        @Override
                        public GeoPosition getPosition() {
                            return gp_2;
                        }
                    };

                    Point2D pt2D_3 = new Point2D.Double(x2, y2);
                    GeoPosition gp_3 = mapw.convertPointToGeoPosition(pt2D_3);

                    Waypoint wp_3 = new Waypoint() {
                        @Override
                        public GeoPosition getPosition() {
                            return gp_3;
                        }
                    };

                    WaypointPainter<Waypoint> wpp = new WaypointPainter<Waypoint>();
                    set.clear();
                    set.add(wp);
                    set.add(wp_2);
                    set.add(wp_3);
                    wpp.setWaypoints(set);
                    mapw.setOverlayPainter(wpp);
                    mapw.revalidate();
                    mapw.repaint();


            }
        });

        final Waypoint lastwp;

        mapw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GeoPosition gp = mapw.convertPointToGeoPosition(mapw.getMousePosition());

                Waypoint wp = new Waypoint() {
                    @Override
                    public GeoPosition getPosition() {
                        return gp;
                    }
                };

                if(set.size()<2) {
                    set.add(wp);
                }

                if(set.size()==1) {
                    x1_textfield.setText(Integer.toString(mapw.getMousePosition().x));
                    y1_textfield.setText(Integer.toString(mapw.getMousePosition().y));
                }

                if(set.size()==2) {
                    if(x2_textfield.getText().isEmpty() && y2_textfield.getText().isEmpty()){
                        x2_textfield.setText(Integer.toString(mapw.getMousePosition().x));
                        y2_textfield.setText(Integer.toString(mapw.getMousePosition().y));
                    }
                }

                WaypointPainter<Waypoint> wpp = new WaypointPainter<Waypoint>();

//                wpp.setRenderer(new WaypointRenderer<Waypoint>() {
//                    @Override
//                    public void paintWaypoint(Graphics2D graphics2D, JXMapViewer jxMapViewer, Waypoint waypoint) {
//                        graphics2D.setColor(Color.RED);
//                        graphics2D.drawLine(-5,-5,+5,+5);
//                        graphics2D.drawLine(-5,+5,+5,-5);
//
//                        jxMapViewer.setOverlayPainter(wpp);
//                        jxMapViewer.revalidate();
//                        jxMapViewer.repaint();
//
//                    }
//                });

                System.out.println("Произошел клик по карте: "+mapw.getMousePosition());
                wpp.setWaypoints(set);
                mapw.setOverlayPainter(wpp);
                mapw.revalidate();
                mapw.repaint();
            }
        });



    }

    public static void nastroyki_button_actionPerformed(ActionEvent e){

        NastroykiWindow = getFrame();
        NastroykiWindow.setSize(new Dimension(300,400));
        NastroykiWindow.setLayout(null);
        NastroykiWindow.setTitle("Настройки");
        NastroykiWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    NastroykiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    NastroykiWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


        JLabel label_types = new JLabel("Тип карты:");
        label_types.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_types.setFont(new Font("Serif",Font.BOLD, 18));
        label_types.setBounds(10,10,130,30);
        types_of_map_combobox.setFont(new Font("Serif",Font.BOLD, 18));
        types_of_map_combobox.setBounds(10,40,130,50);


        JLabel label_okruglenie = new JLabel("<html>Округление координат<br>местоположения:</html>");
        label_okruglenie.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_okruglenie.setFont(new Font("Serif",Font.BOLD, 18));
        label_okruglenie.setBounds(10,120,400,60);

        String[] count_of_digits_okruglenie ={
                "Не округлять",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16"
        };

        JComboBox okruglenie_combobox = new JComboBox(count_of_digits_okruglenie);
        okruglenie_combobox.setBounds(10,175,130,50);

        JButton back_button_nastroiky = new JButton("Назад");
        back_button_nastroiky.setBounds(190, 300, 80,30);
        back_button_nastroiky.setAlignmentX(MainWindow.getAlignmentX());
        back_button_nastroiky.setPreferredSize(new Dimension(25,35));

        back_button_nastroiky.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back_button_nastroiky_actionPerformed(e);
            }
        });

        JButton zapisat_nastroiky = new JButton("Записать");
        zapisat_nastroiky.setBounds(35, 300, 110,30);
        zapisat_nastroiky.setAlignmentX(MainWindow.getAlignmentX());
        zapisat_nastroiky.setPreferredSize(new Dimension(25,35));

        zapisat_nastroiky.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RestTemplate r = new RestTemplate();
                String[] parametry_update = new String[4];
                Long last_id = r.getForObject("http://localhost:8080/get_nastroyki_last_id", Long.class) + 1;
                Integer tip_karty = types_of_map_combobox.getSelectedIndex();
                Integer okruglenie = okruglenie_combobox.getSelectedIndex();
                parametry_update[0] = last_id.toString();
                parametry_update[1] = data_of_user[0];
                parametry_update[2] = tip_karty.toString();
                parametry_update[3] = okruglenie.toString();

                String suchestvuut_nastroiki = r.getForObject("http://localhost:8080/nastroyki_by_id?id="+ data_of_user[0],
                        String.class);

                if(Objects.equals(suchestvuut_nastroiki, null)) {
                    String result_post = r.postForObject("http://localhost:8080/set_nastroyki", parametry_update, String.class);
                    if (result_post.equals("ok")) {
                        JOptionPane.showMessageDialog(null, "Данные пользователя изменены!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    String result_post = r.postForObject("http://localhost:8080/update_nastroyki", parametry_update, String.class);
                    if (result_post.equals("ok")) {
                        JOptionPane.showMessageDialog(null, "Данные пользователя изменены!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        RestTemplate r = new RestTemplate();
        String nastroyki_polzovatelya = r.getForObject("http://localhost:8080/nastroyki_by_id?id="+ data_of_user[0],
                String.class);

        if(!Objects.equals(nastroyki_polzovatelya, null)) {
            nastroyki_polzovatelya = nastroyki_polzovatelya.replaceAll("\\s+","");
            String[] nastroyki_polzovatelya_mas = nastroyki_polzovatelya.split(",");
            types_of_map_combobox.setSelectedIndex(Integer.parseInt(nastroyki_polzovatelya_mas[0]));
            okruglenie_combobox.setSelectedIndex(Integer.parseInt(nastroyki_polzovatelya_mas[1]));
        }

//        JPanel nastroyki_panel = new JPanel();
//        nastroyki_panel.setLayout(new GridLayout(20,1));
        NastroykiWindow.add(label_types);
        NastroykiWindow.add(types_of_map_combobox);
//        nastroyki_panel.add(Box.createVerticalStrut(2));
//        nastroyki_panel.add(Box.createVerticalStrut(2));
//        nastroyki_panel.add(Box.createVerticalStrut(2));
//        nastroyki_panel.add(Box.createVerticalStrut(2));
        NastroykiWindow.add(back_button_nastroiky);

        NastroykiWindow.add(label_okruglenie);
        NastroykiWindow.add(okruglenie_combobox);

        NastroykiWindow.add(zapisat_nastroiky);

        MainWindow.setVisible(false);
        //NastroykiWindow.add(nastroyki_panel);
        NastroykiWindow.setVisible(true);
    }

    public static void back_button_nastroiky_actionPerformed(ActionEvent e){
        NastroykiWindow.dispose();
        MainWindow.setVisible(true);
    }
    public static void back_button_map_actionPerformed(ActionEvent e){
        MapWindow.dispose();
        MainWindow.setVisible(true);
    }

    public static void delete_marks_button_map_actionPerformed(ActionEvent e, JXMapViewer mapw){
        WaypointPainter wpp = new WaypointPainter();
        mapw.setOverlayPainter(wpp);
        mapw.revalidate();
        mapw.repaint();
    }

    public static void spisok_resultatov_button_actionPerformed(ActionEvent e){

        SpisokResultatovWindow.setSize(1000,600);
        SpisokResultatovWindow.setTitle("Список результатов вычислений");

        JButton back_koordinaty_button = new JButton("Назад");
        back_koordinaty_button.setBounds(430, 520, 130,30);
        back_koordinaty_button.setAlignmentX(MainWindow.getAlignmentX());
        back_koordinaty_button.setPreferredSize(new Dimension(130,35));
        back_koordinaty_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SpisokResultatovWindow.dispose();
                MainWindow.setVisible(true);
            }
        });

        RestTemplate r = new RestTemplate();
        String result_data = r.getForObject("http://localhost:8080/get_data_results", String.class);
        System.out.println(result_data);

        List<String> result_data_list = Arrays.asList(result_data.split("\",\""));
        result_data_list.removeAll(Arrays.asList("",null));


        String[] result_data_mas = result_data_list.toArray(new String[result_data_list.size()]);
        System.out.println(result_data_mas);

        String[] columnsNames = {
                "Идентификатор",
                "Пользователь",
                "Название проекта",
                "Угол 1",
                "Угол 2",
                "x1",
                "y1",
                "x2",
                "y2",
                "Мест. x",
                "Мест. y"
        };

        String[][] data_for_table = new String[result_data_mas.length][11];

        for(int i=0;i<result_data_mas.length;i++){

            var m = result_data_mas[i].replace("\"", "")
                    .replace("[", "")
                    .replace("]", "")
                    .split(",");
            for(int j=0;j<11;j++) {
                data_for_table[i][j] = m[j];
                System.out.println(data_for_table[i][j]);
            }


        }


        table_results = new JTable(data_for_table,columnsNames);
        table_results.setBounds(0,0, 980,400);
        table_results.setDefaultEditor(Object.class, null);
        table_results.setAutoCreateRowSorter(true);

        table_results.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    int row = table_results.rowAtPoint(e.getPoint());
                    String[] data_param = new String[10];

                    data_param[0]    = table_results.getValueAt(row,0).toString();
                    data_param[1]    = table_results.getValueAt(row,2).toString();
                    data_param[2]    = table_results.getValueAt(row,3).toString();
                    data_param[3]    = table_results.getValueAt(row,4).toString();
                    data_param[4]    = table_results.getValueAt(row,5).toString();
                    data_param[5]    = table_results.getValueAt(row,6).toString();
                    data_param[6]    = table_results.getValueAt(row,7).toString();
                    data_param[7]    = table_results.getValueAt(row,8).toString();
                    data_param[8]    = table_results.getValueAt(row,9).toString();
                    data_param[9]    = table_results.getValueAt(row,10).toString();


                    RedactirovanieResultata_mouseClicked_ActionPerformed(e,data_param);

                }
            }
        });

        //table_townsCoordinates.setRowSorter(sorter);

        JLabel poisk_label = new JLabel("Поиск 1:");
        poisk_label.setBounds(10, -2, 50,30);
        JTextField poisk_textfield = new JTextField(100);
        poisk_textfield.setBounds(10, 20, 100,30);

        JLabel poisk2_label = new JLabel("Поиск 2:");
        poisk2_label.setBounds(140, -2, 50,30);
        JTextField poisk2_textfield = new JTextField(100);
        poisk2_textfield.setBounds(140, 20, 100,30);

        JButton poisk_button = new JButton("Искать");
        poisk_button.setBounds(430, 490, 130,30);
        poisk_button.setAlignmentX(MainWindow.getAlignmentX());
        poisk_button.setPreferredSize(new Dimension(130,35));

        JButton obnovit_button = new JButton("Обновить");
        obnovit_button.setBounds(430, 460, 130,30);
        obnovit_button.setAlignmentX(MainWindow.getAlignmentX());
        obnovit_button.setPreferredSize(new Dimension(130,35));

        JScrollPane scrollPane = new JScrollPane(table_results);
        scrollPane.setBounds(10,50, 980,400);
        scrollPane.setViewportView(table_results);

        poisk_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_results.getModel());
                table_results.setRowSorter(sorter);
                try {
                    if(!poisk_textfield.getText().isEmpty() && poisk2_textfield.getText().isEmpty() ) {
                        sorter.setRowFilter(RowFilter.regexFilter(poisk_textfield.getText()));
                    } else if(!poisk2_textfield.getText().isEmpty() && poisk_textfield.getText().isEmpty()){
                        sorter.setRowFilter(RowFilter.regexFilter(poisk2_textfield.getText()));
                    }else if(!poisk2_textfield.getText().isEmpty() && !poisk_textfield.getText().isEmpty()){
                        RowFilter rv1 = RowFilter.regexFilter(poisk_textfield.getText(),0);
                        RowFilter rv2 = RowFilter.regexFilter(poisk2_textfield.getText(),1);
                        System.out.println(poisk_textfield.getText());
                        System.out.println(poisk2_textfield.getText());
                        List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
                        filters.add(RowFilter.regexFilter(poisk2_textfield.getText(),0,1,2,3,4,5,6,7,8,9,10));
                        filters.add(RowFilter.regexFilter(poisk2_textfield.getText(),0,1,2,3,4,5,6,7,8,9,10));
                        RowFilter rv3 = RowFilter.andFilter(filters);
                        sorter.setRowFilter(rv3);
                    } else{
                        sorter.setRowFilter(null);
                    }
                }catch(Throwable exp){}
            }
        });

        obnovit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result_data = r.getForObject("http://localhost:8080/get_data_results", String.class);

                List<String> result_data_list = Arrays.asList(result_data.split("\",\""));
                result_data_list.removeAll(Arrays.asList("",null));

                String[] result_data_mas = result_data_list.toArray(new String[result_data_list.size()]);

                String[] columnsNames = {
                        "Идентификатор",
                        "Пользователь",
                        "Название проекта",
                        "Угол 1",
                        "Угол 2",
                        "x1",
                        "y1",
                        "x2",
                        "y2",
                        "Мест. x",
                        "Мест. y"
                };

                String[][] data_for_table = new String[result_data_mas.length][11];

                for(int i=0;i<result_data_mas.length;i++){

                    var m = result_data_mas[i].replace("\"", "")
                            .replace("[", "")
                            .replace("]", "")
                            .split(",");
                    for(int j=0;j<11;j++) {
                        data_for_table[i][j] = m[j];
                    }


                }

                table_results = new JTable(data_for_table,columnsNames);
                table_results.setDefaultEditor(Object.class, null);
                table_results.setAutoCreateRowSorter(true);
                scrollPane.setViewportView(table_results);

                table_results.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(e.getClickCount()==2){

                            int row = table_results.rowAtPoint(e.getPoint());
                            String[] data_param = new String[10];

                            data_param[0]    = table_results.getValueAt(row,0).toString();
                            data_param[1]    = table_results.getValueAt(row,2).toString();
                            data_param[2]    = table_results.getValueAt(row,3).toString();
                            data_param[3]    = table_results.getValueAt(row,4).toString();
                            data_param[4]    = table_results.getValueAt(row,5).toString();
                            data_param[5]    = table_results.getValueAt(row,6).toString();
                            data_param[6]    = table_results.getValueAt(row,7).toString();
                            data_param[7]    = table_results.getValueAt(row,8).toString();
                            data_param[8]    = table_results.getValueAt(row,9).toString();
                            data_param[9]    = table_results.getValueAt(row,10).toString();

                            RedactirovanieResultata_mouseClicked_ActionPerformed(e, data_param);

                        }
                    }
                });

            }
        });


        JPanel data_panel_results = new JPanel();
        data_panel_results.setLayout(null);
        data_panel_results.setBounds(0,0, 1000,600);
        data_panel_results.add(poisk_label);
        data_panel_results.add(poisk_textfield);
        data_panel_results.add(poisk2_label);
        data_panel_results.add(poisk2_textfield);
        data_panel_results.add(scrollPane);
        data_panel_results.add(obnovit_button);
        data_panel_results.add(poisk_button);
        data_panel_results.add(back_koordinaty_button);
        data_panel_results.setPreferredSize(new Dimension(1000,600));

        SpisokResultatovWindow.add(data_panel_results);
        MainWindow.setVisible(false);
        SpisokResultatovWindow.setVisible(true);

    }

    public static void RedactirovanieResultata_mouseClicked_ActionPerformed(MouseEvent e, String[] data_param){
        RedaktirovanieRezultataWindow = getFrame();
        RedaktirovanieRezultataWindow.setTitle("Редактирование результатов вычислений");
        RedaktirovanieRezultataWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Вы действительно хотите " +
                        "закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    RedaktirovanieRezultataWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    RedaktirovanieRezultataWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        RedaktirovanieRezultataWindow.setTitle("Редактирование результата");
        RedaktirovanieRezultataWindow.setSize(new Dimension(435,600));
        RedaktirovanieRezultataWindow.setLayout(null);
        JButton back_redactirovanie_button = new JButton("Назад");
        back_redactirovanie_button.setBounds(290, 520, 90,30);
        back_redactirovanie_button.setAlignmentX(MainWindow.getAlignmentX());
        back_redactirovanie_button.setPreferredSize(new Dimension(130,35));
        back_redactirovanie_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedaktirovanieRezultataWindow.dispose();
                SpisokResultatovWindow.setVisible(true);
            }
        });

        JButton izmenenie_redactirovanie_button = new JButton("Изменить");
        izmenenie_redactirovanie_button.setBounds(50, 520, 100,30);
        izmenenie_redactirovanie_button.setAlignmentX(MainWindow.getAlignmentX());
        izmenenie_redactirovanie_button.setPreferredSize(new Dimension(130,35));

        JButton udalenie_redactirovanie_button = new JButton("Удалить");
        udalenie_redactirovanie_button.setBounds(170, 520, 100,30);
        udalenie_redactirovanie_button.setAlignmentX(MainWindow.getAlignmentX());
        udalenie_redactirovanie_button.setPreferredSize(new Dimension(130,35));


        JLabel ID_resultata_label = new JLabel("Идентификатор:");
        ID_resultata_label.setBounds(10, -2, 50,30);
        JTextField ID_resultata_textfield = new JTextField(100);
        ID_resultata_textfield.setBounds(10, 20, 100,30);
        ID_resultata_textfield.setText(data_param[0]);
        ID_resultata_textfield.setEditable(false);


        JLabel nazvanie_label = new JLabel("Наименование проекта:");
        nazvanie_label.setBounds(10, 50, 50,30);
        JTextField nazvanie_textfield = new JTextField(100);
        nazvanie_textfield.setBounds(10, 72, 100,30);
        nazvanie_textfield.setText(data_param[1]);

        JLabel ugol1_label = new JLabel("Угол 1:");
        ugol1_label.setBounds(10, 50, 50,30);
        JTextField ugol1_textfield = new JTextField(100);
        ugol1_textfield.setBounds(10, 72, 100,30);
        ugol1_textfield.setText(data_param[2]);

        JLabel ugol2_label = new JLabel("Угол 2:");
        ugol2_label.setBounds(10, 50, 50,30);
        JTextField ugol2_textfield = new JTextField(100);
        ugol2_textfield.setBounds(10, 72, 100,30);
        ugol2_textfield.setText(data_param[3]);

        JLabel x1_label = new JLabel("x1:");
        x1_label.setBounds(10, 50, 50,30);
        JTextField x1_textfield = new JTextField(100);
        x1_textfield.setBounds(10, 72, 100,30);
        x1_textfield.setText(data_param[4]);

        JLabel y1_label = new JLabel("y1:");
        y1_label.setBounds(10, 50, 50,30);
        JTextField y1_textfield = new JTextField(100);
        y1_textfield.setBounds(10, 72, 100,30);
        y1_textfield.setText(data_param[5]);

        JLabel x2_label = new JLabel("x2:");
        x2_label.setBounds(10, 50, 50,30);
        JTextField x2_textfield = new JTextField(100);
        x2_textfield.setBounds(10, 72, 100,30);
        x2_textfield.setText(data_param[6]);

        JLabel y2_label = new JLabel("y2:");
        y2_label.setBounds(10, 50, 50,30);
        JTextField y2_textfield = new JTextField(100);
        y2_textfield.setBounds(10, 72, 100,30);
        y2_textfield.setText(data_param[7]);

        JLabel mest_x_label = new JLabel("Местоположение x:");
        mest_x_label.setBounds(10, 50, 50,30);
        JTextField mest_x_textfield = new JTextField(100);
        mest_x_textfield.setBounds(10, 72, 100,30);
        mest_x_textfield.setText(data_param[8]);

        JLabel mest_y_label = new JLabel("Местоположение y:");
        mest_y_label.setBounds(10, 50, 50,30);
        JTextField mest_y_textfield = new JTextField(100);
        mest_y_textfield.setBounds(10, 72, 100,30);
        mest_y_textfield.setText(data_param[9]);

        JPanel data_panel_results = new JPanel();
        data_panel_results.setLayout(new GridLayout(20,1));
        data_panel_results.setBounds(40,0, 350,500);
        data_panel_results.add(ID_resultata_label);
        data_panel_results.add(ID_resultata_textfield);
        data_panel_results.add(nazvanie_label);
        data_panel_results.add(nazvanie_textfield);
        data_panel_results.add(ugol1_label);
        data_panel_results.add(ugol1_textfield);
        data_panel_results.add(ugol2_label);
        data_panel_results.add(ugol2_textfield);
        data_panel_results.add(x1_label);
        data_panel_results.add(x1_textfield);
        data_panel_results.add(y1_label);
        data_panel_results.add(y1_textfield);
        data_panel_results.add(x2_label);
        data_panel_results.add(x2_textfield);
        data_panel_results.add(y2_label);
        data_panel_results.add(y2_textfield);
        data_panel_results.add(mest_x_label);
        data_panel_results.add(mest_x_textfield);
        data_panel_results.add(mest_y_label);
        data_panel_results.add(mest_y_textfield);


        izmenenie_redactirovanie_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(nazvanie_textfield.getText().replaceAll("\\s+", "").isEmpty()){
                    JOptionPane.showMessageDialog(null, "Не указано имя проекта!",
                            "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ugol1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение угла первой точки " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ugol2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение угла второй точки " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (x1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение x1 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (y1_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение y1 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (x2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение x2 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (y2_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение y2 " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (mest_x_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение x " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (mest_y_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение y " +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Double ugol1;
                Double ugol2;
                Double x1;
                Double y1;
                Double x2;
                Double y2;
                Double mest_x;
                Double mest_y;

                try {
                    ugol1 = Double.parseDouble(ugol1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение угла первой точки не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    ugol2 = Double.parseDouble(ugol2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение угла второй точки не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    x1 = Double.parseDouble(x1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    y1 = Double.parseDouble(y1_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение y1 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    x2 = Double.parseDouble(x2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение x2 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    y2 = Double.parseDouble(y2_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение y2 не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    mest_x = Double.parseDouble(mest_x_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение x не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;
                try {
                    mest_y = Double.parseDouble(mest_y_textfield.getText());
                } catch (Throwable excep) {
                    JOptionPane.showMessageDialog(null, "Значение местоположение y не может " +
                            "быть преобразовано в вещественное число!", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ;

                String[] data_param_zapros = new String[10];

                data_param_zapros[0]    = ID_resultata_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[1]    = nazvanie_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[2]    = ugol1_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[3]    = ugol2_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[4]    = x1_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[5]    = y1_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[6]    = x2_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[7]    = y2_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[8]    = mest_x_textfield.getText().replaceAll("\\s+", "");
                data_param_zapros[9]    = mest_y_textfield.getText().replaceAll("\\s+", "");


                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Данные будут изменены! Продолжить?"
                        , "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,
                        options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    RestTemplate r = new RestTemplate();
                    String result_post = r.postForObject("http://localhost:8080/update_result", data_param_zapros, String.class);
                    if(result_post.equals("ok")){
                        JOptionPane.showMessageDialog(null,"Данные изменены!","Инфо", JOptionPane.INFORMATION_MESSAGE);
                    }
                }



            }
        });

        udalenie_redactirovanie_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (ID_resultata_textfield.getText().replaceAll("\\s+", "").isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Значение ID" +
                            "не заполнено", "Инфо", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[] options = new String[2];
                options[0]="Да";
                options[1]="Нет";
                int vybor = JOptionPane.showOptionDialog(null, "Данные с идентификатором "+
                                ID_resultata_textfield.getText().replaceAll("\\s+", "")+" будут удалены! Продолжить?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,
                        options,null);

                if(Objects.equals(vybor, JOptionPane.YES_OPTION)) {
                    RestTemplate r = new RestTemplate();
                    String result_post = r.postForObject("http://localhost:8080/delete_result", ID_resultata_textfield.getText().replaceAll("\\s+", ""), String.class);
                    if(result_post.equals("ok")){
                        RedaktirovanieRezultataWindow.dispose();
                        SpisokResultatovWindow.setVisible(true);
                        JOptionPane.showMessageDialog(null,"Данные удалены!","Инфо", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });





        RedaktirovanieRezultataWindow.add(data_panel_results);
        RedaktirovanieRezultataWindow.add(back_redactirovanie_button);
        RedaktirovanieRezultataWindow.add(izmenenie_redactirovanie_button);
        RedaktirovanieRezultataWindow.add(udalenie_redactirovanie_button);

        SpisokResultatovWindow.setVisible(false);
        RedaktirovanieRezultataWindow.setVisible(true);

    }
}
