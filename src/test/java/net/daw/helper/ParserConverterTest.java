/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.helper;

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
public class ParserConverterTest {

    public ParserConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("test: ParserConverterTest");
    }

    @After
    public void tearDown() {
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testTag() throws Exception {
        System.out.println("test: Method Tag");
        ParserConverter oParserConverter = new ParserConverter();
        String tag = oParserConverter.tag("======Probando====== el ======metodo====== para comprobar si ======funciona======", "======", "h6");
        assertEquals("Tag: devuelve texto parseado", tag, "<h6>Probando</h6> el <h6>metodo</h6> para comprobar si <h6>funciona</h6>");
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testA() throws Exception {
        System.out.println("test: Method A");
        ParserConverter oParserConverter = new ParserConverter();
        String a = oParserConverter.a("[http://intel.com|Intel] hola [8|Enlaze interno]", "class=documento&method=view");
        assertEquals("A: devuelve enlazes parseados", a, "<a href='http://intel.com'>Intel&nbsp<i class='icon-globe'></i></a> hola <a href='Controller?class=documento&method=view&id=8'>Enlaze interno&nbsp<i class='icon-globe'></i></a>");
    }
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void testP() throws Exception {
        System.out.println("test: Method P");
        ParserConverter oParserConverter = new ParserConverter();
        String p = oParserConverter.p("hola \n que tal \n yo bien");
        assertEquals("P: devuelve parrafos parseados", p, "<p>hola </p><p> que tal </p><p> yo bien</p>");
    }
}
