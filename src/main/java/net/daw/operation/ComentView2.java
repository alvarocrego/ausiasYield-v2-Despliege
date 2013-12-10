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
 * @author Jordi Eslava Barrera
 */
public class ComentView2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/comentario/list.jsp");
        oContexto.setClase("coment");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        ComentList1 oOperacion = new ComentList1();
        return oOperacion.execute(request, response);
    }
}
