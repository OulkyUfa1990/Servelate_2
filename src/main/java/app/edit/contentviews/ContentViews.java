package app.edit.contentviews;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

@WebServlet("/contentviews")
public class ContentViews extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbcard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ildar1990";
    private static final String SELECT = "SELECT * FROM cards WHERE cards.idC = ?;";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        InputStream in = null;
        BufferedInputStream bin=null;
        BufferedOutputStream bout=null;
        String idC = req.getParameter("id");
        int cont = Integer.parseInt(req.getParameter("cont")); //указывает с какой ячейки в таблице скачивать данные
        String cont_type = req.getParameter("cont_type");
        System.out.println("cont, ячейка в БД №: " + cont + ", content-type: " + cont_type);

        ServletOutputStream out;
        out = resp.getOutputStream();
        if (cont_type.equals("image/jpeg")) {
            resp.setContentType("image/jpeg");
            resp.setHeader("Content-disposition", "attachment; filename=source.jpeg");
        } else if (cont_type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            resp.setContentType("application/msword");
            resp.setHeader("Content-disposition", "attachment; filename=source.docx");
        } else if (cont_type.equals("image/gif")) {
            resp.setContentType("image/gif");
            resp.setHeader("Content-disposition", "attachment; filename=source.gif");
        } else if (cont_type.equals("image/png")) {
            resp.setContentType("image/png");
            resp.setHeader("Content-disposition", "attachment; filename=source.png");
        } else if (cont_type.equals("image/tiff")) {
            resp.setContentType("image/tiff");
            resp.setHeader("Content-disposition", "attachment; filename=source.tiff");
        } else if (cont_type.equals("application/pdf")) {
            resp.setContentType("application/pdf");
            resp.setHeader("Content-disposition", "attachment; filename=source.pdf");
        } else if (cont_type.equals("text/plain")) {
            resp.setContentType("text/plain");
            resp.setHeader("Content-disposition", "attachment; filename=source.txt");
        } else if (cont_type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; filename=source.xlsx");
        } else if (cont_type.equals("application/zip")) {
            resp.setContentType("application/zip");
            resp.setHeader("Content-disposition", "attachment; filename=source.zip");
        } else if (cont_type.equals("application/octet-stream")) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-disposition", "attachment; filename=source.dwg");
        } else System.out.println("формат файла не определен");
        try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                preparedStatement = connection.prepareStatement(SELECT);
                preparedStatement.setString(1, idC);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    in = resultSet.getBinaryStream(cont);
                }
                bin = new BufferedInputStream(in);
                bout = new BufferedOutputStream(out);

                int ch = 0;
                while ((ch = bin.read()) != -1) {
                    bout.write(ch);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (bin != null) bin.close();
                    if (in != null) in.close();
                    if (bout != null) bout.close();
                    if (out != null) out.close();
                    if (connection != null) connection.close();
                } catch (IOException | SQLException ex) {
                    System.out.println("Error : " + ex.getMessage());
                }
            }
            getServletContext().getRequestDispatcher("/views/ContentViews/ContentViews.jsp").forward(req, resp);
    }
}
