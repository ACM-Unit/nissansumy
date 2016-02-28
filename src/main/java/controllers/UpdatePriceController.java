package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import database.DataService;
import models.Price;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class UpdatePriceController extends AbstractWebtasksServletHandler {
    private final Map<Integer, String> mappings = new HashMap<Integer, String>();
    public UpdatePriceController() {
        mappings.put(ROLE_ADMIN, "/admin");
        mappings.put(ROLE_USER, "/user");
    }
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataService dataService=new DataService();
        String micrastr;
        String sentrastr;
        String jukestr;
        String qashqaistr;
        String muranostr;
        String xtrailstr;
        String patrolstr;
        if(request.getParameter("Button").equals("nissan")) {
            Document html = Jsoup.parse("http://www.nissan.ua");
            String title = html.title();
            String h1 = html.body().getElementsByTag("h1").text();
            Document htmlFile = null;
            try {
                htmlFile = Jsoup.connect("http://www.nissan.ua").get();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // right
            title = htmlFile.title();
            Elements div = htmlFile.getElementsByClass("nsnmn-item-price");
            micrastr = div.get(0).getElementsByTag("span").get(1).text().substring(3).split(" грн.")[0];
            sentrastr = div.get(1).getElementsByTag("span").get(1).text().substring(3).split(" грн.")[0];
            jukestr = div.get(2).getElementsByTag("span").get(1).text().substring(3).split(" грн.")[0];
            qashqaistr = div.get(3).getElementsByTag("span").get(1).text().substring(3).split(" грн.")[0];
            muranostr = div.get(4).getElementsByTag("span").get(1).text().substring(3).split(" грн.")[0];
            xtrailstr = div.get(5).getElementsByTag("span").get(1).text().substring(3).split(" грн.")[0];


        }else{
            micrastr = request.getParameter("micra");
            sentrastr = request.getParameter("sentra");
            jukestr = request.getParameter("juke");
            qashqaistr = request.getParameter("qashqai");
            muranostr = request.getParameter("murano");
            xtrailstr = request.getParameter("xtrail");
            patrolstr = request.getParameter("patrol");
            Price patrol = new Price();
            patrol.setModel("patrol");
            patrol.setPrice(patrolstr);
            dataService.updatePrice(patrol);
        }
        Price micra = new Price();
        micra.setModel("micra");
        micra.setPrice(micrastr);
        dataService.updatePrice(micra);
        Price sentra = new Price();
        sentra.setModel("sentra");
        sentra.setPrice(sentrastr);
        dataService.updatePrice(sentra);
        Price juke = new Price();
        juke.setModel("juke");
        juke.setPrice(jukestr);
        dataService.updatePrice(juke);
        Price qashqai = new Price();
        qashqai.setModel("qashqai");
        qashqai.setPrice(qashqaistr);
        dataService.updatePrice(qashqai);
        Price murano = new Price();
        murano.setModel("murano");
        murano.setPrice(muranostr);
        dataService.updatePrice(murano);
        Price xtrail = new Price();
        xtrail.setModel("xtrail");
        xtrail.setPrice(xtrailstr);
        dataService.updatePrice(xtrail);
        response.sendRedirect("/adminPage");
    }
}
