/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeff
 */
public class EmployeeTest {

    public EmployeeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCity method, of class Employee.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCity method, of class Employee.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "";
        Employee instance = new Employee();
        instance.setCity(city);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEid method, of class Employee.
     */
    @Test
    public void testGetEid() {
        System.out.println("getEid");
        Employee instance = new Employee();
        int expResult = 0;
        int result = instance.getEid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEid method, of class Employee.
     */
    @Test
    public void testSetEid() {
        System.out.println("setEid");
        int eid = 0;
        Employee instance = new Employee();
        instance.setEid(eid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Employee.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Employee.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Employee instance = new Employee();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstname method, of class Employee.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getFirstname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstname method, of class Employee.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        String firstname = "";
        Employee instance = new Employee();
        instance.setFirstname(firstname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastname method, of class Employee.
     */
    @Test
    public void testGetLastname() {
        System.out.println("getLastname");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getLastname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastname method, of class Employee.
     */
    @Test
    public void testSetLastname() {
        System.out.println("setLastname");
        String lastname = "";
        Employee instance = new Employee();
        instance.setLastname(lastname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalary method, of class Employee.
     */
    @Test
    public void testGetSalary() {
        System.out.println("getSalary");
        Employee instance = new Employee();
        BigDecimal expResult = null;
        BigDecimal result = instance.getSalary();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSalary method, of class Employee.
     */
    @Test
    public void testSetSalary() {
        System.out.println("setSalary");
        BigDecimal salary = null;
        Employee instance = new Employee();
        instance.setSalary(salary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartdate method, of class Employee.
     */
    @Test
    public void testGetStartdate() {
        System.out.println("getStartdate");
        Employee instance = new Employee();
        Date expResult = null;
        Date result = instance.getStartdate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartdate method, of class Employee.
     */
    @Test
    public void testSetStartdate() {
        System.out.println("setStartdate");
        Date startdate = null;
        Employee instance = new Employee();
        instance.setStartdate(startdate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Employee.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class Employee.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        String state = "";
        Employee instance = new Employee();
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoreid method, of class Employee.
     */
    @Test
    public void testGetStoreid() {
        System.out.println("getStoreid");
        Employee instance = new Employee();
        int expResult = 0;
        int result = instance.getStoreid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStoreid method, of class Employee.
     */
    @Test
    public void testSetStoreid() {
        System.out.println("setStoreid");
        int storeid = 0;
        Employee instance = new Employee();
        instance.setStoreid(storeid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStreetaddr method, of class Employee.
     */
    @Test
    public void testGetStreetaddr() {
        System.out.println("getStreetaddr");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getStreetaddr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStreetaddr method, of class Employee.
     */
    @Test
    public void testSetStreetaddr() {
        System.out.println("setStreetaddr");
        String streetaddr = "";
        Employee instance = new Employee();
        instance.setStreetaddr(streetaddr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Employee.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Employee.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Employee instance = new Employee();
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZip method, of class Employee.
     */
    @Test
    public void testGetZip() {
        System.out.println("getZip");
        Employee instance = new Employee();
        String expResult = "";
        String result = instance.getZip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZip method, of class Employee.
     */
    @Test
    public void testSetZip() {
        System.out.println("setZip");
        String zip = "";
        Employee instance = new Employee();
        instance.setZip(zip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewRecord method, of class Employee.
     */
    @Test
    public void testAddNewRecord() {
        System.out.println("addNewRecord");
        Employee instance = new Employee();
        instance.setStoreid(11);
        instance.setFirstname("Mike");
        instance.setLastname("Spring");
        instance.setTitle("sales");
        instance.setEmail("mbs@petstore.com");
        instance.setSalary(new BigDecimal(60000.00));
        instance.setStreetaddr("135 N Bellefield St");
        instance.setCity("Pittsburgh");
        instance.setState("PA");
        instance.setZip("15207");
        instance.setStartdate(new Date(2007-1-1));

        boolean expResult = false;
        boolean result = instance.addNewRecord();
//        assertEquals(expResult, result);

    }

    /**
     * Test of updateRecord method, of class Employee.
     */
    @Test
    public void testUpdateRecord() {
        System.out.println("updateRecord");
        Employee instance = new Employee();
        instance.setEid(12);
        instance.setStoreid(4);
        instance.setFirstname("Rogger");
        instance.setLastname("Spring");
        instance.setTitle("sales");
        instance.setEmail("mbs@petstore.com");
        instance.setSalary(new BigDecimal(60000.00));
        instance.setStreetaddr("135 N Bellefield St");
        instance.setCity("Pittsburgh");
        instance.setState("PA");
        instance.setZip("15207");
        instance.setStartdate(new Date(2007-1-1));
        boolean expResult = false;
//        boolean result = instance.updateRecord();
//        assertEquals(expResult, result);

    }

    /**
     * Test of deleteRecord method, of class Employee.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        Employee instance = new Employee();
        instance.setEid(12);
        boolean expResult = false;
//        boolean result = instance.deleteRecord();
//        assertEquals(expResult, result);

    }

    /**
     * Test of getAllRecord method, of class Employee.
     */
    @Test
    public void testGetAllRecord() {
        System.out.println("getAllRecord");
        Employee instance = new Employee();
        ArrayList expResult = null;
        ArrayList result = instance.getAllRecord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecord method, of class Employee.
     */
    @Test
    public void testGetRecord() {
        System.out.println("getRecord");
        Employee instance = new Employee();
        instance.setEid(11);
        ArrayList expResult = null;
        ArrayList result = instance.getRecord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
