package app.search;

import app.card.model.Records;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cardssearch")
public class SearchCards extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectcategory = req.getParameter("selectcategory");
        String parameter = req.getParameter("parameter");

        if (selectcategory == null){
            System.out.println("Параметр не передан!");
        } else System.out.println("Параметр передан!");

        ResultSet resultSet = null;
        List<Records> cardlist = new ArrayList<>();
        int k=0;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();


            //Перечень условий по выбранной категории поиска
            if (parameter != null && selectcategory.equals("id")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.idC LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("status")) {
                if (parameter.equals("не актуально")) {
                    resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.status LIKE '%" + parameter + "%'");
                } else {
                    resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.status LIKE 'акт______'");
                }
            } else if (parameter != null && selectcategory.equals("revision")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.revision LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("title")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.title LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("titleMore")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.title_more LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("process")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.process LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("type_of_activity")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.type_of_activity LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("record_type")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.record_type LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("date_of_creation")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.date_of_creation LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("direction_of_activity")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.direction_of_activity LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("equipment")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.equipment LIKE '%" + parameter + "%'");
            } else if (parameter != null && selectcategory.equals("responsible")) {
                resultSet = statement.executeQuery("SELECT * FROM cards WHERE cards.responsible LIKE '%" + parameter + "%'");
            }
            if (resultSet != null) {

                while (resultSet.next()) {
                    String ID = resultSet.getString(2);
                    String title = resultSet.getString(5);
                    String titleMore = resultSet.getString(6);
                    String revision = resultSet.getString(4);
                    String data = resultSet.getString(10);
                    cardlist.add(new Records(ID, title, titleMore, revision, data));
                    k++;
                }
            } else System.out.println("Ничего не найдено!");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("counter", "Найдено статей: " + k);
        req.setAttribute("cardlist", cardlist);
        req.getRequestDispatcher("/views/CardsSearchList.jsp").forward(req,resp);
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/CardsSearch.jsp").forward(req, resp);
    }
}