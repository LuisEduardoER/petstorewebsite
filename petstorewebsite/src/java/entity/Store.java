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
 *
 * @author Jeff
 */
public class Store extends Record{

    int sid;
    int regionid;
    int manager;
    String street;
    String city;
    String state;
    String zip;
    String phone;
    String name;

    public Store() {
    }

    public Store(int sid, int regionid, int managerid, String name, String street, String city, String state, String zip, String phone) {
        this.sid = sid;
        this.regionid = regionid;
        this.manager = managerid;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    public ArrayList<Store> getRecord() {
        ArrayList<Store> storelist = new ArrayList<Store>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * from stores WHERE sid=?");
            stmt.setInt(1, this.sid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Store sto = new Store();
                sto.setSid(rs.getInt("sid"));
                sto.setRegionid(rs.getInt("regionid"));
                sto.setName(rs.getString("name"));
                sto.setStreet(rs.getString("street"));
                sto.setCity(rs.getString("city"));
                sto.setState(rs.getString("state"));
                sto.setZip(rs.getString("zip"));
                sto.setPhone(rs.getString("phone"));
                sto.setManager(rs.getInt("manager"));
                storelist.add(sto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return storelist;
    }

    public ArrayList<Store> getAllRecord() {
        ArrayList<Store> storelist = new ArrayList<Store>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM stores");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Store sto = new Store();
                sto.setSid(rs.getInt("sid"));
                sto.setRegionid(rs.getInt("regionid"));
                sto.setName(rs.getString("name"));
                sto.setStreet(rs.getString("street"));
                sto.setCity(rs.getString("city"));
                sto.setState(rs.getString("state"));
                sto.setZip(rs.getString("zip"));
                sto.setPhone(rs.getString("phone"));
                sto.setManager(rs.getInt("manager"));
                storelist.add(sto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return storelist;
    }

    public boolean addNewRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO stores (regionid,manager,name,street,city,state,zip,phone) VALUES" +
                    "(? ,?,'" + this.getName() + "','" + this.getStreet() + "','" + this.getCity() + "','" + this.getState() + "','" + this.getZip() + "','" + this.getPhone() + "')");
            stmt.setInt(1, this.getRegionid());
            stmt.setInt(2, this.getManager());
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            dbConnection.closeConnection();
            return false;
        }
    }

    public boolean updateRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE stores SET regionid = ?,name='" + this.getName() + "',street='" + this.getStreet() + "',city='" + this.getCity() + "',state ='" + this.getState() + "',zip='" + this.getZip() + "',phone='" + this.getPhone() + "',manager=? WHERE sid =?");
            stmt.setInt(1, this.getRegionid());
            stmt.setInt(2, this.getManager());
            stmt.setInt(3, this.getSid());
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            dbConnection.closeConnection();
            return false;
        }
    }

    public boolean deleteRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM stores WHERE sid =?");
            stmt.setInt(1, this.sid);
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnection.closeConnection();
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


}
