package models;

/**
 * Created by dell on 06.09.2015.
 */
public class Photo {
    private int id;
    private String name;
    private String path;
    private String pathFullSizeImg;
    private String Priority;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathFullSizeImg() {return pathFullSizeImg;}

    public void setPathFullSizeImg(String pathFullSizeImg) {this.pathFullSizeImg = pathFullSizeImg;}

    public String getPriority() {return Priority;}

    public void setPriority(String priority) {Priority = priority;}

}
