/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author lehun
 */
public class BLog {

    private int id;
    private String slug;
    private String thumnail_Image;
    private String Tittle;
    private String brieft;
    private String Category;

    private String PublishDate;
    private String content;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumnail_Image() {
        return thumnail_Image;
    }

    public void setThumnail_Image(String thumnail_Image) {
        this.thumnail_Image = thumnail_Image;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String Tittle) {
        this.Tittle = Tittle;
    }

    public String getBrieft() {
        return brieft;
    }

    public void setBrieft(String brieft) {
        this.brieft = brieft;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String PublishDate) {
        this.PublishDate = PublishDate;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BLog() {
    }

    public BLog( String thumnail_Image, String Tittle, String brieft, String Category, String PublishDate, String slug, String content, String author) {
        this.thumnail_Image = thumnail_Image;
        this.Tittle = Tittle;
        this.brieft = brieft;
        this.Category = Category;
        this.PublishDate = PublishDate;
        this.slug = slug;
        this.content = content;
        this.author = author;
    }

}
