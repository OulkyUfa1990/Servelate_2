package app.users.servlets;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import app.users.entities.User;
import app.users.model.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    public static void getUserSql() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Связь установлена");
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            Model model = Model.getInstance(); //Подключение к Model для добавления пользователей из БД в лист
            model.clear();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String password = resultSet.getString(3);
                String login = resultSet.getString(4);
                String role = resultSet.getString(5);
                User user = new User(name, password, login, role);
                model.add(user);
            }

            connection.close();
            if (connection.isClosed()) {
                System.out.println("Связь разорвана");
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model modePrintUser = Model.getInstance();
        modePrintUser.clear();
        getUserSql();
        List<String> names = modePrintUser.list();
        req.setAttribute("userNames", names);

        getServletContext().getRequestDispatcher("/views/Admin/listUsers.jsp").forward(req, resp);
    }
}