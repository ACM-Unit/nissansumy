package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import database.DataService;
import models.Account;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class AdminPageController extends AbstractWebtasksServletHandler {
    private final Map<Integer, String> mappings = new HashMap<Integer, String>();
    public AdminPageController() {
        mappings.put(ROLE_ADMIN, "/admin");
        mappings.put(ROLE_USER, "/user");

    }
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataService service = new DataService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Account account = service.getAccountByLoginPassword(login, password);
        if (account.getId() == 0) {
            request.setAttribute("validMessage", "failed");
            response.sendRedirect("/home.jsp");
        } else {
            request.getSession().setAttribute("auth", "yes");
            gotoToJSP("adminPage.jsp", request, response);
        }
    }
}
