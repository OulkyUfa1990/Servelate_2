package app.login.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/menuAdmin")
public class MenuAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) {
            String newUrl = "/index";
            String redirectUrl = resp.encodeRedirectURL(newUrl);
            resp.sendRedirect(redirectUrl);
        } else {
            getServletContext().getRequestDispatcher("/views/Admin/menuAdmin.jsp").forward(req, resp);
        }
    }
}
