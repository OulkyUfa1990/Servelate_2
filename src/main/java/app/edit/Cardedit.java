package app.edit;

import app.card.entities.ListRevision;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cardedit")
@MultipartConfig
public class Cardedit extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    private static final String SELECT = "SELECT * FROM cards WHERE cards.idC = ?;";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<String> cardedit = new ArrayList<>();
    String idCEdit; //ID для подключения к базе и поиска по конкретному этому ID
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String ID = req.getParameter("idС");
        String status = req.getParameter("status");
        String revision = req.getParameter("revision");
        String title = req.getParameter("title");
        String titleMore = req.getParameter("titleMore");
        String process = req.getParameter("process");
        String type_of_activity = req.getParameter("type_of_activity");
        String record_type = req.getParameter("record_type");
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
            int kl = listFileContent.size();

            if (kl != 0) {
                for (int i = 0; i < kl; i++) {
                        if (cardedit.get(14) == null) {
                            Class.forName("com.mysql.jdbc.Driver");
                            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                            String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, type_of_activity = ?," +
                                    "record_type = ?, direction_of_activity = ?, equipment = ?," +
                                    "responsible = ?, content_1 = ?, content_1_name = ?, content_1_type = ? WHERE idC = ?";
                            preparedStatement = connection.prepareStatement(sql);

                            preparedStatement.setString(1, status);
                            preparedStatement.setString(2, revision);
                            preparedStatement.setString(3, title);
                            preparedStatement.setString(4, titleMore);
                            preparedStatement.setString(5, process);
                            preparedStatement.setString(6, type_of_activity);
                            preparedStatement.setString(7, record_type);
                            preparedStatement.setString(8, direction_of_activity);
                            preparedStatement.setString(9, equipment);
                            preparedStatement.setString(10, responsible);
                            preparedStatement.setString(14, ID); //Для параметра SELECT поля WHERE

                            preparedStatement.setBinaryStream(11, listFileContent.get(i));
                            preparedStatement.setString(12, listNameContent.get(i));
                            preparedStatement.setString(13, listTypeContent.get((i)));

                            preparedStatement.executeUpdate();
                            connection.close();
                                System.out.println("Отработала ячейка контент 1");

                            req.setAttribute("update", "Корректировки внесены...");

                            cardedit.clear();
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                                preparedStatement = connection.prepareStatement(SELECT);
                                preparedStatement.setString(1, idCEdit);
                                ResultSet resultSet = preparedStatement.executeQuery();

                                if (resultSet != null) {
                                    while (resultSet.next()) {
                                        int j = 1;
                                        while (j <= 29) {
                                            cardedit.add(resultSet.getString(j++));
                                        }
                                    }
                                } else System.out.println("resultSet пустой");
                                connection.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        } else if (cardedit.get(17) == null) {
                            Class.forName("com.mysql.jdbc.Driver");
                            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                            String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, type_of_activity = ?," +
                                    "record_type = ?, direction_of_activity = ?, equipment = ?," +
                                    "responsible = ?, content_2 = ?, content_2_name = ?, content_2_type = ? WHERE idC = ?";
                            preparedStatement = connection.prepareStatement(sql);

                            preparedStatement.setString(1, status);
                            preparedStatement.setString(2, revision);
                            preparedStatement.setString(3, title);
                            preparedStatement.setString(4, titleMore);
                            preparedStatement.setString(5, process);
                            preparedStatement.setString(6, type_of_activity);
                            preparedStatement.setString(7, record_type);
                            preparedStatement.setString(8, direction_of_activity);
                            preparedStatement.setString(9, equipment);
                            preparedStatement.setString(10, responsible);
                            preparedStatement.setString(14, ID); //Для параметра SELECT поля WHERE

                            preparedStatement.setBinaryStream(11, listFileContent.get(i));
                            preparedStatement.setString(12, listNameContent.get(i));
                            preparedStatement.setString(13, listTypeContent.get((i)));

                            preparedStatement.executeUpdate();
                            connection.close();
                            System.out.println("Отработала ячейка контент 2");

                            req.setAttribute("update", "Корректировки внесены...");

                            cardedit.clear();
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                                preparedStatement = connection.prepareStatement(SELECT);
                                preparedStatement.setString(1, idCEdit);
                                ResultSet resultSet = preparedStatement.executeQuery();

                                if (resultSet != null) {
                                    while (resultSet.next()) {
                                        int j = 1;
                                        while (j <= 29) {
                                            cardedit.add(resultSet.getString(j++));
                                        }
                                    }
                                } else System.out.println("resultSet пустой");
                                connection.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        } else if (cardedit.get(20) == null) {
                            Class.forName("com.mysql.jdbc.Driver");
                            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                            String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, type_of_activity = ?," +
                                    "record_type = ?, direction_of_activity = ?, equipment = ?," +
                                    "responsible = ?, content_3 = ?, content_3_name = ?, content_3_type = ? WHERE idC = ?";
                            preparedStatement = connection.prepareStatement(sql);

                            preparedStatement.setString(1, status);
                            preparedStatement.setString(2, revision);
                            preparedStatement.setString(3, title);
                            preparedStatement.setString(4, titleMore);
                            preparedStatement.setString(5, process);
                            preparedStatement.setString(6, type_of_activity);
                            preparedStatement.setString(7, record_type);
                            preparedStatement.setString(8, direction_of_activity);
                            preparedStatement.setString(9, equipment);
                            preparedStatement.setString(10, responsible);
                            preparedStatement.setString(14, ID); //Для параметра SELECT поля WHERE

                            preparedStatement.setBinaryStream(11, listFileContent.get(i));
                            preparedStatement.setString(12, listNameContent.get(i));
                            preparedStatement.setString(13, listTypeContent.get((i)));

                            preparedStatement.executeUpdate();
                            connection.close();
                            System.out.println("Отработала ячейка контент 3");

                            req.setAttribute("update", "Корректировки внесены...");

                            cardedit.clear();
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                                preparedStatement = connection.prepareStatement(SELECT);
                                preparedStatement.setString(1, idCEdit);
                                ResultSet resultSet = preparedStatement.executeQuery();

                                if (resultSet != null) {
                                    while (resultSet.next()) {
                                        int j = 1;
                                        while (j <= 29) {
                                            cardedit.add(resultSet.getString(j++));
                                        }
                                    }
                                } else System.out.println("resultSet пустой");
                                connection.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        } else if (cardedit.get(23) == null) {
                            Class.forName("com.mysql.jdbc.Driver");
                            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                            String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, type_of_activity = ?," +
                                    "record_type = ?, direction_of_activity = ?, equipment = ?," +
                                    "responsible = ?, content_4 = ?, content_4_name = ?, content_4_type = ? WHERE idC = ?";
                            preparedStatement = connection.prepareStatement(sql);

                            preparedStatement.setString(1, status);
                            preparedStatement.setString(2, revision);
                            preparedStatement.setString(3, title);
                            preparedStatement.setString(4, titleMore);
                            preparedStatement.setString(5, process);
                            preparedStatement.setString(6, type_of_activity);
                            preparedStatement.setString(7, record_type);
                            preparedStatement.setString(8, direction_of_activity);
                            preparedStatement.setString(9, equipment);
                            preparedStatement.setString(10, responsible);
                            preparedStatement.setString(14, ID); //Для параметра SELECT поля WHERE

                            preparedStatement.setBinaryStream(11, listFileContent.get(i));
                            preparedStatement.setString(12, listNameContent.get(i));
                            preparedStatement.setString(13, listTypeContent.get((i)));

                            preparedStatement.executeUpdate();
                            connection.close();
                            System.out.println("Отработала ячейка контент 4");

                            req.setAttribute("update", "Корректировки внесены...");

                            cardedit.clear();
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                                preparedStatement = connection.prepareStatement(SELECT);
                                preparedStatement.setString(1, idCEdit);
                                ResultSet resultSet = preparedStatement.executeQuery();

                                if (resultSet != null) {
                                    while (resultSet.next()) {
                                        int j = 1;
                                        while (j <= 29) {
                                            cardedit.add(resultSet.getString(j++));
                                        }
                                    }
                                } else System.out.println("resultSet пустой");
                                connection.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        } else if (cardedit.get(26) == null) {
                            Class.forName("com.mysql.jdbc.Driver");
                            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                            String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, type_of_activity = ?," +
                                    "record_type = ?, direction_of_activity = ?, equipment = ?," +
                                    "responsible = ?, content_5 = ?, content_5_name = ?, content_5_type = ? WHERE idC = ?";
                            preparedStatement = connection.prepareStatement(sql);

                            preparedStatement.setString(1, status);
                            preparedStatement.setString(2, revision);
                            preparedStatement.setString(3, title);
                            preparedStatement.setString(4, titleMore);
                            preparedStatement.setString(5, process);
                            preparedStatement.setString(6, type_of_activity);
                            preparedStatement.setString(7, record_type);
                            preparedStatement.setString(8, direction_of_activity);
                            preparedStatement.setString(9, equipment);
                            preparedStatement.setString(10, responsible);
                            preparedStatement.setString(14, ID); //Для параметра SELECT поля WHERE

                            preparedStatement.setBinaryStream(11, listFileContent.get(i));
                            preparedStatement.setString(12, listNameContent.get(i));
                            preparedStatement.setString(13, listTypeContent.get((i)));

                            preparedStatement.executeUpdate();
                            connection.close();
                            System.out.println("Отработала ячейка контент 5");

                            req.setAttribute("update", "Корректировки внесены...");

                            cardedit.clear();
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                                preparedStatement = connection.prepareStatement(SELECT);
                                preparedStatement.setString(1, idCEdit);
                                ResultSet resultSet = preparedStatement.executeQuery();

                                if (resultSet != null) {
                                    while (resultSet.next()) {
                                        int j = 1;
                                        while (j <= 29) {
                                            cardedit.add(resultSet.getString(j++));
                                        }
                                    }
                                } else System.out.println("resultSet пустой");
                                connection.close();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            Class.forName("com.mysql.jdbc.Driver");
                            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                            String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, type_of_activity = ?," +
                                    "record_type = ?, direction_of_activity = ?, equipment = ?," +
                                    "responsible = ? WHERE idC = ?";
                            preparedStatement = connection.prepareStatement(sql);

                            preparedStatement.setString(1, status);
                            preparedStatement.setString(2, revision);
                            preparedStatement.setString(3, title);
                            preparedStatement.setString(4, titleMore);
                            preparedStatement.setString(5, process);
                            preparedStatement.setString(6, type_of_activity);
                            preparedStatement.setString(7, record_type);
                            preparedStatement.setString(8, direction_of_activity);
                            preparedStatement.setString(9, equipment);
                            preparedStatement.setString(10, responsible);
                            preparedStatement.setString(11, ID); //Для параметра SELECT поля WHERE

                            preparedStatement.executeUpdate();
                            connection.close();
                            System.out.println("Ячейки заполнены, файл не добавлен.");

                            req.setAttribute("update", "Первышено допустимое количство файлов в карточке (не более 5).Удалите ненужные файлы либо запакуйте в архив. Остальные текстовые параметры внесены...");
                        }
                }
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                String sql = "UPDATE cards SET status = ?, revision = ?, title = ?, title_more = ?, process = ?, " +
                        "type_of_activity = ?, record_type = ?, direction_of_activity = ?," +
                        " equipment = ?, responsible = ? WHERE idC = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, revision);
                preparedStatement.setString(3, title);
                preparedStatement.setString(4, titleMore);
                preparedStatement.setString(5, process);
                preparedStatement.setString(6, type_of_activity);
                preparedStatement.setString(7, record_type);
                preparedStatement.setString(8, direction_of_activity);
                preparedStatement.setString(9, equipment);
                preparedStatement.setString(10, responsible);
                preparedStatement.setString(11, ID); //Для параметра SELECT поля WHERE
                preparedStatement.executeUpdate();
                connection.close();
                req.setAttribute("update", "Корректировки внесены...");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String path = req.getContextPath() + "/cardout?IDnewCard="+ID;
        resp.sendRedirect(path);
/*        doGet(req, resp);*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            cardedit.clear();
            idCEdit = req.getParameter("id");
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                preparedStatement = connection.prepareStatement(SELECT);
                preparedStatement.setString(1, idCEdit);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet != null) {
                    while (resultSet.next()) {
                        int i = 1;
                        while (i <= 29) {
                            cardedit.add(resultSet.getString(i++));
                        }
                    }
                } else System.out.println("resultSet пустой");
                connection.close();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            ArrayList<ListRevision> LinkListRevision = app.card.model.LinkListRevision.ListRevision (idCEdit); //Список прошлых ID и ревизий для списка ревизий
            req.getSession().setAttribute("LinkListRevision", LinkListRevision);
            req.getSession().setAttribute("cardedit", cardedit);
            getServletContext().getRequestDispatcher("/views/EditViewsCard/Cardedit.jsp").forward(req, resp);
        }
}