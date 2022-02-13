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
    private String Category_Name;

    public Category(int id, String Category_Name) {
        this.id = id;
        this.Category_Name = Category_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String Category_Name) {
        this.Category_Name = Category_Name;
    }

}
