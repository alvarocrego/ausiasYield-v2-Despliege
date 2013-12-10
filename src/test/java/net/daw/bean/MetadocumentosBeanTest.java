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
public class MetadocumentosBeanTest {
    
    public MetadocumentosBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("test: MetadocumentosBean");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testsGetterSettersId() {
        System.out.println("test: Method Get Set Id");
        MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean();
        
        oMetadocumentosBean.setId(1);
        assertEquals("getId: devuelve 1", oMetadocumentosBean.getId(), 1);
    }
    
    @Test
    public void testsGetterSettersOrden() {
        System.out.println("test: Method Get Set Orden");
        MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean();
        
        oMetadocumentosBean.setOrden(7);
        assertEquals("getNombre: devuelve 7", oMetadocumentosBean.getOrden(), 7);
    }
}
