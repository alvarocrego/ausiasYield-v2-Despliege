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
 * @author Diana Ortega
 */
public class EstadoView2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/estado/list.jsp");
        oContexto.setClase("estado");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        EstadoList1 oOperacion = new EstadoList1();
        return oOperacion.execute(request, response);
    }
}
