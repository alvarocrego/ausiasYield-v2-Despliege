/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;

/**
 *
 * @author ana
 */
public class RepositorioView2 implements Operation{
    
        @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/repositorio/list.jsp");
        oContexto.setClase("repositorio");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        RepositorioList1 oOperacion = new RepositorioList1();
        return oOperacion.execute(request, response);
    }
    
}
