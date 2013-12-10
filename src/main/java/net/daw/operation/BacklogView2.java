
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogView2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/backlog/list.jsp");
        oContexto.setClase("backlog");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        BacklogList1 oOperacion = new BacklogList1();
        return oOperacion.execute(request, response);
    }
}
