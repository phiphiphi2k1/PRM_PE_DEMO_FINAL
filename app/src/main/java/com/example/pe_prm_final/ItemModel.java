package com.example.pe_prm_final;

import java.io.Serializable;

public class ItemModel implements Serializable {
    private String id;
    private String title;
    private String author;
    private String pages;

    public ItemModel(String id, String title, String author, String pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
