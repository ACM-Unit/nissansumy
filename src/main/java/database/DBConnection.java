package database;

import constants.Constants;
import models.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 19.01.2016.
 */
public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    private Connection conn = null;
    private ResultSet rs = null;
    private static PreparedStatement loadAllPrices;
    private static PreparedStatement loadPrice;
    private static PreparedStatement loadAllNews;
    private static PreparedStatement insertPrice;
    private static PreparedStatement updatePrice;
    private static PreparedStatement getAccountByLoginPassword;
    private static PreparedStatement getAllNews;
    private static PreparedStatement toCountNewsFromCity;
    private static PreparedStatement getAllUrlToOpenMeetingByCity;
    private static PreparedStatement getAllUrlToNewsByCity;
    private static PreparedStatement getNewsByUrl;
    private static PreparedStatement getNewsPhotosById;
    private static PreparedStatement getRecommendedNews;
    private static PreparedStatement getAllPhotosToNews;
    public DBConnection(String url) {
        try {
            String dbUrl = Constants.CONNECTING_URL;
            if (System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null) {
                dbUrl = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&characterEncoding=UTF-8",
                        System.getenv("OPENSHIFT_MYSQL_DB_HOST"),
                        System.getenv("OPENSHIFT_MYSQL_DB_PORT"),
                        System.getenv("OPENSHIFT_GEAR_NAME"),
                        System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"),
                        System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
            }
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl);
            loadPreparedStatements();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadPreparedStatements() {
        try {
            getAllPhotosToNews = conn.prepareStatement("Select \n" +
                    "p.id,\n" +
                    "p.name,\n" +
                    "p.path,\n" +
                    "p.path_full_size_img,\n" +
                    "p.description,\n" +
                    "p.priority\n" +
                    "from link_news_photo as l\n" +
                    "left outer join photo as p on l.id_photo = p.id\n" +
                    "where p.status = 1 order by p.priority");
            getAllUrlToOpenMeetingByCity = conn.prepareStatement("select op.url as op_url from link_city_open_meeting as l left outer join open_meeting as op on l.id_open_meeting = op.id");
            getAllUrlToNewsByCity = conn.prepareStatement("select n.url as n_url from link_city_news as l left outer join news as n on l.id_news = n.id");
            loadAllPrices = conn.prepareStatement("SELECT * from prices");
            loadPrice=conn.prepareStatement("SELECT * from prices WHERE  model=?");
            loadAllNews = conn.prepareStatement("SELECT * from NEWS");
            insertPrice = conn.prepareStatement("INSERT INTO prices(model, price) VALUES(?, ?)");
            updatePrice = conn.prepareStatement("UPDATE prices SET prices.price = ? WHERE prices.model= ?");
            //news
            toCountNewsFromCity = conn.prepareStatement("SELECT count(*) AS count FROM link_city_news");
            getAllNews = conn.prepareStatement("SELECT n.id as n_id,\n" +
                    "n.title as n_title,\n" +
                    "n.date as n_date,\n" +
                    "n.url as n_url, \n" +
                    "n.image as n_image,\n" +
                    "b.id as b_id, \n" +
                    "b.text as b_text, \n" +
                    "b.has_item as b_has_item,\n" +
                    "b.priority as b_priority,\n" +
                    "i.id as i_id,\n" +
                    "i.item as i_item, \n" +
                    "i.priority as i_priority\n" +
                    "from link_city_news as l\n" +
                    "inner join (select * from news limit ?, ?) n on n.id = l.id_news\n" +
                    "left outer join news_blok as b on l.id_news = b.id_news\n" +
                    "left outer join news_item_of_blok as i on b.id_news = i.id_blok");
            getNewsByUrl = conn.prepareStatement("SELECT n.id as n_id,\n" +
                    "n.title as n_title,\n" +
                    "n.date as n_date,\n" +
                    "n.url as n_url, \n" +
                    "n.image as n_image,\n" +
                    "b.id as b_id, \n" +
                    "b.text as b_text, \n" +
                    "b.has_item as b_has_item,\n" +
                    "b.priority as b_priority,\n" +
                    "i.id as i_id,\n" +
                    "i.item as i_item, \n" +
                    "i.priority as i_priority\n" +
                    "from news as n\n" +
                    "left outer join news_blok as b on n.id = b.id_news\n" +
                    "left outer join news_item_of_blok as i on b.id_news = i.id_blok\n" +
                    "WHERE n.url = ? \n" +
                    "order by n.date, b.priority, i.priority");
            getNewsPhotosById = conn.prepareStatement("select p.id, p.name, p.path, p.description, p.status \n" +
                    "from link_news_photo l\n" +
                    "left outer join photo p on l.id_photo = p.id where l.id_news = ? and p.status = 1");
            getRecommendedNews = conn.prepareStatement("SELECT s.id_news, n.id, n.url, n.date, n.title, n.image from similar_news as s left outer join news as n on s.id_news_similar = n.id where s.id_news = ? order by n.date");

            //accounts
            getAccountByLoginPassword = conn.prepareStatement("select * from account where login = ? and password = ?");
              } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closePreparedStatements() {
        try {
            getAccountByLoginPassword.close();
            loadAllPrices.close();
            loadPrice.close();
            insertPrice.close();
            loadAllNews.close();
            updatePrice.close();
            getAllNews.close();
            toCountNewsFromCity.close();
            getAllUrlToOpenMeetingByCity.close();
            getAllUrlToNewsByCity.close();
            getNewsByUrl.close();
            getNewsPhotosById.close();
            getRecommendedNews.close();
            getAllPhotosToNews.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*-----------------------prices-------------------------*/
    public List<Price> getAllPrices() {
        rs = null;
        List<Price> result = new LinkedList<Price>();
        try {
            rs = loadAllPrices.executeQuery();
            while (rs.next()) {
                Price r = new Price();
                r.setId(rs.getInt("id"));
                r.setPrice(rs.getString("price"));
                r.setModel(rs.getString("model"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Price getPrice(String model) {
        rs = null;
        Price result = new Price();
        try {
            loadPrice.setNString(1, model);
            rs = loadPrice.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setPrice(rs.getString("price"));
                result.setModel(rs.getString("model"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*---------------end prices---------------------*/

    public Account getAccountByLoginPassword(String login, String password) {
        rs = null;
        Account account = new Account();

        try { // select * from account where login = ? and password = ?
            LOGGER.info(login + " / " + password);
            getAccountByLoginPassword.setString(1, login);
            getAccountByLoginPassword.setString(2, password);
            rs = getAccountByLoginPassword.executeQuery();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setLogin(login);
                account.setPassword(password);
                LOGGER.info(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    public void updatePrice(Price price){
        rs = null;
        try {
            updatePrice.setString(1, price.getPrice());
            updatePrice.setString(2, price.getModel());
            updatePrice.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<News> getAllNews(int from, int count) {
        rs = null;
        List<News> newsList = new LinkedList<>();
        try {
            getAllNews.setInt(1, from);
            getAllNews.setInt(2, count);
            rs = getAllNews.executeQuery();
            if (rs.next())
                this.setNewsWithParameters(rs, newsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    private void setNewsWithParameters(ResultSet rs, List<News> newsList) throws SQLException {
        News news = new News();
        news.setId(rs.getInt("n_id"));
        news.setTitle(rs.getString("n_title"));
        news.setDate(rs.getDate("n_date"));
        news.setUrl(rs.getString("n_url"));
        news.setImage(rs.getString("n_image"));
        this.addTextToNews(news, rs, newsList);
    }

    private void addTextToNews(News news, ResultSet rs, List<News> newsList) throws SQLException {
        BlokString text = new BlokString();
        text.setId(rs.getInt("b_id"));
        text.setText(rs.getString("b_text"));
        text.setPriority(rs.getInt("b_priority"));
        text.setHasItem((rs.getInt("b_has_item") == 1));
        this.setItemsToText(rs, text, newsList, news);
    }

    private void setItemsToText(ResultSet rs, BlokString text, List<News> newsList, News news) throws SQLException {
        if (text.hasItem()) {
            ItemString item = new ItemString();
            item.setId(rs.getInt("i_id"));
            item.setPriority(rs.getInt("i_priority"));
            item.setItem(rs.getString("i_item"));
            text.addItem(item);
        }
        this.transferToNextLineNews(rs, newsList, news, text);
    }

    private void transferToNextLineNews(ResultSet rs, List<News> newsList, News news, BlokString text) throws SQLException {
        if (rs.isLast()) {
            news.addToNewsBlok(text);
            newsList.add(news);
        }

        while (rs.next()) {
            if (text.getText() == null) {
                newsList.add(news);
                this.setNewsWithParameters(rs, newsList);
            } else if (text.getId() == rs.getInt("b_id")) {
                this.setItemsToText(rs, text, newsList, news);
            } else if (news.getId() == rs.getInt("n_id")) {
                news.addToNewsBlok(text);
                this.addTextToNews(news, rs, newsList);
            } else {
                news.addToNewsBlok(text);
                newsList.add(news);
                this.setNewsWithParameters(rs, newsList);
            }
        }
    }

    public int toCountNewsFromCity() {
        rs = null;
        int count = 0;
        try {
            rs = toCountNewsFromCity.executeQuery();
            if (rs.next())
                count = rs.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public void close() {
        closePreparedStatements();
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            LOGGER.info("close() exeption " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> getAllUrlToOpenMeetingByCity() {
        rs = null;
        List<String> urls = new LinkedList<>();

        try { // select op.url as op_url from link_city_open_meeting as l left outer join open_meeting as op on l.id_open_meeting = op.id where l.id_city = ?
            rs = getAllUrlToOpenMeetingByCity.executeQuery();
            while (rs.next()) {
                urls.add(rs.getString("op_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urls;
    }
    public List<String> getAllUrlToNewsByCity() {
        rs = null;
        List<String> urls = new LinkedList<>();

        try { // select n.url as n_url from link_city_news as l left outer join news as n on l.id_news = n.id where l.id_city = ?
            rs = getAllUrlToNewsByCity.executeQuery();
            while (rs.next()) {
                urls.add(rs.getString("n_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urls;
    }

    public News getNewsByUrl(String url) {
        rs = null;
        List<News> newsList = new LinkedList<>();
        try {
            getNewsByUrl.setString(1, url);
            rs = getNewsByUrl.executeQuery();
            if (rs.next())
                this.setNewsWithParameters(rs, newsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList.get(0);
    }

    public void addPhotoToNews(News news) {
        rs = null;
        try {
            getNewsPhotosById.setInt(1, news.getId());
            rs = getNewsPhotosById.executeQuery();
            while (rs.next()) {
                Photo photo = new Photo();
                photo.setId(rs.getInt("id"));
                photo.setName(rs.getString("name"));
                photo.setPath(rs.getString("path"));
                photo.setDescription(rs.getString("description"));
                news.addToPhotos(photo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<News> getRecommendedNews(int idNews) {
        rs = null;
        List<News> newsList = new LinkedList<>();
        try {
            getRecommendedNews.setInt(1, idNews);
            rs = getRecommendedNews.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImage(rs.getString("image"));
                news.setUrl(rs.getString("url"));
                news.setDate(rs.getDate("date"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public List<Photo> getAllPhotosToNews() {
        rs = null;
        List<Photo> photos = new LinkedList<>();

        try {
            /*getAllClientsEmailFromCity.setInt(1, idCity);*/
            rs = getAllPhotosToNews.executeQuery();
            while (rs.next()) {
                Photo photo = new Photo();
                photo.setId(rs.getInt("id"));
                photo.setDescription(rs.getString("description"));
                photo.setPath(rs.getString("path"));
                photo.setPathFullSizeImg(rs.getString("path_full_size_img"));
                photos.add(photo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return photos;


    }


}