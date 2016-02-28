package database;

import constants.Constants;
import models.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataService {
	private static final Logger LOGGER = Logger
			.getLogger(DataService.class);
	private static List<DBConnection> conPool = new ArrayList<DBConnection>();
	private static Object monitor = new Object();

	public boolean init() {
		try{
			LOGGER.info("init database");
			for (int i = 0; i < Constants.CONNECTING_POOL_SIZE; i++){
				newConnection();
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}



	public DBConnection getDBConnection() {
		synchronized (monitor) {
			if (conPool.isEmpty()){
				newConnection();
			}
			DBConnection conn = conPool.remove(0);
			return conn;
		}
	}
	
	public void putDBConnection(DBConnection conn) {
		synchronized (monitor){
			conPool.add(conn);
		}
	}

	private void newConnection() {
		DBConnection conn = new DBConnection(Constants.CONNECTING_URL);
		synchronized (monitor) {
			conPool.add(conn);
		}
	}

	public List<Price> getAllPrices() {
		DBConnection conn = getDBConnection();
		List<Price> result = conn.getAllPrices();
		this.putDBConnection(conn);
		return result;
	}

	public Price getPrice(String model) {
		DBConnection conn = getDBConnection();
		Price result = conn.getPrice(model);
		this.putDBConnection(conn);
		return result;
	}
	public void updatePrice(Price price){
		DBConnection conn = getDBConnection();
		conn.updatePrice(price);
	}

	public List<News> getAllNews(int from, int count) {
		DBConnection conn = getDBConnection();
		List<News> newsList = conn.getAllNews(from, count);
		this.putDBConnection(conn);
		return newsList;
	}
	public int toCountNewsFromCity() {
		DBConnection conn = getDBConnection();
		int count = conn.toCountNewsFromCity();
		this.putDBConnection(conn);
		return count;
	}

	public Account getAccountByLoginPassword(String login, String password) {
		DBConnection conn = getDBConnection();
		Account account = conn.getAccountByLoginPassword(login, password);
		this.putDBConnection(conn);
		return account;
	}

	public List<String> getAllUrlToOpenMeetingByCity() {
		DBConnection conn = getDBConnection();
		List<String> urls = conn.getAllUrlToOpenMeetingByCity();
		this.putDBConnection(conn);
		return urls;
	}

	public News getNewsByUrl(String url) {
		DBConnection conn = getDBConnection();
		News news = conn.getNewsByUrl(url);
		this.putDBConnection(conn);
		return news;
	}

	public void addPhotoToNews(News news) {
		DBConnection conn = getDBConnection();
		conn.addPhotoToNews(news);
		this.putDBConnection(conn);
	}

	public List<News> getRecommendedNews(int newsId) {
		DBConnection conn = getDBConnection();
		List<News> news = conn.getRecommendedNews(newsId);
		this.putDBConnection(conn);
		return news;
	}

	public List<String> getAllUrlToNewsByCity() {
		DBConnection conn = getDBConnection();
		List<String> urls = conn.getAllUrlToNewsByCity();
		this.putDBConnection(conn);
		return urls;
	}

	public List<Photo> loadAllPhotosToNews() {
		DBConnection conn = getDBConnection();
		List<Photo> photos = conn.getAllPhotosToNews();
		this.putDBConnection(conn);
		return photos;
	}

	public void close() {
		DBConnection conn = getDBConnection();
		conn.close();
	}
}
