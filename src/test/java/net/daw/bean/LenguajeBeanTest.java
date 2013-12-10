/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alvaro
 */
public class LenguajeBeanTest {

    public LenguajeBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("test: LenguajeBean");
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testsGetterSettersId() {
        System.out.println("test: Method Get Set Id");
        LenguajeBean oLenguajeBean = new LenguajeBean();

        oLenguajeBean.setId(1);
        assertEquals("getId: devuelve 1", oLenguajeBean.getId(), 1);
    }
    
    @Test
    public void testsGetterSettersNombre() {
        System.out.println("test: Method Get Set Nombre");
        LenguajeBean oLenguajeBean = new LenguajeBean();

        oLenguajeBean.setNombre("nombre");
        assertEquals("getNombre: devuelve nombre", oLenguajeBean.getNombre(), "nombre");
    }
}
