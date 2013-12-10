package net.daw.operation;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.LenguajeBean;
import net.daw.dao.LenguajeDao;
import net.daw.helper.Contexto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diana
 */
public class LenguajeRellena1 implements Operation {
    
        @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        LenguajeBean oLenguajeBean = new LenguajeBean();
        LenguajeDao oLenguajeDao = new LenguajeDao(oContexto.getEnumTipoConexion());
        String nombre = "";
        
        ArrayList<String> arrNombres = new ArrayList<>();
        arrNombres.add("AngularJS");
        arrNombres.add("CSS3");
        arrNombres.add("CoffeeScript");
        arrNombres.add("Dojo");
        arrNombres.add("ExtJS");
        arrNombres.add("HTML");
        arrNombres.add("TrypeScrpit");
        arrNombres.add("JavaScript");
        arrNombres.add("jQuery");
        arrNombres.add("MooTools");
        arrNombres.add("Prototype");
        arrNombres.add("Raphael");
        arrNombres.add("VB Scrpit");
        arrNombres.add("LiveScrpit");
        arrNombres.add("Bootstrap");
        arrNombres.add("ada (Gnat)");
        arrNombres.add("Algol-68");
        arrNombres.add("Assembly");
        arrNombres.add("Awk");
        arrNombres.add("Bash Shell");
        arrNombres.add("Basic");
        arrNombres.add("Befunge");
        arrNombres.add("C++");
        arrNombres.add("COBOL");
        arrNombres.add("Clojure");
        arrNombres.add("Dart");
        
        int index;
        Iterator<String> iterador = arrNombres.listIterator();
        Random generator;
        
        while (iterador.hasNext()) {
            nombre = iterador.next();
            generator = new Random();
            oLenguajeBean.setId(0);
            
           
            index = generator.nextInt(arrNombres.size());
            String randomNOMBRE = arrNombres.get(index);
            oLenguajeBean.setNombre(randomNOMBRE);
            
            try {
                oLenguajeDao.set(oLenguajeBean);

            } catch (Exception e) {
                throw new ServletException("LenguajeController: Update Error: Phase 2: " + e.getMessage());
            }
        }

        return "<div class=\"alert alert-info\">OK- Información Autorellena Lenguaje generada.</div>";
    }
}
    

