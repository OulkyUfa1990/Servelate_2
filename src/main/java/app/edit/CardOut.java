package app.edit;

import app.card.entities.ListRevision;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/cardout")
public class CardOut extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    private static final String SELECT = "SELECT * FROM cards WHERE cards.idC = ?;";
    ArrayList<String> cardout = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idADDCard = req.getParameter("IDnewCard");
        if (idADDCard != null){
            //Редирект после добавление картчоки
            System.out.println("ID с редиректа после внесения карточки: " + idADDCard);
            cardout.clear();

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                preparedStatement = connection.prepareStatement(SELECT);
                preparedStatement.setString(1, idADDCard);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet != null) {
                    while (resultSet.next()) {
                        int i = 1;
                        while (i <= 28) {
                            cardout.add(resultSet.getString(i++));
                        }
                    }
                } else System.out.println("resultSet пустой");

                connection.close();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ArrayList<ListRevision> LinkListRevision = app.card.model.LinkListRevision.ListRevision (idADDCard); //Список прошлых ID и ревизий для списка ревизий
            req.getSession().setAttribute("LinkListRevision", LinkListRevision);
            req.setAttribute("createObj", "Запись добавлена. Еще раз ознакомтесь с содержанием. При необходимости внестите корректировки выбрав соотвествующую команду в нижнем меню.");

        } else {
            //Вывод карточки из поиска
            String idC = req.getParameter("id");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            cardout.clear();
            System.out.println("Очистка cardout завершена 2");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                preparedStatement = connection.prepareStatement(SELECT);
                preparedStatement.setString(1, idC);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet != null) {
                    while (resultSet.next()) {
                        int i = 1;
                        while (i <= 28) {
                            cardout.add(resultSet.getString(i++));
                        }
                    }
                } else System.out.println("resultSet пустой");

                connection.close();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ArrayList<ListRevision> LinkListRevision = app.card.model.LinkListRevision.ListRevision (idC); //Список прошлых ID и ревизий для списка ревизий
            req.getSession().setAttribute("LinkListRevision", LinkListRevision);
        }
        req.getSession().setAttribute("cardout", cardout);
        getServletContext().getRequestDispatcher("/views/EditViewsCard/CardOut.jsp").forward(req, resp);
    }
}
