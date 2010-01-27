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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeff
 */
public class Customer extends Record {

    public final static boolean homecustomer = false;
    public final static boolean buscustomer = true;
    final static int ihomecus = 0;
    final static int ibuscus = 1;
    public final static boolean male = true;
    public final static boolean female = false;
    final static int imale = 1;
    final static int ifemale = 0;
    int customerid;
    String lastname;
    String firstname;
    String streetaddr;
    String city;
    String state;
    String zip;
    String phone;
    boolean ctype;
    //for home customer
    boolean gender;
    boolean marriage;
    int age;
    String preference;
    java.math.BigDecimal income;
    //for business customer
    String industry;
    String companyname;
    String company_desc;
    //db conn

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public int getCtype() {
        if (ctype == buscustomer) {
            return ibuscus;
        } else {
            return ihomecus;
        }
    }

    public void setCtype(boolean ctype) {
        this.ctype = ctype;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getCompany_desc() {
        return company_desc;
    }

    public void setCompany_desc(String company_desc) {
        this.company_desc = company_desc;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getGender() {
        if (gender == male) {
            return imale;
        } else {
            return ifemale;
        }
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int isMarriage() {
        if (marriage == true) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setMarriage(boolean marriage) {
        this.marriage = marriage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetaddr() {
        return streetaddr;
    }

    public void setStreetaddr(String streetaddr) {
        this.streetaddr = streetaddr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean addNewRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (streetaddr,city,state,zip,ctype,lastname,firstname,phone) VALUES" +
                    "('" + this.streetaddr + "','" + this.city + "','" + this.state + "','" + this.zip + "',?,'" + this.lastname + "','" + this.firstname + "','" + this.phone + "')");
            stmt.setInt(1, this.getCtype());
            stmt.execute();
            int cid = 0;
            //need to get the cid
            Statement smt = conn.createStatement();
            ResultSet idset = smt.executeQuery("SELECT cid FROM customers WHERE streetaddr='" + this.streetaddr + "' AND lastname='" + this.lastname + "' AND firstname='" + this.firstname + "'");
            if (idset.next()) {
                cid = idset.getInt("cid");
            } else {
                return false;
            }

            if (this.ctype == buscustomer) {
                //business
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO customerbus (hcid,companyname,company_desc,industry,annual_income) VALUES " +
                        "(?,'" + this.companyname + "','" + this.company_desc + "','" + this.industry + "',?)");
                stmt2.setInt(1, cid);
                stmt2.setBigDecimal(2, income);
                stmt2.execute();
            } else {
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO customerhome (bcid,age,preference,gender,marriage) VALUES" +
                        "(?,?,'" + this.preference + "',?,?)");
                stmt2.setInt(1, cid);
                stmt2.setInt(2, this.age);
                stmt2.setInt(3, this.getGender());
                stmt2.setInt(4, this.isMarriage());
                stmt2.execute();
            }
            dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
             dbConnection.closeConnection();
            return false;
        }
    }

    @Override
    public boolean updateRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET streetaddr='" + this.streetaddr + "',city='" + this.city + "',state='" + this.state + "'," +
                    "zip='" + this.zip + "',ctype=?,lastname='" + this.lastname + "',firstname='" + this.firstname + "',phone='" + this.phone + "' WHERE cid=?");
            stmt.setInt(1, this.getCtype());
            stmt.setInt(2, this.customerid);
            stmt.execute();
            if (this.ctype == buscustomer) {
                PreparedStatement stmt2 = conn.prepareStatement("UPDATE customerbus SET companyname='" + this.companyname + "',company_desc='" + this.company_desc + "'," +
                        "industry='" + this.industry + "',annual_income=? WHERE hcid = ?");
                stmt2.setBigDecimal(1, this.income);
                stmt2.setInt(2, this.customerid);
                stmt2.execute();
            } else {
                PreparedStatement stmt2 = conn.prepareStatement("UPDATE customerhome SET age=?,preference='" + this.preference + "',gender=?,marriage=? WHERE  bcid=?");
                stmt2.setInt(1, this.age);
                stmt2.setInt(2, this.getGender());
                stmt2.setInt(3, this.isMarriage());
                stmt2.setInt(4, this.customerid);
                stmt2.execute();
            }
             dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
             dbConnection.closeConnection();
            return false;
        }
    }

    @Override
    public boolean deleteRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE cid =?");
            stmt.setInt(1, this.customerid);
            stmt.execute();
             dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
             dbConnection.closeConnection();
            return false;
        }
    }

    @Override
    public ArrayList getAllRecord() {
        //need to set customer type fist use setCtype()
        // then call this routine
        ArrayList<Customer> cuslist = new ArrayList<Customer>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            if (this.ctype == buscustomer) {
                stmt = conn.prepareStatement("SELECT * FROM customers cs,customerbus cb WHERE cs.cid=cb.hcid ");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Customer cus = new Customer();
                    cus.setCustomerid(rs.getInt("cid"));
                    cus.setFirstname(rs.getString("firstname"));
                    cus.setLastname(rs.getString("lastname"));
                    cus.setStreetaddr(rs.getString("streetaddr"));
                    cus.setCity(rs.getString("city"));
                    cus.setState(rs.getString("state"));
                    cus.setZip(rs.getString("zip"));
                    cus.setPhone(rs.getString("phone"));
                    cus.setCtype(rs.getBoolean("ctype"));

                    cus.setCompanyname(rs.getString("companyname"));
                    cus.setCompany_desc(rs.getString("company_desc"));
                    cus.setIndustry(rs.getString("industry"));
                    cus.setIncome(rs.getBigDecimal("annual_income"));
                    cuslist.add(cus);
                }

            } else {
                stmt = conn.prepareStatement("SELECT * FROM customers cs,customerhome ch WHERE cs.cid=ch.bcid ");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Customer cus = new Customer();
                    cus.setCustomerid(rs.getInt("cid"));
                    cus.setFirstname(rs.getString("firstname"));
                    cus.setLastname(rs.getString("lastname"));
                    cus.setStreetaddr(rs.getString("streetaddr"));
                    cus.setCity(rs.getString("city"));
                    cus.setState(rs.getString("state"));
                    cus.setZip(rs.getString("zip"));
                    cus.setPhone(rs.getString("phone"));
                    cus.setCtype(rs.getBoolean("ctype"));
                    
                    cus.setAge(rs.getInt("age"));
                    cus.setPreference(rs.getString("preference"));
                    cus.setMarriage(rs.getBoolean("marriage"));
                    cus.setGender(rs.getBoolean("gender"));
                    cuslist.add(cus);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
         dbConnection.closeConnection();
        return cuslist;
    }

    @Override
    public ArrayList getRecord() {
        // need to fill in customer id first use setCustomerid()
        // then call this function
        ArrayList<Customer> cuslist = new ArrayList<Customer>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM customers WHERE cid=? ");
            stmt.setInt(1, this.customerid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Customer cus = new Customer();
                cus.setCustomerid(rs.getInt("cid"));
                cus.setFirstname(rs.getString("firstname"));
                cus.setLastname(rs.getString("lastname"));
                cus.setStreetaddr(rs.getString("streetaddr"));
                cus.setCity(rs.getString("city"));
                cus.setState(rs.getString("state"));
                cus.setZip(rs.getString("zip"));
                cus.setPhone(rs.getString("phone"));
                cus.setCtype(rs.getBoolean("ctype"));
                if (cus.ctype == buscustomer) {
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM customerbus WHERE hcid=?");
                    stmt2.setInt(1, cus.customerid);
                    ResultSet rs1 = stmt2.executeQuery();
                    if (rs.next()) {
                        cus.setCompanyname(rs1.getString("companyname"));
                        cus.setCompany_desc(rs1.getString("company_desc"));
                        cus.setIndustry(rs1.getString("industry"));
                        cus.setIncome(rs1.getBigDecimal("annual_income"));
                    }
                } else {
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM customerhome WHERE bcid=?");
                    stmt2.setInt(1, cus.customerid);
                    ResultSet rs1 = stmt2.executeQuery();
                    if (rs1.next()) {
                        cus.setAge(rs1.getInt("age"));
                        cus.setPreference(rs1.getString("preference"));
                        cus.setMarriage(rs1.getBoolean("marriage"));
                        cus.setGender(rs1.getBoolean("gender"));
                    }
                }
                cuslist.add(cus);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
         dbConnection.closeConnection();
        return cuslist;
    }
}
