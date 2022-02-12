/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Models.BlogTEST;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author lehun
 */
public class BlogDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Vector<BlogTEST> GetBlogList(int page) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT b.id,b.slug,b.thumnail_image,b.Tittle,b.brieft,c.Category_Name\n"
                    + "FROM hr_system_v2.blog b \n"
                    + "LEFT JOIN hr_system_v2.category c\n"
                    + "ON b.category = c.id limit 5 offset ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogTEST e = new BlogTEST();
                e.setId(rs.getInt(1));
                e.setSlug(rs.getString(2));
                e.setThumnail_Image(rs.getString(3));
                e.setTittle(rs.getString(4));
                e.setBrieft(rs.getString(5));
                e.setCategory(rs.getString(6));
                vec.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }
       public int GetTotalBlog() {
        int total = 0;
        try {
            String sql = "select count(*) from hr_system_v2.blog";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }

}
