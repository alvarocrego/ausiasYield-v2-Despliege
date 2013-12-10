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
 * @author Enrique Gimeno
 */
public class IncidenciasView2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/incidencias/list.jsp");
        oContexto.setClase("incidencias");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        IncidenciasList1 oOperacion = new IncidenciasList1();
        return oOperacion.execute(request, response);
    }
    
}
