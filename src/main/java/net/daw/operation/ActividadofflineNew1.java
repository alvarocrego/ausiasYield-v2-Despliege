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


public class ActividadofflineNew1 implements Operation{
    
     @Override
     public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         Contexto oContexto = (Contexto) request.getAttribute("contexto");
         oContexto.setVista("jsp/actividadOffline/form.jsp");        
         return null;
     }
    
    
    
    
}
