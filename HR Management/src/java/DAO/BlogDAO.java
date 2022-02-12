/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Models.BLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lehun
 */
public class BlogDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Vector<BLog> GetBlogList(int page) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.blog limit 3 offset ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                BLog e = new BLog();
                e.setId(rs.getInt(1));
                e.setSlug(rs.getString(2));
                e.setThumnail_Image(rs.getString(3));
                e.setTittle(rs.getString(4));
                e.setBrieft(rs.getString(5));
                e.setCategory(rs.getInt(6));
                e.setContent(rs.getString(7));
                e.setAuthor(rs.getString(8));
                e.setPublishDate(rs.getString(9));
                vec.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public BLog GetBlogBySlug(String Slug) {
        BLog e = new BLog();
        try {
            String sql = "SELECT * FROM hr_system_v2.blog where Slug=?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Slug);
            rs = ps.executeQuery();
            while (rs.next()) {
                e.setId(rs.getInt(1));
                e.setSlug(rs.getString(2));
                e.setThumnail_Image(rs.getString(3));
                e.setTittle(rs.getString(4));
                e.setBrieft(rs.getString(5));
                e.setCategory(rs.getInt(6));
                e.setContent(rs.getString(7));
                e.setAuthor(rs.getString(8));
                e.setPublishDate(rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
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
