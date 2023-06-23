package app.login.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/menuUser")
public class MenuUserServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    public static int k;
        public static void getNumbercards(){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM cards");
                if (resultSet != null){
                    k=0;
                    while (resultSet.next()){
                        k++;
                    }
                } else System.out.println("ничего не найдено");

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) {
            String newUrl = "/index";
            String redirectUrl = resp.encodeRedirectURL(newUrl);
            resp.sendRedirect(redirectUrl);
        } else {
            getNumbercards();
            req.setAttribute("numbercards", k);
            getServletContext().getRequestDispatcher("/views/menuUser.jsp").forward(req, resp);
        }
    }
}
