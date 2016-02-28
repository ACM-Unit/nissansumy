package models;

/**
 * Created by MNS on 16.07.2015.
 */
public class ItemString {
    private int id;
    private String item;
    private int priority;
    private boolean isTrue;

    public ItemString() {
    }

    public ItemString(String text, int priority) {
        this.item = text;
        this.priority = priority;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }


}
