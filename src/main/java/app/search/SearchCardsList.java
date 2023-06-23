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

@WebServlet("/cardssearchlist")
public class SearchCardsList extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    private static final String SELECT = "SELECT * FROM cards WHERE cards.idC = ?;";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        String ID = req.getParameter("idC");
        ArrayList<String> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet !=null){
                while (resultSet.next()){
                    int i = 1;
                    while(i <= 14) {
                        list.add(resultSet.getString(i++));
                    }
                }
            } else System.out.println("resultSet пустой");

            connection.close();
            if (connection.isClosed()) {
                System.out.println("Связь разорвана");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        req.getSession().setAttribute("cardout", list);*/ //Вроде как тупо забыл удалить
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultSet resultSet = null;
        List<Records> listAll = new ArrayList<>();
        String ind = req.getParameter("ind");
        int k = 0;
        if (ind != null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM mydbcard.cards");

                if (resultSet !=null){
                    while (resultSet.next()){
                        k++;
                        String ID = resultSet.getString(2);
                        String title = resultSet.getString(5);
                        String titleMore = resultSet.getString(6);
                        String revision = resultSet.getString(4);
                        String data = resultSet.getString(10);
                        listAll.add(new Records(ID, title, titleMore, revision, data));
                    }
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("counter", "Найдено статей: " + k);
            req.setAttribute("cardlist", listAll);
        } else {

        }
        getServletContext().getRequestDispatcher("/views/CardsSearchList.jsp").forward(req, resp);
    }
}
