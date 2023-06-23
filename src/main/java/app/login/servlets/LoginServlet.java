package app.login.servlets;

import app.users.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

import static app.login.servlets.MenuUserServlet.getNumbercards;
import static app.login.servlets.MenuUserServlet.k;

@WebServlet("/index")
public class LoginServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    private static final String SELECT = "SELECT * FROM mydbcard.users WHERE login = ?;";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        User user = new User();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(resultSet.getString("role"));
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        req.getSession().setAttribute("user", user.getName());
        if(login.equals(user.getLogin()) && password.equals(user.getPassword()) && "admin".equals(String.valueOf(user.getRole()))){
            req.getRequestDispatcher("/views/Admin/menuAdmin.jsp").forward(req,resp);
        } else if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
            getNumbercards();
            req.setAttribute("numbercards", k);
            req.getRequestDispatcher("/views/menuUser.jsp").forward(req,resp);
        } else {
            req.setAttribute("errorMessage", "Логин и пароль не корректны");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
