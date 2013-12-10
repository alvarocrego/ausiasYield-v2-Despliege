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
 * @author jordi eslava barrera
 */
public class RequerimientoView2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/requerimiento/list.jsp");
        oContexto.setClase("requerimiento");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        RequerimientoList1 oOperacion = new RequerimientoList1();
        return oOperacion.execute(request, response);
    }
}

