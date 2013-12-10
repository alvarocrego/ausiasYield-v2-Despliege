/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EstadoBean;
import net.daw.dao.EstadoDao;
import net.daw.helper.Contexto;

/**
 *
 * @author al037684
 */
public class EstadoRellena1 implements Operation {
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        EstadoBean oEstadoBean = new EstadoBean();
        EstadoDao oEstadoDao = new EstadoDao(oContexto.getEnumTipoConexion());
        String nombre = "";
        
        ArrayList<String> arrNombres = new ArrayList<>();
        arrNombres.add("abierta");
        arrNombres.add("es_estudio");
        arrNombres.add("resuelta");
        arrNombres.add("integrada");
        arrNombres.add("fase_inicial");
        arrNombres.add("fase_intermedia");
        arrNombres.add("fase_final");
        arrNombres.add("asignada");
        arrNombres.add("no_asignada");
        arrNombres.add("fase_test");
        arrNombres.add("trabajando");
       
        
        int index;
        Iterator<String> iterador = arrNombres.listIterator();
        Random generator;
        
        while (iterador.hasNext()) {
            nombre = iterador.next();
            generator = new Random();
            oEstadoBean.setId(0);
            
           
            index = generator.nextInt(arrNombres.size());
            String randomNOMBRE = arrNombres.get(index);
            oEstadoBean.setNombre(randomNOMBRE);
            
            try {
                oEstadoDao.set(oEstadoBean);

            } catch (Exception e) {
                throw new ServletException("EstadoController: Update Error: Phase 2: " + e.getMessage());
            }
        }

        return "<div class=\"alert alert-info\">OK- Información Autorellena Estado generada.</div>";
    }
    
}
