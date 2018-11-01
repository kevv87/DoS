/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Interfaz.Btree;
import Interfaz.NodeBtree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.LinkedList;

/**
 *
 * @author TomÃ¡s
 */
public class BTreeTest {

    public BTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class Btree.
     */
    @Test
    public void testSearch_NodeBtree_String() {
        System.out.println("search");
        NodeBtree node = new NodeBtree();
        String value = "A2";
        Btree instance = new Btree();
        boolean expResult = false;
        boolean result = instance.search(node, value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class Btree.
     */
    @Test
    public void testSearch_String() {
        System.out.println("search");
        String k = "A1";
        Btree instance = new Btree();
        instance.insert("A1");
        boolean expResult = true;
        boolean result = instance.search(k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class Btree.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        Btree instance = new Btree();
        String expResult = "";
        String result = instance.print();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conversion method, of class Btree.
     */
    @Test
    public void testConversion() {
        System.out.println("conversion");
        String nombre = "G6";
        Btree instance = new Btree();
        int expResult = 480;
        int result = instance.conversion(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

