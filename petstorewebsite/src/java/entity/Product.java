/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.mysql.jdbc.Connection;
import database.dbConnection;
import java.math.BigDecimal;

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
public class Product extends Record {

    int pid;
    String pname;
    BigDecimal uniprice;
    String category;

    public Product(){}

    public Product(int pid){
        this.pid = pid;
        this.setProductbyID();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getUniprice() {
        return uniprice;
    }

    public void setUniprice(BigDecimal uniprice) {
        this.uniprice = uniprice;
    }

    @Override
    public boolean addNewRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (pname,uniprice,category) VALUES" +
                    "('" + pname + "',?,'" + category + "')");
            stmt.setBigDecimal(1, this.uniprice);
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            dbConnection.closeConnection();
            return false;
        }
    }

    @Override
    public boolean updateRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET pname='" + this.pname + "',uniprice=?, category='" + this.category + "' WHERE pid=?");
            stmt.setBigDecimal(1, this.uniprice);
            stmt.setInt(2, this.pid);
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            dbConnection.closeConnection();
            return false;
        }

    }

    @Override
    public boolean deleteRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE pid=?");
            stmt.setInt(1, this.pid);
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList getAllRecord() {
        ArrayList<Product> prolist = new ArrayList<Product>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM products");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPid(rs.getInt("pid"));
                pro.setPname(rs.getString("pname"));
                pro.setUniprice(rs.getBigDecimal("uniprice"));
                pro.setCategory(rs.getString("category"));
                prolist.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return prolist;
    }


     public ArrayList getAllRecordbyOrder(String column, String direction) {
        ArrayList<Product> prolist = new ArrayList<Product>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            String query = "SELECT * FROM products ORDER BY "+column+" "+direction;
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPid(rs.getInt("pid"));
                pro.setPname(rs.getString("pname"));
                pro.setUniprice(rs.getBigDecimal("uniprice"));
                pro.setCategory(rs.getString("category"));
                prolist.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return prolist;
    }


    public ArrayList getAllRecordbySearch(String keyword) {
        ArrayList<Product> prolist = new ArrayList<Product>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM products WHERE pname LIKE ?");
            keyword = "%"+keyword+"%";
            stmt.setString(1,keyword);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPid(rs.getInt("pid"));
                pro.setPname(rs.getString("pname"));
                pro.setUniprice(rs.getBigDecimal("uniprice"));
                pro.setCategory(rs.getString("category"));

                prolist.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return prolist;
    }

    @Override
    public ArrayList getRecord() {
        ArrayList<Product> prolist = new ArrayList<Product>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM products WHERE pid = ?");
            stmt.setInt(1, this.pid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPid(rs.getInt("pid"));
                pro.setPname(rs.getString("pname"));
                pro.setUniprice(rs.getBigDecimal("uniprice"));
                pro.setCategory(rs.getString("category"));

                prolist.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return prolist;
    }


    public void setProductbyID(){
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM products WHERE pid = ?");
            stmt.setInt(1, this.pid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                this.setPname(rs.getString("pname"));
                this.setUniprice(rs.getBigDecimal("uniprice"));
                this.setCategory(rs.getString("category"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
