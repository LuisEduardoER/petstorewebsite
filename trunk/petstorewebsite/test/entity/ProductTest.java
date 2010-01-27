/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;
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
public class ProductTest {

    public ProductTest() {
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

    @Test
    public void testAddNewRecord() {
        System.out.println("addnewrecord");
        Product instance = new Product();
        instance.setPname("cat fish food");
        instance.setUniprice(new BigDecimal(2.99));
        instance.setCategory("cat canned food");

        // instance.addNewRecord();
    }

    /**
     * Test of getCategory method, of class Product.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategory method, of class Product.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        String category = "";
        Product instance = new Product();
        instance.setCategory(category);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPid method, of class Product.
     */
    @Test
    public void testGetPid() {
        System.out.println("getPid");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getPid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPid method, of class Product.
     */
    @Test
    public void testSetPid() {
        System.out.println("setPid");
        int pid = 0;
        Product instance = new Product();
        instance.setPid(pid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPname method, of class Product.
     */
    @Test
    public void testGetPname() {
        System.out.println("getPname");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getPname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPname method, of class Product.
     */
    @Test
    public void testSetPname() {
        System.out.println("setPname");
        String pname = "";
        Product instance = new Product();
        instance.setPname(pname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUniprice method, of class Product.
     */
    @Test
    public void testGetUniprice() {
        System.out.println("getUniprice");
        Product instance = new Product();
        BigDecimal expResult = null;
        BigDecimal result = instance.getUniprice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUniprice method, of class Product.
     */
    @Test
    public void testSetUniprice() {
        System.out.println("setUniprice");
        BigDecimal uniprice = null;
        Product instance = new Product();
        instance.setUniprice(uniprice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRecord method, of class Product.
     */
    @Test
    public void testUpdateRecord() {
        System.out.println("updateRecord");
        Product instance = new Product();
        instance.setPid(27);
        instance.setPname("cat fish food");
        instance.setUniprice(new BigDecimal(4.99));
        instance.setCategory("cat canned food");
        
        boolean expResult = false;
       // boolean result = instance.updateRecord();

    }

    /**
     * Test of deleteRecord method, of class Product.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        Product instance = new Product();
        instance.setPid(27);
        boolean expResult = false;
       // boolean result = instance.deleteRecord();
    }

    /**
     * Test of getAllRecord method, of class Product.
     */
    @Test
    public void testGetAllRecord() {
        System.out.println("getAllRecord");
        Product instance = new Product();
        ArrayList expResult = null;
        //ArrayList result = instance.getAllRecord();
    }

    /**
     * Test of getRecord method, of class Product.
     */
    @Test
    public void testGetRecord() {
        System.out.println("getRecord");
        Product instance = new Product();
        instance.setPid(24);
        ArrayList expResult = null;
        ArrayList result = instance.getRecord();
    }

    /**
     * Test of setProductbyID method, of class Product.
     */
    @Test
    public void testSetProductbyID() {
        System.out.println("setProductbyID");
        Product instance = new Product();
        instance.setProductbyID();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
