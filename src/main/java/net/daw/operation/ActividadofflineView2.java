/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

/**
 *
 * @author Javier Bonet
 */

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import net.daw.helper.Contexto;


public class ActividadofflineView2 implements Operation {
    
    @Override
     public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         Contexto oContexto = (Contexto) request.getAttribute("contexto");
         oContexto.setVista("jsp/actividadOffline/list.jsp");
         oContexto.setClase("actividadOffline");
         oContexto.setMetodo("list");
         oContexto.setFase("1");
         ActividadofflineList1 oOperacion = new ActividadofflineList1();
         return oOperacion.execute(request, response);
    
    }   
    
}
