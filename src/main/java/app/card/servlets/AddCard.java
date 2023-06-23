package app.card.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.card.model.CreateID.*;

@WebServlet("/AddCard")
@MultipartConfig
public class AddCard extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    private static final String SELECT = "SELECT * FROM cards;";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String ID;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        Подключение к БД для генерации ID
        ArrayList<String> IDViews = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    int i = 2;
                    IDViews.add(resultSet.getString(i));
                }
            } else System.out.println("resultSet пустой");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Возврат параметра имя пользователя сесии
        String UserName = req.getParameter("nameUser");

        ID = id_first(id_sort(IDViews)); //Методы генерации ID для новых записец и ID для выпуска ревизий
        req.setAttribute("ID", ID);
        req.setAttribute("UserName", UserName);
        getServletContext().getRequestDispatcher("/views/addCard.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String status = req.getParameter("status");
        String revision = req.getParameter("revision");
        String title = req.getParameter("title");
        String titleMore = req.getParameter("titleMore");
        String process = req.getParameter("process");
        String type_of_activity = req.getParameter("type_of_activity");
        String record_type = req.getParameter("record_type");
        String date_of_creation = req.getParameter("date_of_creation");
        String direction_of_activity = req.getParameter("direction_of_activity");
        String equipment = req.getParameter("equipment");
        String responsible = req.getParameter("responsible");

//        Обработка файлов
        InputStream fileContent = null;
        String NameContent = null;
        List<InputStream> listFileContent = new ArrayList<>();  /*Лсит файлов*/
        List<String> listNameContent = new ArrayList<>();       /*Лист имен файлов*/
        List<String> listTypeContent = new ArrayList<>();       /*Лист типов файлов*/
        for (Part part : req.getParts()) {
            if (part != null && part.getSize() > 0 && part.getSubmittedFileName() != null) {
                NameContent = part.getSubmittedFileName();
                listNameContent.add(NameContent);
                String contentType = part.getContentType();
                listTypeContent.add(contentType);
                System.out.println(contentType);
                fileContent = part.getInputStream();
                listFileContent.add(fileContent);
                fileContent = null;
            }
        }

//Подключение и запись карточки в БД
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "INSERT INTO cards (idC, status, revision, title, title_more, process, type_of_activity," +
                    "record_type, date_of_creation, direction_of_activity, equipment," +
                    "responsible, content_1, content_1_name, content_1_type, content_2, content_2_name, content_2_type, content_3, content_3_name, content_3_type, content_4, content_4_name, content_4_type, content_5, content_5_name, content_5_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, status);
            preparedStatement.setString(3, revision);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, titleMore);
            preparedStatement.setString(6, process);
            preparedStatement.setString(7, type_of_activity);
            preparedStatement.setString(8, record_type);
            preparedStatement.setString(9, date_of_creation);
            preparedStatement.setString(10, direction_of_activity);
            preparedStatement.setString(11, equipment);
            preparedStatement.setString(12, responsible);
            if (listFileContent.size() != 0){
                if (listFileContent.size() > 0) {
                    preparedStatement.setBinaryStream(13, listFileContent.get(0));
                    preparedStatement.setString(14, listNameContent.get(0));
                    preparedStatement.setString(15, listTypeContent.get((0)));
                } else {
                    preparedStatement.setString(13, null);
                    preparedStatement.setString(14, null);
                    preparedStatement.setString(15, null);
                }
                if (listFileContent.size() > 1) {
                    preparedStatement.setBinaryStream(16, listFileContent.get(1));
                    preparedStatement.setString(17, listNameContent.get(1));
                    preparedStatement.setString(18, listTypeContent.get((1)));
                } else{
                    preparedStatement.setString(16, null);
                    preparedStatement.setString(17, null);
                    preparedStatement.setString(18, null);
                }
                if (listFileContent.size() > 2) {
                    preparedStatement.setBinaryStream(19, listFileContent.get(2));
                    preparedStatement.setString(20, listNameContent.get(2));
                    preparedStatement.setString(21, listTypeContent.get((2)));
                } else{
                    preparedStatement.setString(19, null);
                    preparedStatement.setString(20, null);
                    preparedStatement.setString(21, null);
                }
                if (listFileContent.size() > 3) {
                    preparedStatement.setBinaryStream(22, listFileContent.get(3));
                    preparedStatement.setString(23, listNameContent.get(3));
                    preparedStatement.setString(24, listTypeContent.get((3)));
                } else{
                    preparedStatement.setString(22, null);
                    preparedStatement.setString(23, null);
                    preparedStatement.setString(24, null);
                }
                if (listFileContent.size() > 4) {
                    preparedStatement.setBinaryStream(25, listFileContent.get(4));
                    preparedStatement.setString(26, listNameContent.get(4));
                    preparedStatement.setString(27, listTypeContent.get((4)));
                } else{
                    preparedStatement.setString(25, null);
                    preparedStatement.setString(26, null);
                    preparedStatement.setString(27, null);
                }
            } else {
                preparedStatement.setString(13, null);
                preparedStatement.setString(14, null);
                preparedStatement.setString(15, null);
                preparedStatement.setString(16, null);
                preparedStatement.setString(17, null);
                preparedStatement.setString(18, null);
                preparedStatement.setString(19, null);
                preparedStatement.setString(20, null);
                preparedStatement.setString(21, null);
                preparedStatement.setString(22, null);
                preparedStatement.setString(23, null);
                preparedStatement.setString(24, null);
                preparedStatement.setString(25, null);
                preparedStatement.setString(26, null);
                preparedStatement.setString(27, null);
            }

            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String path = req.getContextPath() + "/cardout?IDnewCard="+ID;
        resp.sendRedirect(path);


        /*req.setAttribute("IDnewCard", ID);
        System.out.println("IDnewCard: " + ID);
        req.getRequestDispatcher("/views/EditViewsCard/CardOut.jsp").forward(req,resp);*/

/*        req.setAttribute("createObj", "Запись добавлена. Присвоенно ID: "+ID);
        doGet(req, resp);*/
    }
}