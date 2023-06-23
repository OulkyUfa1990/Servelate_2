package app.card.model;

import app.card.entities.ListRevision;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinkListRevision {

private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;

    public static ArrayList<ListRevision> ListRevision (String ID){

        String[] words_1 = ID.split("\\.");
        String k = words_1[0];
        String SELECT = "SELECT * FROM cards WHERE cards.idC LIKE '%" + k + "%';";
        List<String> listID = new ArrayList<>(); //Для генерации ссылок в таблице ссылка на ревизии
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null){
                while (resultSet.next()){
                    int i = 2;
                    listID.add(resultSet.getString(i));
                }
            } else System.out.println("resultSet пустой");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<ListRevision> listRev = new ArrayList<>(); //Список для передачи на страницу ревизий. Список прошлых версий

        for (String i : listID){
            String[] words_2 = i.split("\\.");
            listRev.add(new ListRevision(i, words_2[1]));
        }
        return listRev;
    }
}
