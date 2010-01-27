/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.mysql.jdbc.Connection;
import database.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeff
 */
public class Region extends Record {

    int id;
    String name;
    int managerid;

    public Region() {
    }

    public Region(int id, String name, int managerid) {
        this.id = id;
        this.name = name;
        this.managerid = managerid;
    }

    public Region(String name, int managerid) {
        this.name = name;
        this.managerid = managerid;
    }

    public boolean deleteRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM regions WHERE rid=?");
            stmt.setInt(1, this.id);
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            dbConnection.closeConnection();
            return false;
        }
    }

    public boolean updateRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE regions SET rname = ?, managerid=? WHERE rid=?");
            stmt.setString(1, this.getName());
            stmt.setInt(2, this.getManagerid());
            stmt.setInt(3, this.getId());
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            dbConnection.closeConnection();
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerid() {
        return managerid;
    }

    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean addNewRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO regions (rname,managerid) VALUES" +
                    "(?,?)");
            stmt.setString(1, this.getName());
            stmt.setInt(2, this.getManagerid());
            stmt.execute();
            //succeed
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            dbConnection.closeConnection();
            return false;
        }
    }

    @Override
    public ArrayList<Region> getAllRecord() {
        ArrayList<Region> reg_list = new ArrayList<Region>();
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conn.prepareStatement("SELECT * FROM regions");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Region reg = new Region();
                reg.setId(rs.getInt("rid"));
                reg.setName(rs.getString("rname"));
                reg.setManagerid(rs.getInt("managerid"));
                reg_list.add(reg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return reg_list;
    }

    public ArrayList getRecord() {
        ArrayList<Region> reg_list = new ArrayList<Region>();
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conn.prepareStatement("SELECT * from regions WHERE rid=?");
            stmt.setInt(1, this.id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Region reg = new Region();
                reg.setId(rs.getInt("rid"));
                reg.setName(rs.getString("rname"));
                reg.setManagerid(rs.getInt("managerid"));
                reg_list.add(reg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return reg_list;
    }
}
