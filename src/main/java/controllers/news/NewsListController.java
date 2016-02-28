package controllers.news;

import controllers.AbstractWebtasksServletHandler;
import database.DataService;
import models.News;
import org.apache.log4j.Logger;
import utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by MNS on 10.07.2015.
 */
public class NewsListController extends AbstractWebtasksServletHandler {
    private static final Logger LOGGER = Logger.getLogger(NewsListController.class);

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataService service = new DataService();
        HttpSession session = request.getSession();

        String pageStr = request.getParameter("page");
        int countOfNews = service.toCountNewsFromCity();

        List<News> newsList = null;
        if (pageStr == null) {
            newsList = service.getAllNews(0, NUM_NEWS_ON_PAGE);
            System.out.println(newsList.size());
            request.setAttribute("page", 1);
        } else {
            int page = Integer.parseInt(pageStr);
            newsList = service.getAllNews((page * NUM_NEWS_ON_PAGE) - NUM_NEWS_ON_PAGE, NUM_NEWS_ON_PAGE);
            System.out.println(newsList.size());
            request.setAttribute("page", page);
        }
        TextUtils.cutNewsTextBlok(newsList);
        request.setAttribute("countOfPages", (int)Math.ceil(((double)countOfNews / NUM_NEWS_ON_PAGE)));
        request.setAttribute("newsList", newsList);
        request.setAttribute("displayName", "news");
        this.gotoToJSP("news/newsList.jsp", request, response);
    }

}



