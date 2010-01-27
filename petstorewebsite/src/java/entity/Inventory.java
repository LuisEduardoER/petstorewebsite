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
public class Inventory extends Record{

    int storeId;
    String storename;
    int productId;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
    String productname;
    int quantity;

    public Inventory() {
    }

    public Inventory(int sid, int pid, int pamount) {
        storeId = sid;
        productId = pid;
        quantity = pamount;
    }

    public ArrayList<Inventory> getRecord() {
        ArrayList<Inventory> inv_list = new ArrayList<Inventory>();
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conn.prepareStatement("SELECT sto.name,pro.pname,inv.storeid,inv.productid,inv.quantity from stores sto,products pro,inventory inv WHERE inv.storeid=sto.sid AND pro.pid=inv.productid AND inv.storeid=? AND inv.productid=?");
            stmt.setInt(1, this.storeId);
            stmt.setInt(2, this.productId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setStoreId(rs.getInt("storeid"));
                inv.setProductId(rs.getInt("productid"));
                inv.setStorename(rs.getString("name"));
                inv.setProductname(rs.getString("pname"));
                inv.setProductAmount(rs.getInt("quantity"));
                inv_list.add(inv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return inv_list;
    }

    public ArrayList<Inventory> getAllRecord() {
        ArrayList<Inventory> inv_list = new ArrayList<Inventory>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT sto.name,pro.pname,inv.storeid,inv.productid,inv.quantity from stores sto,products pro,inventory inv WHERE inv.storeid=sto.sid AND pro.pid=inv.productid");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setStoreId(rs.getInt("storeid"));
                inv.setProductId(rs.getInt("productid"));
                inv.setStorename(rs.getString("name"));
                inv.setProductname(rs.getString("pname"));
                inv.setProductAmount(rs.getInt("quantity"));
                inv_list.add(inv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return inv_list;
    }

    public boolean addNewRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory (storeid,productid,quantity) VALUES" +
                    "(?,?,?)");
            stmt.setInt(1, this.storeId);//Integer.valueOf(sto_region)
            stmt.setInt(2, this.productId);
            stmt.setInt(3, this.quantity);
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET quantity=? WHERE storeid =? AND productid=?");
            stmt.setInt(1, this.getProductAmount());
            stmt.setInt(2, this.getStoreId());
            stmt.setInt(3, this.getProductId());
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
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM inventory WHERE storeid =? AND productid=?");
            stmt.setInt(1, this.getStoreId());
            stmt.setInt(2, this.getProductId());
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            dbConnection.closeConnection();
            return false;
        }

    }

    public int getProductAmount() {
        return quantity;
    }

    public void setProductAmount(int productAmount) {
        this.quantity = productAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

}
