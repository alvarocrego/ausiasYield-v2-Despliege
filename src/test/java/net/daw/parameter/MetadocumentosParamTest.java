/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.parameter;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.MetadocumentosBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Alvaro
 */
public class MetadocumentosParamTest {

    public MetadocumentosParamTest() {
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

    @Test
    public void testsGetterSetters() throws IOException, Exception {
        System.out.println("test: MetadocumentosParam");
        
        HttpServletRequest request = mock(HttpServletRequest.class);
        
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("orden")).thenReturn("9");
        
        MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean();
        MetadocumentosParam oMetadocumentosParam = new MetadocumentosParam(request);

        oMetadocumentosBean = oMetadocumentosParam.loadId(oMetadocumentosBean);
        
        assertEquals("getId: devuelve 1", oMetadocumentosBean.getId(), 1);
        
        oMetadocumentosBean = oMetadocumentosParam.load(oMetadocumentosBean);
        
        assertEquals("getNombre: devuelve 9", oMetadocumentosBean.getOrden(), 9);
    }

}
