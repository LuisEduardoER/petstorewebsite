/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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
public class TranscationTest {

    public TranscationTest() {
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
     * Test of getOrderdate method, of class Transcation.
     */
    @Test
    public void testGetOrderdate() {
        System.out.println("getOrderdate");
        Transcation instance = new Transcation();
        Date expResult = null;
        Date result = instance.getOrderdate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderdate method, of class Transcation.
     */
    @Test
    public void testSetOrderdate() {
        System.out.println("setOrderdate");
        Date orderdate = null;
        Transcation instance = new Transcation();
        instance.setOrderdate(orderdate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdertotal method, of class Transcation.
     */
    @Test
    public void testGetOrdertotal() {
        System.out.println("getOrdertotal");
        Transcation instance = new Transcation();
        BigDecimal expResult = null;
        BigDecimal result = instance.getOrdertotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrdertotal method, of class Transcation.
     */
    @Test
    public void testSetOrdertotal() {
        System.out.println("setOrdertotal");
        BigDecimal ordertotal = null;
        Transcation instance = new Transcation();
        instance.setOrdertotal(ordertotal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerid method, of class Transcation.
     */
    @Test
    public void testGetCustomerid() {
        System.out.println("getCustomerid");
        Transcation instance = new Transcation();
        int expResult = 0;
        int result = instance.getCustomerid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCustomerid method, of class Transcation.
     */
    @Test
    public void testSetCustomerid() {
        System.out.println("setCustomerid");
        int customerid = 0;
        Transcation instance = new Transcation();
        instance.setCustomerid(customerid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderid method, of class Transcation.
     */
    @Test
    public void testGetOrderid() {
        System.out.println("getOrderid");
        Transcation instance = new Transcation();
        int expResult = 0;
        int result = instance.getOrderid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderid method, of class Transcation.
     */
    @Test
    public void testSetOrderid() {
        System.out.println("setOrderid");
        int orderid = 0;
        Transcation instance = new Transcation();
        instance.setOrderid(orderid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProduct2list method, of class Transcation.
     */
    @Test
    public void testAddProduct2list() {
        System.out.println("addProduct2list");
        int productid = 0;
        int quantity = 0;
        Transcation instance = new Transcation();
        instance.addProduct2list(productid, quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPurchaselist method, of class Transcation.
     */
    @Test
    public void testGetPurchaselist() {
        System.out.println("getPurchaselist");
        Transcation instance = new Transcation();
        HashMap expResult = null;
        HashMap result = instance.getPurchaselist();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPurchaselist method, of class Transcation.
     */
    @Test
    public void testSetPurchaselist_HashMap() {
        System.out.println("setPurchaselist");
        HashMap<Product, Integer> purchaselist = null;
        Transcation instance = new Transcation();
        instance.setPurchaselist(purchaselist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalesmanid method, of class Transcation.
     */
    @Test
    public void testGetSalesmanid() {
        System.out.println("getSalesmanid");
        Transcation instance = new Transcation();
        int expResult = 0;
        int result = instance.getSalesmanid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSalesmanid method, of class Transcation.
     */
    @Test
    public void testSetSalesmanid() {
        System.out.println("setSalesmanid");
        int salesmanid = 0;
        Transcation instance = new Transcation();
        instance.setSalesmanid(salesmanid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoreid method, of class Transcation.
     */
    @Test
    public void testGetStoreid() {
        System.out.println("getStoreid");
        Transcation instance = new Transcation();
        int expResult = 0;
        int result = instance.getStoreid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStoreid method, of class Transcation.
     */
    @Test
    public void testSetStoreid() {
        System.out.println("setStoreid");
        int storeid = 0;
        Transcation instance = new Transcation();
        instance.setStoreid(storeid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewRecord method, of class Transcation.
     */
    @Test
    public void testAddNewRecord() {
        System.out.println("addNewRecord");
        Transcation instance = new Transcation();

        instance.setCustomerid(11);
        instance.setOrderdate(new Date(2009-10-11));
        instance.setSalesmanid(1);
        instance.setStoreid(1);
        instance.addProduct2list(1,3);
        instance.addProduct2list(3,10);

        boolean expResult = false;
        boolean result = instance.addNewRecord();
    }

    /**
     * Test of updateRecord method, of class Transcation.
     */
    @Test
    public void testUpdateRecord() {
        System.out.println("updateRecord");
        Transcation instance = new Transcation();
        instance.setOrderid(66);
        instance.setCustomerid(5);
        instance.setOrderdate(new Date(2009-10-11));
        instance.setSalesmanid(14);
        instance.setStoreid(1);

        instance.addProduct2list(1,9);
        instance.addProduct2list(3,3);
        boolean expResult = false;
        //boolean result = instance.updateRecord();
    }

    /**
     * Test of deleteRecord method, of class Transcation.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        Transcation instance = new Transcation();
        instance.setOrderid(66);
        boolean expResult = false;
       // boolean result = instance.deleteRecord();

    }

    /**
     * Test of getAllRecord method, of class Transcation.
     */
    @Test
    public void testGetAllRecord() {
        System.out.println("getAllRecord");
        Transcation instance = new Transcation();
        ArrayList expResult = null;
        //ArrayList result = instance.getAllRecord();
    }

    /**
     * Test of getRecord method, of class Transcation.
     */
    @Test
    public void testGetRecord() {
        System.out.println("getRecord");
        Transcation instance = new Transcation();
        instance.setOrderid(58);
        ArrayList expResult = null;
        //ArrayList result = instance.getRecord();
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}