/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.mysql.jdbc.Connection;
import database.dbConnection;
import java.math.BigDecimal;
import java.sql.Date;
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
public class Employee extends Record {

    int eid;
    int storeid;
    String lastname;
    String firstname;
    String title;
    String email;
    BigDecimal salary;
    String streetaddr;
    String city;
    String state;
    String zip;
    Date startdate;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public String getStreetaddr() {
        return streetaddr;
    }

    public void setStreetaddr(String streetaddr) {
        this.streetaddr = streetaddr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO employee (storeid,firstname,lastname,title,email,streetaddr,salary,city,state,zip,startdate)" +
                    "VALUES (?,'" + this.firstname + "','" + this.lastname + "','" + this.title + "','" + this.email + "','" + this.streetaddr + "','" + this.salary + "','" + this.city + "','" + this.state + "','" + this.zip + "','" + this.startdate + "')");
            stmt.setInt(1, this.storeid);
            stmt.execute();
             dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
             dbConnection.closeConnection();
            return false;
        }
    }

    @Override
    public boolean updateRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE employee SET storeid=?,firstname='" + this.firstname + "',lastname='" + this.lastname + "',title='" + this.title + "',email='" + this.email + "',streetaddr='" + this.streetaddr + "',salary=?," +
                    "city='" + this.city + "',state='" + this.state + "',zip='" + this.zip + "',startdate=? WHERE eid=?");
            stmt.setInt(1, this.storeid);
            stmt.setBigDecimal(2, this.salary);
            stmt.setDate(3, this.startdate);
            stmt.setInt(4, this.eid);
            stmt.execute();
             dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
             dbConnection.closeConnection();
            return false;
        }

    }

    @Override
    public boolean deleteRecord() {
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM employee WHERE eid=?");
            stmt.setInt(1, this.eid);
            stmt.execute();
             dbConnection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
             dbConnection.closeConnection();
            return false;
        }

    }

    @Override
    public ArrayList getAllRecord() {
        ArrayList<Employee> emplist = new ArrayList<Employee>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM employee");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEid(rs.getInt("eid"));
                emp.setStoreid(rs.getInt("storeid"));
                emp.setFirstname(rs.getString("firstname"));
                emp.setLastname(rs.getString("lastname"));
                emp.setTitle(rs.getString("title"));
                emp.setEmail(rs.getString("email"));
                emp.setSalary(rs.getBigDecimal("salary"));
                emp.setStreetaddr(rs.getString("streetaddr"));
                emp.setCity(rs.getString("city"));
                emp.setState(rs.getString("state"));
                emp.setZip(rs.getString("zip"));
                emp.setStartdate(rs.getDate("startdate"));
                emplist.add(emp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
         dbConnection.closeConnection();
        return emplist;
    }

    @Override
    public ArrayList getRecord() {
        ArrayList<Employee> emplist = new ArrayList<Employee>();
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = dbConnection.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM employee WHERE eid=?");
            stmt.setInt(1, this.eid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEid(rs.getInt("eid"));
                emp.setStoreid(rs.getInt("storeid"));
                emp.setFirstname(rs.getString("firstname"));
                emp.setLastname(rs.getString("lastname"));
                emp.setTitle(rs.getString("title"));
                emp.setEmail(rs.getString("email"));
                emp.setSalary(rs.getBigDecimal("salary"));
                emp.setStreetaddr(rs.getString("streetaddr"));
                emp.setCity(rs.getString("city"));
                emp.setState(rs.getString("state"));
                emp.setZip(rs.getString("zip"));
                emp.setStartdate(rs.getDate("startdate"));
                emplist.add(emp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
         dbConnection.closeConnection();
        return emplist;
    }

}
