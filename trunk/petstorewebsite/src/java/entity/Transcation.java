/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import database.dbConnection;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeff
 */
public class Transcation extends Record {

    int orderid;
    int storeid;
    int customerid;
    int salesmanid;
    Date orderdate;
    BigDecimal ordertotal = new BigDecimal(0.0);
    HashMap<Product, Integer> purchaselist = new HashMap<Product, Integer>();

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public BigDecimal getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void addProduct2list(int productid, int quantity) {
        Product pro = new Product(productid);
        ordertotal = ordertotal.add(pro.getUniprice().multiply(new BigDecimal(quantity)));
        purchaselist.put(pro, quantity);
    }

    public boolean addProduct2Orderlist(int pid, int quantity) {
        if (checkAvailability(this.storeid, pid, quantity)) {
            int total = checkAvailability(this.storeid, pid);
            total -= quantity;
            if(!updateInventory(this.storeid, pid, total)){
                return false;
            }
            Product pro = new Product(pid);
            ordertotal = ordertotal.add(pro.getUniprice().multiply(new BigDecimal(quantity)));
            purchaselist.put(pro, quantity);
            return true;
        }
        return false;

    }

    public HashMap<Product, Integer> getPurchaselist() {
        return purchaselist;
    }

    public void setPurchaselist(HashMap<Product, Integer> purchaselist) {
        this.purchaselist = purchaselist;
    }

    public int getSalesmanid() {
        return salesmanid;
    }

    public void setSalesmanid(int salesmanid) {
        this.salesmanid = salesmanid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    @Override
    public boolean addNewRecord() {
        try {

            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("INSERT INTO orders (sid,cid,salesmanid,odate,totalamount) VALUES" +
                    "(?,?,?,?,?)");
            stmt.setInt(1, this.storeid);
            stmt.setInt(2, this.customerid);
            stmt.setInt(3, this.salesmanid);
            stmt.setDate(4, this.orderdate);
            stmt.setBigDecimal(5, this.ordertotal);
            stmt.execute();

            PreparedStatement stmtforid = dbConnection.getConnection().prepareStatement("SELECT MAX(oid) AS oid FROM orders");
            ResultSet rsforid = stmtforid.executeQuery();

            if (rsforid.next()) {
                this.setOrderid(rsforid.getInt("oid"));
            }
            //then is the purchase list
            Iterator itp = purchaselist.keySet().iterator();
            while (itp.hasNext()) {
                Product pro = (Product) itp.next();
                int quantity = purchaselist.get(pro);
                PreparedStatement stmt2 = dbConnection.getConnection().prepareStatement("INSERT INTO orderdetail (oid,pid,quantity) VALUES(?,?,?)");
                stmt2.setInt(1, this.orderid);
                stmt2.setInt(2, pro.getPid());
                stmt2.setInt(3, quantity);
                stmt2.execute();
            }
            dbConnection.closeConnection();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean updateRecord() {
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("UPDATE  orders SET sid=?,cid=?,salesmanid=?,odate=?,totalamount=? WHERE oid=?");
            stmt.setInt(1, this.storeid);
            stmt.setInt(2, this.customerid);
            stmt.setInt(3, this.salesmanid);
            stmt.setDate(4, this.orderdate);
            stmt.setBigDecimal(5, this.ordertotal);
            stmt.setInt(6, this.orderid);
            stmt.execute();
            //then is the purchase list
            Iterator itp = purchaselist.keySet().iterator();
            while (itp.hasNext()) {
                //now it cann't handle add or sub product from list
                //future work.
                Product pro = (Product) itp.next();
                int quantity = purchaselist.get(pro);
                PreparedStatement stmt2 = dbConnection.getConnection().prepareStatement("UPDATE orderdetail SET quantity=? WHERE oid=? AND pid=?");
                stmt2.setInt(1, quantity);
                stmt2.setInt(2, this.orderid);
                stmt2.setInt(3, pro.getPid());
                stmt2.execute();
                dbConnection.closeConnection();
            }
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean deleteRecord() {
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("DELETE FROM orders WHERE oid = ?");
            stmt.setInt(1, this.orderid);
            stmt.execute();
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public ArrayList getAllRecord() {
        ArrayList<Transcation> tranlist = new ArrayList<Transcation>();
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("SELECT * FROM orders ORDER BY odate DESC");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transcation tran = new Transcation();
                tran.setOrderid(rs.getInt("oid"));
                tran.setStoreid(rs.getInt("sid"));
                tran.setCustomerid(rs.getInt("cid"));
                tran.setSalesmanid(rs.getInt("salesmanid"));
                //tran.setOrdertotal(rs.getBigDecimal("totalamount"));
                tran.setOrderdate(rs.getDate("odate"));
                tran.setPurchaselist();
                //tran.syncOrdertotal();
                tranlist.add(tran);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbConnection.closeConnection();
        return tranlist;
    }

    @Override
    public ArrayList getRecord() {
        ArrayList<Transcation> tranlist = new ArrayList<Transcation>();
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("SELECT * FROM orders WHERE oid=?");
            stmt.setInt(1, this.orderid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transcation tran = new Transcation();
                tran.setOrderid(rs.getInt("oid"));
                tran.setStoreid(rs.getInt("sid"));
                tran.setCustomerid(rs.getInt("cid"));
                tran.setSalesmanid(rs.getInt("salesmanid"));
                tran.setOrderdate(rs.getDate("odate"));
                tran.setPurchaselist();
                tranlist.add(tran);
            }
            dbConnection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tranlist;
    }

    public void setPurchaselist() {
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("SELECT * FROM orderdetail WHERE oid=?");
            stmt.setInt(1, this.orderid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                addProduct2list(rs.getInt("pid"), rs.getInt("quantity"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePurchaselist(int pid, int quantity) {

        Product pro = new Product(pid);
        if (purchaselist.containsKey(pro) && purchaselist.get(pro).equals(quantity)) {
            return;
        }
        if (purchaselist.containsKey(pro)) {
            BigDecimal uniontotal = pro.getUniprice().multiply(new BigDecimal(purchaselist.get(pro)));
            ordertotal.subtract(uniontotal);
            purchaselist.remove(pro);
        }
        //put new quantity of pro
        purchaselist.put(pro, quantity);
        ordertotal = ordertotal.add(pro.getUniprice().multiply(new BigDecimal(quantity)));
    }

    public void syncOrdertotal() {
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("UPDATE orders SET totalamount=?  WHERE oid=?");
            stmt.setBigDecimal(1, this.ordertotal);
            stmt.setInt(2, this.orderid);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkAvailability(int sid, int pid, int quantity) {
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("SELECT quantity FROM inventory WHERE storeid=? AND productid=?");
            stmt.setInt(1, sid);
            stmt.setInt(2, pid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int available = rs.getInt("quantity");
                if (available >= quantity) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int checkAvailability(int sid, int pid) {
        int quantity = 0;
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("SELECT quantity FROM inventory WHERE storeid=? AND productid=?");
            stmt.setInt(1, sid);
            stmt.setInt(2, pid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }

    public boolean updateInventory(int sid, int pid, int quantity) {
        try {
            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("UPDATE inventory SET quantity=? WHERE storeid=? AND productid=?");
            stmt.setInt(1, quantity);
            stmt.setInt(2, sid);
            stmt.setInt(3, pid);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Transcation.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }
}
