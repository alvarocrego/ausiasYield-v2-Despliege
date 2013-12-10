package net.daw.parameter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.DocumentoBean;
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
public class DocumentoParamTest {

    public DocumentoParamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
                System.out.println("test: DocumentoParam");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testsParamId() throws IOException, Exception {
        System.out.println("test: Param Id");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("id")).thenReturn("1");

        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
        
        Date fecha = new Date();
        java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        assertEquals("getId: devuelve 1", oDocumentoBean.getId(), 1);

        assertEquals("getTitulo: devuelve nada", oDocumentoBean.getTitulo(), "");
        assertEquals("getcontendio: devuelve nada", oDocumentoBean.getContenido(), "");
        assertEquals("getFecha: devuelve nada", oSimpleDateFormat.format(oDocumentoBean.getFecha()), oSimpleDateFormat.format(fecha));
        assertEquals("getNota: devuelve nada", oDocumentoBean.getNota(), 0);
        assertEquals("getEtiquetas: devuelve nada", oDocumentoBean.getEtiquetas(), "");
    }

    @Test
    public void testsParamTitulo() throws IOException, Exception {
        System.out.println("test: Param Titulo");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("titulo")).thenReturn("titulo");

        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
        
        Date fecha = new Date();
        java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        assertEquals("getTitulo: devuelve titulo", oDocumentoBean.getTitulo(), "titulo");

        assertEquals("getId: devuelve 0", oDocumentoBean.getId(), 0);
        assertEquals("getcontendio: devuelve nada", oDocumentoBean.getContenido(), "");
        assertEquals("getFecha: devuelve nada", oSimpleDateFormat.format(oDocumentoBean.getFecha()), oSimpleDateFormat.format(fecha));
        assertEquals("getNota: devuelve 0", oDocumentoBean.getNota(), 0);
        assertEquals("getEtiquetas: devuelve nada", oDocumentoBean.getEtiquetas(), "");
    }

    @Test
    public void testsParamContendio() throws IOException, Exception {
        System.out.println("test: Param Contenido");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("contenido")).thenReturn("contenido");

        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
        
        Date fecha = new Date();
        java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        assertEquals("getContenido: devuelve contenido", oDocumentoBean.getContenido(), "contenido");
        
        assertEquals("getId: devuelve 0", oDocumentoBean.getId(), 0);
        assertEquals("getTitulo: devuelve nada", oDocumentoBean.getTitulo(), "");
        assertEquals("getFecha: devuelve nada", oSimpleDateFormat.format(oDocumentoBean.getFecha()), oSimpleDateFormat.format(fecha));
        assertEquals("getNota: devuelve nada", oDocumentoBean.getNota(), 0);
        assertEquals("getEtiquetas: devuelve nada", oDocumentoBean.getEtiquetas(), "");
        
    }

    @Test
    public void testsParamFecha() throws IOException, Exception {
        System.out.println("test: Param Fecha");
        HttpServletRequest request = mock(HttpServletRequest.class);

        Date fecha = new Date();
        java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        when(request.getParameter("fecha")).thenReturn(oSimpleDateFormat.format(fecha));

        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);

        assertEquals("getFecha: devuelve fecha", oSimpleDateFormat.format(oDocumentoBean.getFecha()), oSimpleDateFormat.format(fecha));
    
        assertEquals("getId: devuelve 0", oDocumentoBean.getId(), 0);
        assertEquals("getTitulo: devuelve nada", oDocumentoBean.getTitulo(), "");
        assertEquals("getContendio: devuelve nada", oDocumentoBean.getContenido(), "");
        assertEquals("getNota: devuelve nada", oDocumentoBean.getNota(), 0);
        assertEquals("getEtiquetas: devuelve nada", oDocumentoBean.getEtiquetas(), "");
    }

    @Test
    public void testsParamNota() throws IOException, Exception {
        System.out.println("test: Param Nota");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("nota")).thenReturn("10");

        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
        
        Date fecha = new Date();
        java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        when(request.getParameter("fecha")).thenReturn(oSimpleDateFormat.format(fecha));

        assertEquals("getNota: devuelve nota", oDocumentoBean.getNota(), 10);
        
        assertEquals("getId: devuelve 0", oDocumentoBean.getId(), 0);
        assertEquals("getTitulo: devuelve nada", oDocumentoBean.getTitulo(), "");
        assertEquals("getcontendio: devuelve nada", oDocumentoBean.getContenido(), "");
        assertEquals("getFecha: devuelve nada", oSimpleDateFormat.format(oDocumentoBean.getFecha()), oSimpleDateFormat.format(fecha));
        assertEquals("getEtiquetas: devuelve nada", oDocumentoBean.getEtiquetas(), "");
    }

    @Test
    public void testsParamUsuario() throws IOException, Exception {
        System.out.println("test: Param Usuario");
        HttpServletRequest request = mock(HttpServletRequest.class);

        //when(request.getParameter("id_usuario")).thenReturn("id_usuario");
        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);

        //assertEquals("getNombre: devuelve id_usuario", oDocumentoBean.getTitulo(), "id_usuario");
    }

    @Test
    public void testsParamEtiquetas() throws IOException, Exception {
        System.out.println("test: Param Etiquetas");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("etiquetas")).thenReturn("etiquetas");

        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
        
        Date fecha = new Date();
        java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        when(request.getParameter("fecha")).thenReturn(oSimpleDateFormat.format(fecha));

        assertEquals("getNombre: devuelve etiquetas", oDocumentoBean.getEtiquetas(), "etiquetas");
        
        assertEquals("getId: devuelve 0", oDocumentoBean.getId(), 0);
        assertEquals("getTitulo: devuelve nada", oDocumentoBean.getTitulo(), "");
        assertEquals("getcontendio: devuelve nada", oDocumentoBean.getContenido(), "");
        assertEquals("getFecha: devuelve nada", oSimpleDateFormat.format(oDocumentoBean.getFecha()), oSimpleDateFormat.format(fecha));
        assertEquals("getNota: devuelve nada", oDocumentoBean.getNota(), 0);
    }
}
