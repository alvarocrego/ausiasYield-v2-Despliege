package net.daw.parameter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.LenguajeBean;
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
public class LenguajeParamTest {

    public LenguajeParamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("test: LenguajeParam");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testsGetterSetters() throws IOException, Exception {
        System.out.println("test: LenguajeParam");

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nombre")).thenReturn("nombre");

        LenguajeBean oLenguajeBean = new LenguajeBean();
        LenguajeParam oLenguajeParam = new LenguajeParam(request);

        oLenguajeBean = oLenguajeParam.loadId(oLenguajeBean);

        assertEquals("getId: devuelve 1", oLenguajeBean.getId(), 1);

        oLenguajeBean = oLenguajeParam.load(oLenguajeBean);

        assertEquals("getNombre: devuelve nombre", oLenguajeBean.getNombre(), "nombre");
    }

}
