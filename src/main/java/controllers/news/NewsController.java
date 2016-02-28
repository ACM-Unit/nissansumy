package controllers.news;

import controllers.AbstractWebtasksServletHandler;
import database.DataService;
import models.News;
import models.Photo;
import utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by MNS on 04.08.2015.
 */
public class NewsController extends AbstractWebtasksServletHandler {

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception  {
        DataService service = new DataService();
        HttpSession session = request.getSession();
        String newsUrl = UrlUtils.getNewsUrl(request,service);
        News news = service.getNewsByUrl(newsUrl);
        service.addPhotoToNews(news);
        List<News> recommendedNews = service.getRecommendedNews(news.getId());
        List<Photo> photosList = service.loadAllPhotosToNews();
        request.setAttribute("photosList", photosList);
        request.setAttribute("news", news);
        request.setAttribute("recommendedNews", recommendedNews);
        request.setAttribute("displayName", news.getTitle());
        gotoToJSP("news/news.jsp", request, response);
    }
}