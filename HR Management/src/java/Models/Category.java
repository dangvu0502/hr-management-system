/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author lehun
 */
public class Category {

    private int id;
    private String CategoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public Category() {
    }

    public Category(int id, String CategoryName) {
        this.id = id;
        this.CategoryName = CategoryName;
    }


}
