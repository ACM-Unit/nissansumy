package models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MNS on 14.07.2015.
 */
public class BlokString {
    private int id;
    private String text;
    private int priority;
    private boolean hasItem;
    private List<ItemString> items = new LinkedList<ItemString>();

    public BlokString() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<ItemString> getItems() {
        return items;
    }

    public void setItems(List<ItemString> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addItem(ItemString item){
        items.add(item);
    }

    public boolean hasItem() {
        return hasItem;
    }

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    public boolean isHasItem() {
        return hasItem;
    }
}

