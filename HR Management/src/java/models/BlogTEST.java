/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author lehun
 */
public class BlogTEST {

    public int id;
    public String slug;
    public String thumnail_Image;
    public String Tittle;
    public String brieft;
    public String Category;

    public int getId() {
        return id;
    }

    public BlogTEST() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public BlogTEST(int id, String slug, String thumnail_Image, String Tittle, String brieft, String Category) {
        this.id = id;
        this.slug = slug;
        this.thumnail_Image = thumnail_Image;
        this.Tittle = Tittle;
        this.brieft = brieft;
        this.Category = Category;
    }
}
