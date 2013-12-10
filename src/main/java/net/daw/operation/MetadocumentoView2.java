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
 * @author al037431
 */
public class MetadocumentoView2 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/metadocumento/list.jsp");
        oContexto.setClase("metadocumento");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        MetadocumentoList1 oOperacion = new MetadocumentoList1();
        return oOperacion.execute(request, response);
    }
    
}
