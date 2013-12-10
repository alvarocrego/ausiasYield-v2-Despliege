/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;
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
public class DocumentoBeanTest {

    public DocumentoBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("test: DocumentoBean");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testsGetterSettersId() {
        System.out.println("test: Method Get Set Id");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        oDocumentoBean.setId(1);
        assertEquals("getId: devuelve 1", oDocumentoBean.getId(), 1);
    }

    @Test
    public void testsGetterSettersTitulo() {
        System.out.println("test: Method Get Set Titulo");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        oDocumentoBean.setTitulo("titulo");
        assertEquals("getNombre: devuelve titulo", oDocumentoBean.getTitulo(), "titulo");
    }

    @Test
    public void testsGetterSettersContenido() {
        System.out.println("test: Method Get Set Contenido");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        oDocumentoBean.setContenido("contenido");
        assertEquals("getNombre: devuelve contenido", oDocumentoBean.getContenido(), "contenido");
    }

    @Test
    public void testsGetterSettersFecha() {
        System.out.println("test: Method Get Set Fecha");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        Date fecha = new Date();
        oDocumentoBean.setFecha(fecha);
        assertEquals("getNombre: devuelve fecha", oDocumentoBean.getFecha(), fecha);
    }

    @Test
    public void testsGetterSettersNota() {
        System.out.println("test: Method Get Set Nota");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        oDocumentoBean.setNota(5);
        assertEquals("getNombre: devuelve 5", oDocumentoBean.getNota(), 5);
    }

    @Test
    public void testsGetterSettersUsuario() {
        System.out.println("test: Method Get Set Usuario");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        //oDocumentoBean.setUsuario("titulo");
        //assertEquals("getNombre: devuelve nombre", oDocumentoBean.getTitulo(), "titulo");
    }

    @Test
    public void testsGetterSettersEtiquetas() {
        System.out.println("test: Method Get Set Etiquetas");
        DocumentoBean oDocumentoBean = new DocumentoBean();

        oDocumentoBean.setEtiquetas("etiquetas");
        assertEquals("getNombre: devuelve etiquetas", oDocumentoBean.getEtiquetas(), "etiquetas");

    }
}
