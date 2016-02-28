package models;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell on 06.07.2015.
 */
public class News {
    private int id;
    private String title;
    private String image;
    private Date date;
    private String url;
    private List<BlokString> newsBlok = new LinkedList<BlokString>();
    private List<Photo> photos = new LinkedList<>();

    public News() {

    }

    public void setNewsBlok(List<BlokString> newsBlok) {
        this.newsBlok = newsBlok;
    }

    public void setPhotos(LinkedList<Photo> photos) {
        this.photos = photos;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void addToPhotos(Photo photo) {
        this.photos.add(photo);
    }

    public void addToNewsBlok(BlokString text) {
        this.newsBlok.add(text);
    }

    public List<BlokString> getNewsBlok() {
        return this.newsBlok;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}