
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;

/**
 *
 * @author jose
 */
public class EntradaView2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/entrada/list.jsp");
        oContexto.setClase("entrada");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        EntradaList1 oOperacion = new EntradaList1();
        return oOperacion.execute(request, response);
    }
}
